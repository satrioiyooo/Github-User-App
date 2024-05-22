package com.example.submissionfund2.data.response

import com.google.gson.annotations.SerializedName

data class GithubResponse(

	@field:SerializedName("items")
	val items: ArrayList<ItemsItem>
)

data class ItemsItem(
	val id : Int,
	var login: String,
	var avatar_url: String,
	var name: String,
	val html_url: String,
)
