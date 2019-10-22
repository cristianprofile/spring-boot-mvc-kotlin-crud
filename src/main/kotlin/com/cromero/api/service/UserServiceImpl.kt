package com.cromero.api.service

import com.cromero.api.CustomProperties
import com.cromero.api.repository.UserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl (val userRepository: UserRepository,val customProperties: CustomProperties) :UserService  {

    private val LOGGER = KotlinLogging.logger {}

    @Transactional(readOnly = true)
    override fun findAll(): List<User> =userRepository.findAll().map { User.createFromUserEntity(it) }

    @Transactional(readOnly = true)
    override fun findByName(name: String): User? = userRepository.findByName(name)?.let { User.createFromUserEntity(it) }

    @Transactional
    override fun addUser(user: User): User {
        LOGGER.info("Trying to create user $user")
        val userEntity = user.convertToUserEntity()
        val savedEntity = userRepository.save(userEntity)
        LOGGER.info("User $savedEntity  was successfully created")
        return User.createFromUserEntity(savedEntity)
    }
}
