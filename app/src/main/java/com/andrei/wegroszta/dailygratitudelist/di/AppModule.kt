package com.andrei.wegroszta.dailygratitudelist.di

import android.content.Context
import androidx.room.Room
import com.andrei.wegroszta.dailygratitudelist.BuildConfig
import com.andrei.wegroszta.dailygratitudelist.db.GratitudeDao
import com.andrei.wegroszta.dailygratitudelist.db.GratitudeDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.HEADERS
            })
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val converterFactory = json.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        GratitudeDatabase::class.java,
        "gratitude_db"
    ).build()

    @Singleton
    @Provides
    fun provideGratitudeDao(db: GratitudeDatabase): GratitudeDao = db.getGratitudeDao()
}
