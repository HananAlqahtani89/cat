package net.hanan.cat.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import net.hanan.cat.local.entity.BreedsEntity
import net.hanan.cat.local.entity.CatInfoEntity
import net.hanan.cat.local.entity.relations.BreedWithCats
import net.hanan.cat.local.entity.relations.CatBreedCrossRef
import net.hanan.cat.local.entity.relations.CatWithBreeds

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(catInfoEntity: CatInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreed(breedsEntity: BreedsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatBreedsCrossRef(catBreedCrossRef: CatBreedCrossRef)

    @Transaction
    @Query("SELECT * FROM breed WHERE breedId= :breedId")
    suspend fun getCatsOfBreed(breedId: String): List<BreedWithCats>

    @Transaction
    @Query("SELECT * FROM cat_info WHERE catId= :catId")
    suspend fun getBreedsOfCat(catId: String): List<CatWithBreeds>

    @Transaction
    @Query("SELECT * FROM cat_info")
    fun getAllCats(): Flow<List<CatInfoEntity>>
}