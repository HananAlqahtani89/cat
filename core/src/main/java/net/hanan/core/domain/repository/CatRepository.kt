package net.hanan.core.domain.repository

import kotlinx.coroutines.flow.Flow
import net.hanan.core.domain.model.CatInfo
import net.hanan.core.util.Resource

interface CatRepository {
    suspend fun getCats(): Flow<Resource<List<CatInfo>>>
}