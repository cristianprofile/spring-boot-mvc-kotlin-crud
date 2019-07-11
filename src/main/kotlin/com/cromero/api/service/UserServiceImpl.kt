package com.cromero.api.service

import org.springframework.stereotype.Service

@Service
class UserServiceImpl :UserService {


    private val users =  mutableListOf( User(name = "pepe",age = 22,favoriteNumber = "3"),
            User(name = "manolo",age = 25,favoriteNumber = "8"),
            User(name = "juan",age = 27,favoriteNumber = "9"))

    override fun findAll(): List<User> =users

    override fun findByName(name: String): User? = users.firstOrNull {it.name.contentEquals(name)}

    override fun addUser(user: User): User {
        users.add(user)
        return user
    }
}