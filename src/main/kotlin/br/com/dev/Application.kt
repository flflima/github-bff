package br.com.dev

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.micronaut.context.event.BeanCreatedEvent
import io.micronaut.context.event.BeanCreatedEventListener
import io.micronaut.runtime.Micronaut.build
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import jakarta.inject.Singleton


@OpenAPIDefinition(
    info = Info(
        title = "github-bff",
        version = "0.0"
    )
)
object Api {
}

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("br.com.dev")
        .start()
}

@Singleton
internal class ObjectMapperBeanEventListener : BeanCreatedEventListener<ObjectMapper> {
    override fun onCreated(event: BeanCreatedEvent<ObjectMapper>): ObjectMapper {
        val mapper: ObjectMapper = event.bean
        mapper.registerModule(JavaTimeModule())
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS)
        return mapper
    }
}
