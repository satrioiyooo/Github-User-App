package com.example.submissionfund2.fav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionfund2.R
import com.example.submissionfund2.data.response.UserResponse
import com.example.submissionfund2.database.FavoriteUser
import com.example.submissionfund2.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding : ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)

        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        rvHandler()

        viewModel.getUserFav()?.observe(this){
            if (it != null){
                showLoading(true)
                val list = maplist(it)
                adapter.setList(list)
                showLoading(false)
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.Favorite)
    }


    private fun rvHandler() {
        adapter = FavoriteAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
            rvFav.setHasFixedSize(true)
            rvFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFav.adapter = adapter
        }
    }
    private fun maplist(users : List<FavoriteUser>): ArrayList<UserResponse>{
        val listUser = ArrayList<UserResponse>()
        for (user: FavoriteUser in users){
            val userMapped = UserResponse(
                user.id,
                user.login,
                user.avatar_url)
            listUser.add(userMapped)
        }
        return listUser
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }

}