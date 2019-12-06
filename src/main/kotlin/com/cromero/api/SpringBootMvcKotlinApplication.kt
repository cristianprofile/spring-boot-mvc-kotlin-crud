package com.cromero.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(CustomProperties::class)
class SpringBootMvcKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringBootMvcKotlinApplication>(*args)
}
