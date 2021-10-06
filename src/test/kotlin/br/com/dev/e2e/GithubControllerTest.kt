package br.com.dev.e2e

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

@MicronautTest
class GithubControllerTest {

    private val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)

    @Test
    fun `it should return the username in the path URL`() {
        RestAssured.given().port(embeddedServer.port)
            .`when`()
            .get("/github/user-test/timeline")
            .then()
            .statusCode(200)
            .body(CoreMatchers.equalTo("user-test"))
    }
}