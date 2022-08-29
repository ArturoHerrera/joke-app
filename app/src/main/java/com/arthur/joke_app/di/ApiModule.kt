package com.arthur.joke_app.di

import com.arthur.joke_app.data.remote.api.JokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideJokeService(retrofit: Retrofit): JokeApi = retrofit.create(JokeApi::class.java)
}