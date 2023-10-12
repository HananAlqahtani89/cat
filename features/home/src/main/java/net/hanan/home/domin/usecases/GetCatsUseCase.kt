package net.hanan.home.domin.usecases

import kotlinx.coroutines.flow.Flow
import net.hanan.core.domain.model.CatInfo
import net.hanan.core.domain.repository.CatRepository
import net.hanan.core.util.Resource

class GetCatsUseCase(
    private val repository: CatRepository
) {
    suspend operator fun  invoke  (): Flow<Resource<List<CatInfo>>> = repository.getCats()
}