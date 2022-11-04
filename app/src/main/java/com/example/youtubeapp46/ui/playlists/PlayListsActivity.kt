package com.example.youtubeapp46.ui.playlists

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapp46.databinding.ActivityMainBinding
import com.example.youtubeapp46.model.Item
import com.example.youtubeapp46.base.BaseActivity
import com.example.youtubeapp46.network.NetworkStatus
import com.example.youtubeapp46.network.NetworkStatusHelper
import com.example.youtubeapp46.ui.playlists.adapters.PlaylistsAdapter
import com.example.youtubeapp46.ui.playlists_detail.PlaylistDetailActivity

class PlayListsActivity : BaseActivity<ActivityMainBinding, PlaylistsViewModel>() {

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    companion object {
        const val plaPDAId = "ID"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initViewModel() {
        viewModel = ViewModelProvider(this)[PlaylistsViewModel::class.java]
        viewModel.playLists().observe(this) {
            initRecyclerView(it.items)
        }
    }

    override fun initViews() {
    }

    private fun initRecyclerView(list : List<Item>) {
        binding.recyclerView.adapter = PlaylistsAdapter(list , this::onClick)
    }

    override fun isConnection() {
        checkInternet()
    }

    private fun checkInternet() {
        NetworkStatusHelper(this).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.networkLayout.root.visibility = View.GONE
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.networkLayout.root.visibility = View.VISIBLE
            }
        }

    }

    private fun onClick(channelId: String) {
        Intent(this, PlaylistDetailActivity::class.java).apply {
            putExtra(plaPDAId , channelId)
            startActivity(this)
        }
    }
}