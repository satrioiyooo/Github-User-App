package com.example.submissionfund2.fav

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submissionfund2.database.FavDao
import com.example.submissionfund2.database.FavoriteDatabase
import com.example.submissionfund2.database.FavoriteUser

class FavoriteViewModel(application: Application): AndroidViewModel(application){
    private var favDao : FavDao? = null
    private var favoritedatabase : FavoriteDatabase? = null
    init {
        favoritedatabase = FavoriteDatabase.getDatabase(application)
        favDao = favoritedatabase?.favDao()
    }

    fun getUserFav():LiveData<List<FavoriteUser>>{
        return favDao?.getFav()!!
    }
}