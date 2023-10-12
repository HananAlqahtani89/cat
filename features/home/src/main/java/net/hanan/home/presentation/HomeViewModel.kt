package net.hanan.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import net.hanan.core.util.Resource
import net.hanan.home.domin.usecases.HomeUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val useCases: HomeUseCases
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    fun onEvent(event: HomeEvent){
        when(event) {
            is HomeEvent.GetCats-> getCats()
        }
    }

    private fun getCats() {
        viewModelScope.launch {
            useCases.getCatsUseCase()
                .catch {
                    state = state.copy(
                        loading = false,
                        error = true
                    )
                }
                .collect { res ->
                    state = when (res) {
                        is Resource.Loading -> {
                            state.copy(
                                loading = true,
                                error = false
                            )
                        }

                        is Resource.Success -> {
                            state.copy(
                                loading = false,
                                error = false,
                                cat = res.data ?: emptyList()
                            )
                        }

                        is Resource.Error -> {
                            state.copy(
                                loading = false,
                                error = true
                            )
                        }
                    }
                }
        }
    }
}