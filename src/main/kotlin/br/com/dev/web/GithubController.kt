package br.com.dev.web

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/github")
class GithubController {

    @Get("/{user}/timeline")
    fun getUserTimeline(user: String) = user
}