package br.com.dev.web.dto

import com.fasterxml.jackson.annotation.*
import java.time.LocalDateTime

data class ProjectDTO(
    val name: String,
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    val description: String? = "",
    @JsonProperty("created_at", access = JsonProperty.Access.WRITE_ONLY)
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    )
    private val _createdAt: LocalDateTime,
    @JsonProperty("html_url", access = JsonProperty.Access.WRITE_ONLY)
    private val _url: String
) {
    val createdAt: LocalDateTime
        @JsonProperty("created_at", access = JsonProperty.Access.READ_ONLY)
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
        get() = this._createdAt

    val url: String
        get() = this._url
}
