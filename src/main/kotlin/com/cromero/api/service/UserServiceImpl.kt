package com.cromero.api.service

import com.cromero.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (val userRepository: UserRepository) :UserService  {


    override fun findAll(): List<User> =userRepository.findAll().map { it.conveToUserModel() }

    override fun findByName(name: String): User? = userRepository.findByName(name)?.conveToUserModel()


    override fun addUser(user: User): User {
        val userEntity = user.toUserEntity()
        userRepository.save(userEntity)
        return user
    }
}