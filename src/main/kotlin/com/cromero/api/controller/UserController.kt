package com.cromero.api.controller

import com.cromero.api.service.User
import com.cromero.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

//TODO Use try monad of Arrow https://arrow-kt.io/docs/arrow/core/try/

@RestController
@RequestMapping("/user")
class UserController (val userService: UserService) {

    @GetMapping
    fun findAll()=userService.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user: com.cromero.api.controller.User)= userService.addUser(user.toUserModel())


    @GetMapping(value = "/{name}")
    fun getUser(@PathVariable("name") name: String): User? = userService.findByName(name)

}