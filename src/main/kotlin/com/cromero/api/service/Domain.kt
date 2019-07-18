package com.cromero.api.service

import com.cromero.api.repository.UserEntity



enum class Color(val rgb: Int,val description:String) {
    RED(0xFF0000,"red color"),
    GREEN(0x00FF00,"green color"),
    BLUE(0x0000FF,"green color"),
    BLACK(0x0000FF,"black color");
    companion object {
        fun assignColorPerAge(age: Int) = when (age) {
            in 1..10 -> BLUE
            in 20..40 -> GREEN
            in 40..99 -> RED
            else -> BLACK
        }
    }
}

data class User (var id: Long? = null,val name:String, val age:Int,val favoriteNumber: String,val color: Color)
{
     fun convertToUserEntity() =UserEntity(
            id=id,
            name = name,
            age = age,
            favoriteNumber = favoriteNumber
    )


    companion object {
        fun createFromUserEntity(userEntity: UserEntity) = User(
                id = userEntity.id,
                name = userEntity.name,
                age = userEntity.age,
                favoriteNumber = userEntity.favoriteNumber,
                color = Color.assignColorPerAge(userEntity.age)
        )
    }


}

data class Pet (val name:String,val age:Int)