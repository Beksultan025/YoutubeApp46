package com.example.youtubeapp46.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapp46.BuildConfig.API_KEY
import com.example.youtubeapp46.`object`.Constant
import com.example.youtubeapp46.model.PlayLists
import com.example.youtubeapp46.base.BaseViewModel
import com.example.youtubeapp46.remote.ApiService
import com.example.youtubeapp46.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playLists() : LiveData<PlayLists> {
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<PlayLists>  {
        val data = MutableLiveData<PlayLists>()

        apiService.getPlaylists(Constant.part , Constant.channelId , API_KEY , 50).enqueue(object: Callback<PlayLists> {
            override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<PlayLists>, t: Throwable) {

            }

        })
        return data
    }
}