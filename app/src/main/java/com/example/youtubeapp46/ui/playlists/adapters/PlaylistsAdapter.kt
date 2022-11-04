package com.example.youtubeapp46.ui.playlists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp46.R
import com.example.youtubeapp46.databinding.ItemListsBinding
import com.example.youtubeapp46.extensions.load
import com.example.youtubeapp46.model.Item

class PlaylistsAdapter(private var list : List<Item> , private val onClick: (pos : String) -> Unit?) : RecyclerView.Adapter<PlaylistsAdapter.MainViewHolder>() {


    inner class MainViewHolder(private val binding: ItemListsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(items: Item) {
            val imageUrl = items.snippet.thumbnails.default.url
            val playlistsTitle = items.snippet.title
            val numberOfVideos = items.contentDetails.itemCount
            val displayNumber = String.format(itemView.context.getString(R.string.video_series) , numberOfVideos)

            binding.imagePlaylists.load(imageUrl)
            binding.tvDesc.text = playlistsTitle
            binding.tvSer.text = displayNumber

            binding.root.setOnClickListener {
                onClick(items.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemListsBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}