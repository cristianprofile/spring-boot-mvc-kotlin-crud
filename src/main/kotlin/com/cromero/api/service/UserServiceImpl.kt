package com.cromero.api.service

import com.cromero.api.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl (val userRepository: UserRepository) :UserService  {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java.name)
    }
    @Transactional(readOnly = true)
    override fun findAll(): List<User> =userRepository.findAll().map { User.createFromUserEntity(it) }

    @Transactional(readOnly = true)
    override fun findByName(name: String): User? = userRepository.findByName(name)?.let { User.createFromUserEntity(it) }

    @Transactional
    override fun addUser(user: User): User {
        LOGGER.info("Trying to create user $user")
        val userEntity = user.convertToUserEntity()
        val savedEntity = userRepository.save(userEntity)
        //TODO CREATE A MAPPER FROM POJO TO DATAMODEL
        LOGGER.info("User $savedEntity was was successfully created")
        return User.createFromUserEntity(savedEntity)
    }
}