package net.hanan.details.presenntation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hanan.details.domain.usecases.CatDetailsUseCases
import javax.inject.Inject

@HiltViewModel
class CatDetailsViewModel
@Inject constructor(
    private val useCase: CatDetailsUseCases
) : ViewModel() {


}