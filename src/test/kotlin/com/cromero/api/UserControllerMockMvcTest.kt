package com.cromero.api


import com.cromero.api.controller.User
import com.cromero.api.repository.UserEntity
import com.cromero.api.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerMockMvcTest (@Autowired  val mockMvc: MockMvc,
                                 @Autowired  val restTemplate: TestRestTemplate,
                                 @Autowired  val userRepository: UserRepository) {

    @Test
    fun `controller Must return User`() {
        //save an user
        val user = UserEntity(name = "pepe",age = 33,favoriteNumber = "25")
        userRepository.save(user)

        mockMvc.perform(get("/user/${user.name}")).andExpect(status().isOk)
                .andExpect(jsonPath("\$.name").value(user.name))
                .andExpect(jsonPath("\$.age").value(user.age))
                .andExpect(jsonPath("\$.favoriteNumber").value(user.favoriteNumber))
    }


    @Test
    fun `controller Must return User using rest template`() {
        //save an user
        val user = UserEntity(name = "manolo",age = 33,favoriteNumber = "54")
        userRepository.save(user)

        //get the user by id
        val result = restTemplate.getForEntity<User>("/user/${user.name}")

        //user asserts from database
        assertEquals(result.statusCode,HttpStatus.OK)
        assertTrue(result.body!!.name == user.name)
        assertTrue(result.body!!.age == user.age)
        assertTrue(result.body!!.favoriteNumber == user.favoriteNumber)

    }

}
