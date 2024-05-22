package com.example.submissionfund2.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.submissionfund2.R
import com.example.submissionfund2.databinding.ActivityDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {


    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


        val username = intent.getStringExtra(EXTRA_USERNAME)
        val name = intent.getStringExtra(EXTRA_NAME)
        val photo = intent.getStringExtra(EXTRA_PHOTO)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val url= intent.getStringExtra(EXTRA_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        showLoading(true)
        if (username != null){
            viewModel.setUserDetail(username)
            viewModel.getUserDetail().observe(this){
                if (it != null){
                    binding.apply {
                        textView.text = it.name
                        textView2.text = it.login
                        Followers.text = resources.getString(R.string.follower, it.followers)
                        Following.text = resources.getString(R.string.following, it.following)
                        binding.imageView.load(it.avatarUrl) {
                            transformations(CircleCropTransformation())
                        }
                        floatingActionButton2.setOnClickListener{
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.putExtra(Intent.EXTRA_TEXT,("$url"))
                            intent.type = "text/plain"
                            startActivity(Intent.createChooser(intent,"Share"))
                        }
                        showLoading(false)
                    }
                }
            }
        }



        var isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    isChecked = count > 0
                    binding.floatingActionButton.setImageResource(if (isChecked) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                if (id != null && username != null && photo != null ) {
                    viewModel.addFavorite(id, username, photo)
                    Toast.makeText(this, "Berhasil Ditambahkan Ke Favorit", Toast.LENGTH_SHORT).show()
                }
            } else {
                viewModel.deleteFav(id)
                Toast.makeText(this, "Berhasil Dihapus Dari Favorit", Toast.LENGTH_SHORT).show()
            }
            binding.floatingActionButton.setImageResource(if (isChecked) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
        }




        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewpager.adapter = sectionPagerAdapter
            tabLayout.setupWithViewPager(viewpager)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.Detail)
    }


    companion object{
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"

    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }
}