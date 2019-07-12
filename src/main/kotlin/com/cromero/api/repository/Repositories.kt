package com.cromero.api.repository

import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByName(name:String): UserEntity?
    fun findByAge(age:Int): UserEntity?

}

interface PetRepository : CrudRepository<PetEntity, Long> {
    fun findByName(name:String): PetEntity?
}

