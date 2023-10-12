package net.hanan.cat.repository

import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.hanan.cat.local.CatDatabase
import net.hanan.cat.local.entity.relations.CatWithBreeds
import net.hanan.cat.mapper.toCat
import net.hanan.cat.mapper.toCatBreedCrossRef
import net.hanan.cat.mapper.toCatInfo
import net.hanan.cat.mapper.toCatWithBreeds
import net.hanan.cat.remote.CatsApi
import net.hanan.core.domain.model.Cat
import net.hanan.core.domain.model.CatInfo
import net.hanan.core.domain.repository.CatRepository
import net.hanan.core.util.Resource
import net.hanan.core.util.networkBoundResource

class CatRepositoryImpl(
    private val api: CatsApi,
    private val db: CatDatabase
) : CatRepository {
    override suspend fun getCats(): Flow<Resource<List<CatInfo>>> =
        networkBoundResource(
            query = {
                db.dao.getAllCats().map { cats -> cats.map { it.toCatInfo() } }
            },
            shouldFetch = {
                it.isEmpty()
            },
            fetch = {
                api.getCats(limit = 300, key = CatsApi.API_KEY, breedIds = "beng,abys")
            },
            saveFetchResult = { catsDto ->
                db.withTransaction {
                    insertCatList(catsDto.map { it.toCatWithBreeds() })
                }
            },
        )

    private suspend fun insertCatList(
        catWithBreedsList: List<CatWithBreeds>
    ) {
        for (item in catWithBreedsList) {
            if ((item.cat.height > item.cat.width) && item.cat.height < 2500) {
                db.dao.insertCat(item.cat)
                for (breed in item.breeds) {
                    db.dao.insertBreed(breed)
                    db.dao.insertCatBreedsCrossRef(item.cat.catId.toCatBreedCrossRef(breed.breedId))
                }
            }
        }
    }

    override suspend fun getCat(id: String): Flow<Cat> =
        db.dao.getBreedsOfCat(catId = id).map { it.toCat() }
}