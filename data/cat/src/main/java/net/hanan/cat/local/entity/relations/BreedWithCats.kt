package net.hanan.cat.local.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import net.hanan.cat.local.entity.BreedsEntity
import net.hanan.cat.local.entity.CatInfoEntity

data class BreedWithCats(
    @Embedded
    val breeds: BreedsEntity,
    @Relation(
        parentColumn = "breedId",
        entityColumn = "catId",
        associateBy = Junction(CatBreedCrossRef::class)
    )
    val cats: List<CatInfoEntity>,
)