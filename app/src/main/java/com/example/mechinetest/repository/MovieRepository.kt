package com.example.mechinetest.repository

import android.content.Context
import android.service.media.MediaBrowserService
import androidx.lifecycle.LiveData
import com.example.mechinetest.model.MovieModel
import com.example.mechinetest.network.MovieDetailsResponse
import com.example.mechinetest.network.Result
import com.example.mechinetest.network.RetroInstance
import com.example.mechinetest.network.RetroService
import com.example.mechinetest.room.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class MovieRepository {

    companion object {
        lateinit var retroService: RetroService
        init {
            retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        }
        var movieDatabase: MovieDatabase? = null

        var movieTableModel: MovieModel? = null

        fun initializeDB(context: Context): MovieDatabase {
            return MovieDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, result: List<Result>, pageNum: Int) {

            movieDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = MovieModel(0, result, pageNum)
                movieDatabase!!.loginDao().InsertData(loginDetails)
            }

        }

        suspend fun getAllRecords(context: Context,nextPage:Int): MovieModel {

            movieDatabase = initializeDB(context)



            movieTableModel = movieDatabase!!.loginDao().getAllRecords(nextPage)
            return movieTableModel as MovieModel;

        }
        suspend fun getMovieDetails(id:Long):MovieDetailsResponse {
            return  retroService.getMovieDetails(id,"9555e26950e57c80d2eeff35141f574d","en-US")
        }


    }

}