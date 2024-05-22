package com.example.submissionfund2.fav

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.submissionfund2.data.response.UserResponse
import com.example.submissionfund2.databinding.ItemUserBinding
import com.example.submissionfund2.detail.DetailActivity

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.UserViewHolder>() {
    private val data =  ArrayList<UserResponse>()

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = data[position]
                    val intent = Intent(binding.root.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID,item.id)
                    intent.putExtra(DetailActivity.EXTRA_PHOTO, item.avatar_url)
                    intent.putExtra(DetailActivity.EXTRA_USERNAME, item.login)
                    binding.root.context.startActivity(intent)
                }
            }
        }

        fun bind(item: UserResponse) {
            binding.image.load(item.avatar_url) {
                transformations(CircleCropTransformation())
            }
            binding.username.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users : ArrayList<UserResponse>){
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}