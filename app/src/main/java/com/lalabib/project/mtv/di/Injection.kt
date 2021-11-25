package com.lalabib.project.mtv.di

import android.content.Context
import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.local.LocalDataSource
import com.lalabib.project.mtv.data.local.room.MtvDatabase
import com.lalabib.project.mtv.data.remote.RemoteDataSource
import com.lalabib.project.mtv.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MtvRepository {

        val database = MtvDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.mtvDao())
        val appExecutor = AppExecutors()

        return MtvRepository.getInstance(remoteDataSource, localDataSource, appExecutor)
    }
}