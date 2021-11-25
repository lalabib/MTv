package com.lalabib.project.mtv.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lalabib.project.mtv.data.local.entity.MovieEntity
import com.lalabib.project.mtv.data.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MtvDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: MtvDatabase? = null

        fun getInstance(context: Context): MtvDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MtvDatabase::class.java,
                    "Mtv.db"
                ).build()
            }
    }
    abstract fun mtvDao(): MtvDao
}