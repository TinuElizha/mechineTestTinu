package com.example.mechinetest.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mechinetest.model.MovieModel
import com.example.mechinetest.network.Result

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(movieModel: MovieModel)

    @Query("Select * from movie where page_num= :nextPage")
    suspend fun getAllRecords(nextPage: Int) : MovieModel



}