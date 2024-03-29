package com.example.dailyinform.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailyinform.bean.ClassifyBean
import com.example.dailyinform.bean.CollectionDetailBean
import com.example.dailyinform.bean.DetailBean

@Database(entities = [ClassifyBean::class, DetailBean::class,CollectionDetailBean::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun classifyDao():ClassifyDao
    abstract fun detailDao():DetailDao
    abstract fun collectionDetailDao():CollectionDetailDao

    companion object{
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "daily_inform").build()
        }
    }

}