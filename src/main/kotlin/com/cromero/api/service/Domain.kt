package com.cromero.api.service

import com.cromero.api.repository.UserEntity

enum class Color(val rgb: Int, val description: String) {
    RED(0xFF0000, "red color nice"),
    GREEN(0x00FF00, "green color ugly"),
    BLUE(0x0000FF, "blue color dirty"),
    BLACK(0x0000FF, "black color nice");

    companion object {
        // When expression is exhaustive, else is a must with integer value. It is better to use Enum or Sealed
        //classes instead if it would be possible because do not need else branch
        fun assignColorPerAge(age: Int) = when (age) {
            in Int.MIN_VALUE..0 -> BLACK
            in 1..10 -> BLUE
            in 20..40 -> GREEN
            in 40..99 -> RED
            in 100..Int.MAX_VALUE -> BLACK
            else -> BLACK
        }
    }
}

data class User(
    val id: Long? = null,
    val name: String,
    val age: Int,
    val favoriteNumber: String,
    val color: Color
) {
    fun convertToUserEntity() = UserEntity(
        id = id,
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

data class Pet(val name: String, val age: Int)





