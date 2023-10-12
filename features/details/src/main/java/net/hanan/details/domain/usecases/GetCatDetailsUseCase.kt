package net.hanan.details.domain.usecases

import kotlinx.coroutines.flow.Flow
import net.hanan.core.domain.model.Cat
import net.hanan.core.domain.repository.CatRepository

class GetCatDetailsUseCase(
    private val repository: CatRepository
) {
    suspend operator fun  invoke(id: String): Flow<Cat> = repository.getCat(id = id)
}