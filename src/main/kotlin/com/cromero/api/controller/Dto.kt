package com.cromero.api.controller

import com.cromero.api.service.Color


data class User (val id: Long? = null,val name:String, val age:Int,val favoriteNumber: String,val color: Color)
{
    internal fun toUserModel() = com.cromero.api.service.User(
            id=id,
            name = name,
            age = age,
            favoriteNumber = favoriteNumber,
            color = Color.assignColorPerAge(age)
    )


    companion object {
        fun createFromUserModel(user: com.cromero.api.service.User) = User(
                id = user.id,
                name = user.name,
                age = user.age,
                favoriteNumber = user.favoriteNumber,
                color = Color.assignColorPerAge(user.age)
        )
    }

}


data class ResponseDTO (val status: Int,val data: Any)


class ResponseErrorDTO(val message: String,
                       val code: String)


data class Error(val errorCode:String,val errorDescription:String)


