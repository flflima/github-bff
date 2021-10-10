package br.com.dev.integration.impl

import br.com.dev.web.dto.ProjectDTO
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.reactivestreams.Publisher
import reactor.core.publisher.Mono

internal class GithubIntegrationServiceImplTest {

    val httpClient = mockk<HttpClient>()

    val service = GithubIntegrationServiceImpl(httpClient)

    @Test
    fun `it should fetch and paginate results`() {
        val publisherMock = mockk<Publisher<MutableList<ProjectDTO>>>()

        every { publisherMock.subscribe(any()) } just Runs
        every { httpClient.retrieve(any<HttpRequest<*>>(), any<Argument<MutableList<ProjectDTO>>>()) } returns Mono.from(publisherMock)

        service.fetchRepos("", 1, 1)
    }
}