package br.com.dev
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@MicronautTest
class GithubBffTest {

    @Inject
    lateinit var application: EmbeddedApplication<*>

    @Test
    fun `application should be running`() {
        assertTrue(application.isRunning)
    }

}
