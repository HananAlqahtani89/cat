package net.hanan.cat.local.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import net.hanan.cat.local.entity.BreedsEntity
import net.hanan.cat.local.entity.CatInfoEntity

data class CatWithBreeds(
    @Embedded
    val cat: CatInfoEntity,
    @Relation(
        parentColumn = "catId",
        entityColumn = "breedId",
        associateBy = Junction(CatBreedCrossRef::class)
    )
    val breeds: List<BreedsEntity>
)