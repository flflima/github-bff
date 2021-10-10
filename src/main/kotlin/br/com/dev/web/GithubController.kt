package br.com.dev.web

import br.com.dev.integration.ApiIntegrationService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

@Controller("/github")
class GithubController(@Inject val apiIntegrationService: ApiIntegrationService) {

    @Get("/{user}/timeline")
    fun getUserTimeline(
        user: String,
        @QueryValue(defaultValue = "10") total_page: Int = 1,
        @QueryValue(defaultValue = "1") page: Int = 1
    ) = this.apiIntegrationService.fetchRepos(user, total_page, page)
}
