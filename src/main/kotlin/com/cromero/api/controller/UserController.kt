package com.cromero.api.controller

import com.cromero.api.service.Color
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


    //TODO rewrite using exceptions of arrow's  or Result.
    @GetMapping(value = ["color/{name}"])
    fun getColorTextByName(@PathVariable("name") name: String) =
        userService.findByName(name)?.let {
            val createFromUserModel = User.createFromUserModel(it)
            when (val color=createFromUserModel.color) {
                Color.BLACK->  "Your black color description is ${color.description}"
                Color.RED -> "Your red color description is ${color.description}"
                Color.GREEN -> "Your green color description is ${color.description}"
                Color.BLUE -> "Your blue color description is ${color.description}"
            }
        }?: throw UserNotFoundException("user not found")


}

