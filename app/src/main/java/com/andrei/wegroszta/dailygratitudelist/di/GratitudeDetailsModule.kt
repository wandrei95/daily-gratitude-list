package com.andrei.wegroszta.dailygratitudelist.di

import com.andrei.wegroszta.dailygratitudelist.db.GratitudeDao
import com.andrei.wegroszta.dailygratitudelist.details.data.GratitudeDetailsRepository.GratitudeDetailsLocalDataSource
import com.andrei.wegroszta.dailygratitudelist.details.data.GratitudeDetailsRepository.GratitudeDetailsRemoteDataSource
import com.andrei.wegroszta.dailygratitudelist.details.io.local.RoomGratitudeDetailsLocalDataSource
import com.andrei.wegroszta.dailygratitudelist.details.io.remote.DummyGratitudeDetailsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GratitudeDetailsModule {
//    @Provides
//    @Singleton
//    fun provideGratitudeDetailsLocalDataSource(retrofit: Retrofit): GratitudeDetailsService =
//        retrofit.create(GratitudeDetailsService::class.java)
//
//    @Provides
//    @Singleton
//    fun provideGratitudeDetailsRemoteDataSource(service: GratitudeDetailsService): GratitudeDetailsRemoteDataSource =
//        RetrofitGratitudeDetailsRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideGratitudeDetailsRemoteDataSource(): GratitudeDetailsRemoteDataSource =
        DummyGratitudeDetailsRemoteDataSource()

    @Singleton
    @Provides
    fun provideGratitudeDetailsLocalDataSource(dao: GratitudeDao): GratitudeDetailsLocalDataSource =
        RoomGratitudeDetailsLocalDataSource(dao)
}
