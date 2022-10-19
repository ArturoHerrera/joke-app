package com.arthur.joke_app.di

import com.arthur.joke_app.data.repository.joke_repository.repositorys.GoodJokeRepository
import com.arthur.joke_app.data.repository.joke_repository.repositorys.JokeTasks
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InterfaceTasksModule {

    @Binds
    abstract fun providesJokeGoodRepository(
        goodJokeRepository: GoodJokeRepository
    ): JokeTasks

}