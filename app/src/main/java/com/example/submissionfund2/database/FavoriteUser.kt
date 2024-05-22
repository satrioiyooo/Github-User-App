package com.example.submissionfund2.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "Favo")
data class FavoriteUser(
    @PrimaryKey
    val id : Int,
    var login: String,
    var avatar_url: String,
):Serializable