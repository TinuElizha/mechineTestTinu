package com.example.mechinetest

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mechinetest.network.Result
import com.example.mechinetest.network.RetroService
import com.example.mechinetest.repository.MovieRepository

class MoviePagingSource(val apiService: RetroService,context: Context,isNetwork:Boolean): PagingSource<Int, Result>() {

    val context:Context =context
    val isNetwork:Boolean =isNetwork

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        return state.anchorPosition

    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {

            if (isNetwork)
            {
                val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

                val response = apiService.getDataFromAPI("9555e26950e57c80d2eeff35141f574d","en-US","popularity.desc",
                    false,false,nextPage,"flatrate")

                MovieRepository.insertData(context,response.results,response.page)


                var nextPageNumber: Int? = response.page+1

                var prevPageNumber: Int? = response.page-1
                LoadResult.Page(data = response.results,
                    prevKey = prevPageNumber,
                    nextKey = nextPageNumber)

            }
            else{
                val nextPage: Int = params.key ?: FIRST_PAGE_INDEX

                val response =MovieRepository.getAllRecords(context,nextPage)
                var nextPageNumber: Int? = response.page_num+1

                var prevPageNumber: Int? = response.page_num-1
                LoadResult.Page(data = response.result,
                    prevKey = prevPageNumber,
                    nextKey = nextPageNumber)



            }
           /* var nextPageNumber: Int? = response1.page_num+1

            var prevPageNumber: Int? = response.page-1
            LoadResult.Page(data = response.results,
                    prevKey = prevPageNumber,
                    nextKey = nextPageNumber)*/
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }



}