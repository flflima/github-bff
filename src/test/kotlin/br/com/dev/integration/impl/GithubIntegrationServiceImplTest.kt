package br.com.dev.integration.impl

import br.com.dev.web.dto.ProjectDTO
import com.github.javafaker.Faker
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


internal class GithubIntegrationServiceImplTest {

    private val httpClient = mockk<HttpClient>()
    private val service = GithubIntegrationServiceImpl(httpClient)

    @Test
    fun `it should fetch and paginate results`() {
        // arrange
        val faker = Faker()
        val result = mutableListOf(
            ProjectDTO(faker.company().buzzword(), faker.company().catchPhrase(), LocalDateTime.now(), faker.internet().url()),
            ProjectDTO(faker.company().buzzword(), faker.company().catchPhrase(), LocalDateTime.now(), faker.internet().url()),
            ProjectDTO(faker.company().buzzword(), faker.company().catchPhrase(), LocalDateTime.now(), faker.internet().url()),
            )

        val blocking = mockk<BlockingHttpClient>()
        every { httpClient.toBlocking() } returns blocking
        every { blocking.retrieve(any<HttpRequest<*>>(), any<Argument<MutableList<ProjectDTO>>>()) } returns result

        // act
        val resp = service.fetchRepos(faker.internet().slug(), 1, 1)

        // assert
        assertThat(resp).isNotNull
        assertThat(resp.page).isEqualTo(1)
        assertThat(resp.totalPage).isEqualTo(3)
        assertThat(resp.data).size().isEqualTo(3)
    }

    @Test
    fun `it should fetch and return response with empty data list`() {
        // arrange
        val faker = Faker()
        val result = mutableListOf<ProjectDTO>()

        val blocking = mockk<BlockingHttpClient>()
        every { httpClient.toBlocking() } returns blocking
        every { blocking.retrieve(any<HttpRequest<*>>(), any<Argument<MutableList<ProjectDTO>>>()) } returns result

        // act
        val resp = service.fetchRepos(faker.internet().slug(), 1, 1)

        // assert
        assertThat(resp).isNotNull
        assertThat(resp.page).isEqualTo(1)
        assertThat(resp.totalPage).isEqualTo(0)
        assertThat(resp.data).isEmpty()
    }
}