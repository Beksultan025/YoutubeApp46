package com.example.youtubeapp46.ui.playlists_detail

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapp46.base.BaseActivity
import com.example.youtubeapp46.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapp46.network.NetworkStatus
import com.example.youtubeapp46.network.NetworkStatusHelper
import com.example.youtubeapp46.ui.playlists.PlayListsActivity

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding , PlaylistsDetailViewModel>() {

    override fun inflateViewBinding(): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

    override fun isConnection() {
        NetworkStatusHelper(this).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.networkLayout.root.visibility = View.GONE
            } else {
                binding.networkLayout.root.visibility = View.VISIBLE
            }
        }
    }
    override fun initViewModel() {
        viewModel = ViewModelProvider(this)[PlaylistsDetailViewModel::class.java]
    }

    override fun initViews() {
        val channelId = intent.getStringExtra(PlayListsActivity.plaPDAId)
        Toast.makeText(this, channelId , Toast.LENGTH_SHORT).show()
    }
}