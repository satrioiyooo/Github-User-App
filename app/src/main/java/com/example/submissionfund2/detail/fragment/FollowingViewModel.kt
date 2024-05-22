package com.example.submissionfund2.detail.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submissionfund2.data.response.ItemsItem
import com.example.submissionfund2.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel(){
    val listFollowing = MutableLiveData<ArrayList<ItemsItem>>()


    fun setListFollowing (username : String){
        ApiConfig.getApiService()
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<ItemsItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ItemsItem>>,
                    response: Response<ArrayList<ItemsItem>>
                ) {
                    if (response.isSuccessful){
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                    Log.d("FollowingViewModel", "Error")
                }
            })
    }
    fun getListFollowing(): LiveData<ArrayList<ItemsItem>> {
        return listFollowing
    }
}