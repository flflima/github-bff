package br.com.dev.integration

import br.com.dev.web.dto.ProjectDTO
import reactor.core.publisher.Mono

interface ApiIntegrationService {
    fun fetchRepos(username: String, total_page: Int, page: Int): Mono<MutableList<ProjectDTO>>?
}