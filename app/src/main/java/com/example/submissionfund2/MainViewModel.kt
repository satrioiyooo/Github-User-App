package com.example.submissionfund2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissionfund2.data.response.GithubResponse
import com.example.submissionfund2.data.response.ItemsItem
import com.example.submissionfund2.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<ItemsItem>>()
    fun setSearch(query: String) {
        ApiConfig.getApiService().getSearch(query).enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.d("MainViewModel", "Error")
            }


        })

    }

    fun getSearch(): LiveData<ArrayList<ItemsItem>> {
        return listUser
    }
}