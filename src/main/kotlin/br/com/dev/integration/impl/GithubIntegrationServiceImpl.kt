package br.com.dev.integration.impl

import br.com.dev.integration.ApiIntegrationService
import br.com.dev.web.dto.ProjectDTO
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpHeaders.ACCEPT
import io.micronaut.http.HttpHeaders.USER_AGENT
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import jakarta.inject.Singleton
import reactor.core.publisher.Mono
import java.net.URI

@Singleton
class GithubIntegrationServiceImpl(
    @param:Client("\${github.url}") private val httpClient: HttpClient
) :
    ApiIntegrationService {

    override fun fetchRepos(username: String, total_page: Int, page: Int): Mono<MutableList<ProjectDTO>>? {
        val uri: URI = UriBuilder.of("/users")
            .path(username)
            .path("/repos?per_page=$total_page&page=$page&sort=created_at&direction=desc")
            .build()

        val req: HttpRequest<*> = HttpRequest.GET<Any>(uri)
            .header(USER_AGENT, "Micronaut HTTP Client")
            .header(ACCEPT, "application/vnd.github.v3+json, application/json")

        return Mono.from(httpClient.retrieve(req, Argument.listOf(ProjectDTO::class.java)))
    }
}