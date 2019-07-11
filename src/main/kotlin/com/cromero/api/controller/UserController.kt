package com.cromero.api.controller

import com.cromero.api.service.User
import com.cromero.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/user")
class UserController (val userService: UserService) {

    @GetMapping
    fun findAll(): List<User> {
        return userService.findAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user: com.cromero.api.controller.User){
        val user = User(name = user.name, age=user.age, favoriteNumber = user.favoriteNumber)
        userService.addUser(user)
    }

    @GetMapping(value = "/{name}")
    fun getUser(@PathVariable("name") name: String): User? = userService.findByName(name)

}