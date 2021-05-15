package com.example.mechinetest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.mechinetest.network.Result
import com.google.gson.Gson

@Entity(tableName = "movie")
data class MovieModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    val id: Int,

    @ColumnInfo(name = "result")
    var result: List<Result>,

    @ColumnInfo(name = "page_num")
    var page_num: Int
   ) {
    class ResultTypeConverter {
        @TypeConverter
        fun listTojson(value: List<Result>?) = Gson().toJson(value)

        @TypeConverter
        fun jsontoArrayList(value: String) =
            Gson().fromJson(value, Array<Result>::class.java).toList()
    }
}


