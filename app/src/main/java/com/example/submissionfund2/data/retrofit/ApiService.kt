package com.example.submissionfund2.data.retrofit

import com.example.submissionfund2.BuildConfig
import com.example.submissionfund2.data.response.DetailResponse
import com.example.submissionfund2.data.response.GithubResponse
import com.example.submissionfund2.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

const val Token = BuildConfig.TOKEN
interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token $Token")
    fun getSearch(
        @Query ("q") query: String,
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $Token")
    fun getDetailUser(
        @Path("username") username: String,
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token $Token")
    fun getFollowers(
        @Path("username") username: String,
    ): Call<ArrayList<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token $Token")
    fun getFollowing(
        @Path("username") username: String,
    ): Call<ArrayList<ItemsItem>>
}

