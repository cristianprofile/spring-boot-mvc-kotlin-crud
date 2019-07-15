package com.cromero.api

import com.cromero.api.controller.UserController
import com.cromero.api.service.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import com.cromero.api.service.User
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc

// TODO test mockk library and compare with mockito
// https://www.baeldung.com/kotlin-mockk

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
@AutoConfigureMockMvc
internal class UserControllerMvcMokkTest(@Autowired  val mockMvc: MockMvc)

{

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun service() = mockk<UserService>()
    }


    @Autowired
    private lateinit var userService: UserService

    @Test
    fun `Hello returns correct message`() {

        val users= listOf( User(id=1,name = "pepe",age = 22,favoriteNumber = "3"),
                User(id=2,name = "manolo",age = 25,favoriteNumber = "8"),
                User(id=3,name = "juan",age = 27,favoriteNumber = "9"))

        every { userService.findAll() } returns users

        val result = mockMvc.perform(get("/user"))
                .andExpect(status().isOk)

        verify { userService.findAll()}
    }


}
