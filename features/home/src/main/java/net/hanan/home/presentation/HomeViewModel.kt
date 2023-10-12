package net.hanan.home.presentation

import android.util.Log
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

    init {
        getCats()
    }

    private fun getCats() {
        viewModelScope.launch {
            useCases.getCatsUseCase()
                .catch {
                    Log.e("hannan", "getCats: " + it.localizedMessage)
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
                            Log.e("hannan", "getCats: " + res.data)

                            state.copy(
                                loading = false,
                                error = false,
                                cat = res.data ?: emptyList()
                            )
                        }

                        is Resource.Error -> {
                            Log.e("hannan", "getCats: " + res.error)

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