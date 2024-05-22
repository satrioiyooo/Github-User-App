package com.example.submissionfund2.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submissionfund2.data.response.DetailResponse
import com.example.submissionfund2.data.retrofit.ApiConfig
import com.example.submissionfund2.database.FavDao
import com.example.submissionfund2.database.FavoriteDatabase
import com.example.submissionfund2.database.FavoriteUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel (application: Application): AndroidViewModel(application) {
    val listUser = MutableLiveData<DetailResponse>()
    private var favDao : FavDao? = null
    private var favoritedatabase : FavoriteDatabase? = null
    init {
        favoritedatabase = FavoriteDatabase.getDatabase(application)
        favDao = favoritedatabase?.favDao()
    }

    fun setUserDetail(username : String) {
        ApiConfig.getApiService().getDetailUser(username).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("MainViewModel", "Error")
            }
        })

    }
    fun getUserDetail(): LiveData<DetailResponse> {
        return listUser
    }
    fun addFavorite(id : Int, username: String, avatar_url: String){
        CoroutineScope(Dispatchers.IO).launch {
            val userfav = FavoriteUser(
                id, username, avatar_url
            )
            favDao?.insertFav(userfav)

        }

    }
    fun checkUser(id : Int) = favDao?.checkFav(id)
    fun deleteFav(id : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            favDao?.delFav(id)
        }
    }
}