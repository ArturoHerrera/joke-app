package com.arthur.joke_app.di

import com.arthur.joke_app.data.remote.api.JokeApi
import com.arthur.joke_app.data.repository.JokeRepositoryRepository
import com.arthur.joke_app.data.repository.remote_data_source.JokeRetrofitRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesBankReferencesRepository(
        jokeApi: JokeApi
    ): JokeRepositoryRepository = JokeRepositoryRepository(
        jokeRemoteDS = JokeRetrofitRemoteDataSource(
            jokeApi = jokeApi
        )
    )

}