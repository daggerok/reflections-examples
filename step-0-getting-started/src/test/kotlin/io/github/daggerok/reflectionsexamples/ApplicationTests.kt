package io.github.daggerok.reflectionsexamples

import org.apache.logging.log4j.kotlin.logger
import org.junit.jupiter.api.Test
import org.reflections.Reflections
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Configuration

@SpringBootTest
class ApplicationTests(@Autowired val applicationContext: AnnotationConfigApplicationContext) {

    @Test
    fun `should test context and reflections library`() {
        val reflections = Reflections()
        log.info { "reflections store keys: ${reflections.store.keys}" }

        val classes = reflections.getTypesAnnotatedWith(Configuration::class.java).sortedBy { it.name }
        log.info { "@Configurations annotated classes: ${classes.size}" }
        // classes.forEach(log::info)

        // val springBootApps = reflections.getTypesAnnotatedWith(SpringBootApplication::class.java)
        // log.info { "springBootApps: $springBootApps" }

        val res = reflections.getTypesAnnotatedWith(SpringBootApplication::class.java)
        log.info { "res: $res" }
        log.info { applicationContext.getBean(res.first()) }
    }

    companion object { val log = logger() }
}
