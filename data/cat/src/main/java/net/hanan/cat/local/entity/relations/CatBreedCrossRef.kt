package net.hanan.cat.local.entity.relations

import androidx.room.Entity

@Entity(primaryKeys = ["catId", "breedId"])
data class CatBreedCrossRef(
    val catId: String,
    val breedId: String
)