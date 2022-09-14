package com.arthur.joke_app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.joke_app.data.repository.JokeRepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jokeRepository: JokeRepositoryRepository
) : ViewModel() {

    private val vmUiState = MutableStateFlow(HomeUiState())

    val uiState = vmUiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmUiState.value
    )

    init {
        getJoke()
    }

    fun getJoke() =
        viewModelScope.launch {
            vmUiState.update { it.copy(loading = true) }
            jokeRepository.getJoke().collectLatest { result ->
                result.first?.let { safeErrorMsg ->
                    vmUiState.update {
                        it.copy(
                            loading = false,
                            errorMsg = safeErrorMsg,
                            joke = null
                        )
                    }
                } ?: run {
                    result.second?.let { safeJoke ->
                        vmUiState.update {
                            it.copy(
                                loading = false,
                                errorMsg = null,
                                joke = safeJoke
                            )
                        }
                    }
                }
            }
        }

}