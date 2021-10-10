package br.com.dev.integration

import br.com.dev.web.dto.ResponseDTO

interface ApiIntegrationService {
    fun fetchRepos(username: String, totalPage: Int, page: Int): ResponseDTO
}