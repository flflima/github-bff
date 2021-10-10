package br.com.dev.web

import br.com.dev.integration.ApiIntegrationService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

@Controller("/github")
class GithubController(@Inject val apiIntegrationService: ApiIntegrationService) {

    @Get("/{user}/timeline")
    fun getUserTimeline(user: String) = this.apiIntegrationService.fetchRepos(user)
}
