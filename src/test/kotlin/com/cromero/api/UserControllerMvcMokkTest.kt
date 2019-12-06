package com.cromero.api

import com.cromero.api.controller.UserController
import com.cromero.api.service.Color
import com.cromero.api.service.User
import com.cromero.api.service.UserService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

// TODO test mockk library and compare with mockito
// https://www.baeldung.com/kotlin-mockk

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
@AutoConfigureMockMvc
internal class UserControllerMvcMokkTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userService: UserService

    @Test
    fun `find Al users returns list`() {

        val users = listOf(
            User(
                id = 1,
                name = "pepe",
                age = 22,
                favoriteNumber = "3",
                color = Color.assignColorPerAge(22)
            ),
            User(
                id = 2,
                name = "manolo",
                age = 25,
                favoriteNumber = "8",
                color = Color.assignColorPerAge(25)
            ),
            User(
                id = 3,
                name = "juan",
                age = 27,
                favoriteNumber = "9",
                color = Color.assignColorPerAge(27)
            )
        )

        every { userService.findAll() } returns users

        mockMvc.perform(get("/user"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$").isArray)
            .andExpect(jsonPath("$", hasSize<Any>(3)))

        verify { userService.findAll() }
    }

    @ParameterizedTest
    @CsvSource(
        "1,pepe,12,22",
        "2,manolo,33,25",
        "3,juan,45,27"
    )
    fun `find one users returns user detail information`(
        idExpected: Long,
        nameExpected: String,
        ageExpected: Int,
        favoriteNumberExpected: String
    ) {

        val user = User(
            id = idExpected,
            name = nameExpected,
            age = ageExpected,
            favoriteNumber = favoriteNumberExpected,
            color = Color.assignColorPerAge(ageExpected)
        )

        every { userService.findByName(nameExpected) } returns user

        mockMvc.perform(get("/user/$nameExpected"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.id").value(idExpected))
            .andExpect(jsonPath("\$.name").value(nameExpected))
            .andExpect(jsonPath("\$.age").value(ageExpected))
            .andExpect(jsonPath("\$.favoriteNumber").value(favoriteNumberExpected))

        verify { userService.findByName(nameExpected) }
    }
}
