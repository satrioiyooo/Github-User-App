package com.example.submissionfund2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {
    @Insert
    fun insertFav(favoriteUser: FavoriteUser)

    @Query("SELECT * FROM Favo")
    fun getFav():LiveData<List<FavoriteUser>>

    @Query("SELECT COUNT(*) FROM Favo WHERE Favo.id = :id")
    fun checkFav(id: Int): Int

    @Query("DELETE FROM Favo WHERE Favo.id = :id")
    fun delFav(id: Int): Int
}