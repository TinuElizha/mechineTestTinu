package com.example.mechinetest.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "result")
data class Result(
    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String,
    @ColumnInfo(name = "genre_ids")
    var genre_ids: List<Int>,
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "original_language")
    var original_language: String,

    @ColumnInfo(name = "original_title")
    var original_title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "poster_path")
    var poster_path: String,

    @ColumnInfo(name = "release_date")
    var release_date: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "video")
    var video: Boolean,
    @ColumnInfo(name = "page_num")
    var vote_average: Double,

    @ColumnInfo(name = "page_num")
    var vote_count: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
)