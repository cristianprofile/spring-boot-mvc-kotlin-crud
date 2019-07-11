package com.cromero.api.controller

data class User (val name:String, val age:Int,val favoriteNumber: String)
{
    internal fun toUserModel() = com.cromero.api.service.User(
            name = name,
            age = age,
            favoriteNumber = favoriteNumber
    )

}