package com.andrei.wegroszta.dailygratitudelist.di

import com.andrei.wegroszta.dailygratitudelist.db.GratitudeDao
import com.andrei.wegroszta.dailygratitudelist.list.data.GratitudeListRepository.GratitudeListLocalDataSource
import com.andrei.wegroszta.dailygratitudelist.list.data.GratitudeListRepository.GratitudeListRemoteDataSource
import com.andrei.wegroszta.dailygratitudelist.list.io.local.RoomGratitudeListLocalDataSource
import com.andrei.wegroszta.dailygratitudelist.list.io.remote.DummyGratitudeListRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GratitudeListModule {
//    @Provides
//    @Singleton
//    fun provideGratitudeListService(retrofit: Retrofit): GratitudeListService =
//        retrofit.create(GratitudeListService::class.java)
//
//
//    @Provides
//    @Singleton
//    fun provideGratitudeListRemoteDataSource(service: GratitudeListService): GratitudeListRemoteDataSource =
//        RetrofitGratitudeListRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideGratitudeListRemoteDataSource(): GratitudeListRemoteDataSource =
        DummyGratitudeListRemoteDataSource()

    @Singleton
    @Provides
    fun provideGratitudeListLocalDataSource(dao: GratitudeDao): GratitudeListLocalDataSource =
        RoomGratitudeListLocalDataSource(dao)
}
