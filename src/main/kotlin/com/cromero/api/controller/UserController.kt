package com.cromero.api.controller

import com.cromero.api.service.UserNotFoundException
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
    fun addUser(@RequestBody user: User)= User.createFromUserModel(userService.addUser(user.toUserModel()))

    //TODO rewrite using exceptions of arrow's  or Result.
    @GetMapping(value = ["/{name}"])
    fun getUser(@PathVariable("name") name: String) =
        userService.findByName(name)?.let { User.createFromUserModel(it) }?: throw UserNotFoundException("user not found")

}

