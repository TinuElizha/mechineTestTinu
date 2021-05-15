package com.example.mechinetest.room

import android.content.Context
import androidx.room.*
import com.example.mechinetest.model.MovieModel
import com.example.mechinetest.network.Result

@Database(entities = [MovieModel::class],version = 1)
@TypeConverters(MovieModel.ResultTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun loginDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDataseClient(context: Context) : MovieDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, MovieDatabase::class.java, "MovieDatabase")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}