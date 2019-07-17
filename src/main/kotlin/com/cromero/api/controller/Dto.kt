package com.cromero.api.controller

import com.cromero.api.service.Color


data class User (var id: Long? = null,val name:String, val age:Int,val favoriteNumber: String)
{
    internal fun toUserModel() = com.cromero.api.service.User(
            id=id,
            name = name,
            age = age,
            favoriteNumber = favoriteNumber,
            color = Color.assignColorPerAge(age)
    )

}

data class Error(val errorCode:String,val errorDescription:String)
