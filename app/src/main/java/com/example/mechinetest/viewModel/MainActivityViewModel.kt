package com.example.mechinetest.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mechinetest.MoviePagingSource
import com.example.mechinetest.model.MovieModel
import com.example.mechinetest.network.Result
import com.example.mechinetest.network.RetroInstance
import com.example.mechinetest.network.RetroService
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel: ViewModel() {

    lateinit var retroService: RetroService
    var liveDataresult: MovieModel? = null
    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getListData(context: Context): Flow<PagingData<Result>> {
        var  isNetwork =isOnline(context)
        return Pager (config = PagingConfig(pageSize = 19, maxSize = 100),
        pagingSourceFactory = { MoviePagingSource(retroService,context,isNetwork) }).flow.cachedIn(viewModelScope)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

}