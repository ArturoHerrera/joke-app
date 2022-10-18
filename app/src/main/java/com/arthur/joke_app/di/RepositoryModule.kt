package com.arthur.joke_app.di

import com.arthur.joke_app.data.remote.api.JokeApi
import com.arthur.joke_app.data.repository.joke_repository.repositorys.JokeGoodRepository
import com.arthur.joke_app.data.repository.joke_repository.remote_data_source.JokeRetrofitRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideDispatchersIO() : CoroutineDispatcher = Dispatchers.IO

    @ViewModelScoped
    @Provides
    fun providesJokeGoodRepository(
        jokeApi: JokeApi
    ): JokeGoodRepository = JokeGoodRepository(
        dispatcher = provideDispatchersIO(),
        jokeRemoteDS = JokeRetrofitRemoteDataSource(
            jokeApi = jokeApi
        )
    )

}
