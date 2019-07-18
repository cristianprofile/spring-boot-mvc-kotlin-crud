package com.cromero.api.repository

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class UserEntity (@Id @GeneratedValue var id: Long? = null, val name:String, val age:Int, val favoriteNumber: String)

@Entity
data class PetEntity (@Id @GeneratedValue var id: Long? = null, val name:String, val age:Int)