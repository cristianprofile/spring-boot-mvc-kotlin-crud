package com.cromero.api

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.net.InetAddress
import java.time.Duration

// https://docs.spring.io/spring-boot/docs/2.2.0.BUILD-SNAPSHOT/reference/html/spring-boot-features.html#boot-features-kotlin-configuration-properties

@ConstructorBinding
@ConfigurationProperties("com.cromero.application")
data class CustomProperties(
    val name: String? = "app with no name",
    val description: String,
    val database: Database
) {
    data class Database(
        val host: InetAddress? = InetAddress.getByName("127.0.0.1"),
        val port: Int,
        val connectTimeout: Duration = Duration.ofMillis(1000)
    )
}
