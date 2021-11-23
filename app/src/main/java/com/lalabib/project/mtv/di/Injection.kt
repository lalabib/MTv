package com.lalabib.project.mtv.di

import com.lalabib.project.mtv.data.MtvRepository
import com.lalabib.project.mtv.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MtvRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MtvRepository.getInstance(remoteDataSource)
    }
}