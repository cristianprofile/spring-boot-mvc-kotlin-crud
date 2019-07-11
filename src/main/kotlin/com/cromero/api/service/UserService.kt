package com.cromero.api.service

interface UserService {

    fun findAll():List<User>
    fun findByName(name:String): User?
    fun addUser(user:User):User
}