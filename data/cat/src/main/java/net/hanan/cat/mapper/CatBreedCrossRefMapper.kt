package net.hanan.cat.mapper

import net.hanan.cat.local.entity.relations.CatBreedCrossRef

fun String.toCatBreedCrossRef(
    breedId: String
): CatBreedCrossRef = CatBreedCrossRef(
    catId = this,
    breedId = breedId
)