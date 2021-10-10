package br.com.dev.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseDTO(
    val page: Int,
    @JsonProperty("total_page")
    val totalPage: Int,
    val data: MutableList<ProjectDTO>
)
