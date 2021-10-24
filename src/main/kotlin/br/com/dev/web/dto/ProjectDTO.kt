package br.com.dev.web.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls.AS_EMPTY
import java.time.LocalDateTime

data class ProjectDTO(
  val name: String,
  @JsonInclude(ALWAYS)
  @JsonSetter(nulls = AS_EMPTY)
  val description: String? = "",
  @JsonProperty("created_at", access = WRITE_ONLY)
  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
  )
  private val _createdAt: LocalDateTime,
  @JsonProperty("html_url", access = WRITE_ONLY)
  private val _url: String
) {
  val createdAt: LocalDateTime
    @JsonProperty("created_at", access = READ_ONLY)
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
    get() = this._createdAt

  val url: String
    get() = this._url
}
