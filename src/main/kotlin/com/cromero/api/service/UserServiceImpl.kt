package com.cromero.api.service

import com.cromero.api.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (val userRepository: UserRepository) :UserService  {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this.javaClass.name)
    }

    override fun findAll(): List<User> =userRepository.findAll().map { it.convertToUserModel() }

    override fun findByName(name: String): User? = userRepository.findByName(name)?.convertToUserModel()


    override fun addUser(user: User): User {
        LOGGER.info("Trying to create user $user")
        val userEntity = user.toUserEntity()
        val savedEntity = userRepository.save(userEntity)
        //TODO CREATE A MAPPER FROM POJO TO DATAMODEL
        LOGGER.info("User $savedEntity was was successfully created")
        return savedEntity.convertToUserModel()
    }
}