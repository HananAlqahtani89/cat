package net.hanan.details.presenntation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import net.hanan.details.domain.usecases.CatDetailsUseCases
import javax.inject.Inject

@HiltViewModel
class CatDetailsViewModel
@Inject constructor(
    private val useCase: CatDetailsUseCases
) : ViewModel() {

    var state by mutableStateOf(CatDetailsState())
        private set

    fun onEvent(event: CatDetailsEvent) {
        when (event) {
            is CatDetailsEvent.GetCat -> getCat(event.id)
        }
    }

    private fun getCat(id: String) {
        viewModelScope.launch {
            useCase.getCatDetailsUseCase(id)
                .catch { }
                .collect {
                    state = state.copy(cat = it)
                }
        }
    }
}