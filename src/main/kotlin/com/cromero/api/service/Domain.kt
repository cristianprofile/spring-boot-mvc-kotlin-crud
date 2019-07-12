package com.cromero.api.service

import com.cromero.api.repository.UserEntity

data class User (var id: Long? = null,val name:String, val age:Int,val favoriteNumber: String)
{
    internal fun toUserEntity() =UserEntity(
            id=id,
            name = name,
            age = age,
            favoriteNumber = favoriteNumber
    )
}

data class Pet (val name:String,val age:Int)