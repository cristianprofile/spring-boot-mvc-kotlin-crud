package com.cromero.api.repository

import com.cromero.api.service.User
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class UserEntity (@Id @GeneratedValue var id: Long? = null, val name:String, val age:Int, val favoriteNumber: String)
{
    internal fun convertToUserModel() = User(
            id=id,
            name = name,
            age = age,
            favoriteNumber = favoriteNumber
    )
}

@Entity
data class PetEntity (@Id @GeneratedValue var id: Long? = null, val name:String, val age:Int)