package com.example.submissionfund2.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissionfund2.R
import com.example.submissionfund2.UserAdapter
import com.example.submissionfund2.databinding.FragmentFollowBinding
import com.example.submissionfund2.detail.DetailActivity

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding? = null

    private val binding get() =  _binding!!

    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        val root : View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)


        adapterHandler()

        dataFollowersHandler()
    }

    private fun dataFollowersHandler() {
        showLoading(true)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner){
            if (it != null ){
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    private fun adapterHandler() {
        adapter = UserAdapter()
        binding.rvFollow.setHasFixedSize(true)
        binding.rvFollow.layoutManager = LinearLayoutManager(activity)
        binding.rvFollow.adapter = adapter
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    companion object {
    }
}