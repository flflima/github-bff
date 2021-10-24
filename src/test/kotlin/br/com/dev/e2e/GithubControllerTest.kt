package br.com.dev.e2e

import io.micronaut.context.ApplicationContext.run
import io.micronaut.runtime.server.EmbeddedServer
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasKey
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockserver.client.MockServerClient
import org.mockserver.integration.ClientAndServer.startClientAndServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response

class GithubControllerTest {

  private lateinit var embeddedServer: EmbeddedServer
  private val port = 8000
  private lateinit var mockServerClient: MockServerClient

  @BeforeEach
  fun init() {
    embeddedServer = run(EmbeddedServer::class.java)
    mockServerClient = startClientAndServer(port)
  }

  @AfterEach
  fun tearDown() {
    mockServerClient.stop()
  }

  @Test
  fun `it should return a list of repositories for given username`() {
    mockServerClient.`when`(
      request()
        .withMethod("GET")
        .withPath("/users/user-test/repos")
    ).respond(
      response()
        .withStatusCode(200)
        .withHeaders(
          Header("Content-Type", "application/json; charset=utf-8"),
          Header("Cache-Control", "public, max-age=86400")
        )
        .withBody(
          """
              [
                 {
                    "name":"aws",
                    "html_url":"https://github.com/user-test",
                    "description":"A Mock Project",
                    "created_at":"2021-05-09T00:13:21Z"
                 }
              ]
          """.trimIndent()
        )
    )

    given().port(embeddedServer.port)
      .`when`()
      .get("/github/user-test/timeline")
      .then()
      .assertThat()
      .statusCode(200)
      .and()
      .body("total_page", `is`(1))
      .body("page", `is`(1))
      .and()
      .body("data.size()", `is`(1))
      .body("data[0]", hasKey("name"))
      .body("data[0]", hasKey("description"))
      .body("data[0]", hasKey("created_at"))
      .body("data[0]", hasKey("url"))
  }
}
