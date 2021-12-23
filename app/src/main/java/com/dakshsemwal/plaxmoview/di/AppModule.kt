package com.dakshsemwal.plaxmoview.di

import com.dakshsemwal.plaxmoview.common.Constants.BASE_URL
import com.dakshsemwal.plaxmoview.data.remote.MovieDataBaseApi
import com.dakshsemwal.plaxmoview.data.repository.MovieRepositoryImpl
import com.dakshsemwal.plaxmoview.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieDataBaseApi): MovieRepository =
        MovieRepositoryImpl(api = api)


    /**
     * Provides Api By Using Retrofit Dependency
     */
    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieDataBaseApi =
        retrofit.create(MovieDataBaseApi::class.java)

    /**
     * Provides OKHttp client to be used across the app
     */
    @Singleton
    @Provides
    fun provideOKHttp(
        httpLogger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(1, TimeUnit.MINUTES)
            writeTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            addInterceptor(httpLogger)
        }.build()
    }

    /**
     * Provides HTTP request logger to be used across the app
     */
    @Singleton
    @Provides
    fun provideOKHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(BASE_URL)
        }.build()
    }

}