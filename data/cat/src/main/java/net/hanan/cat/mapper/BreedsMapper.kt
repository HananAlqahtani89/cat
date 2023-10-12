package net.hanan.cat.mapper

import net.hanan.cat.local.entity.BreedsEntity
import net.hanan.cat.remote.dto.BreedDto
import net.hanan.core.domain.exception.MappingException
import net.hanan.core.domain.model.Breeds

fun List<BreedDto?>.toBreedsEntity(
): List<BreedsEntity> {
    val breeds = arrayListOf<BreedsEntity>()
    for (item in this)
        breeds.add(
            BreedsEntity(
                breedId = item?.id ?: throw MappingException("id cannot be null"),
                description = item.description
                    ?: throw MappingException("description cannot be null"),
                origin = item.origin ?: throw MappingException("origin cannot be null"),
                lifeSpan = item.lifeSpan ?: throw MappingException("lifeSpan cannot be null"),
                name = item.name ?: throw MappingException("name cannot be null"),
            )
        )
    return breeds
}


fun List<BreedsEntity>.toBreed(
): List<Breeds> {
    val breeds = arrayListOf<Breeds>()
    for (item in this)
        breeds.add(
            Breeds(
                name = item.name,
                origin = item.origin,
                description = item.description,
                lifeSpan = item.lifeSpan
            )
        )
    return breeds
}