package net.hanan.cat.mapper

import net.hanan.cat.local.entity.relations.CatWithBreeds
import net.hanan.cat.remote.dto.CatDto
import net.hanan.core.domain.exception.MappingException
import net.hanan.core.domain.model.Cat

fun CatDto.toCatWithBreeds(
): CatWithBreeds = CatWithBreeds(
    cat = toCatEntity(),
    breeds = breeds?.toBreedsEntity() ?: throw MappingException("breeds cannot be null"),
)

fun CatWithBreeds.toCat(
): Cat = Cat(
    info = toCatInfo(),
    breeds = breeds.toBreed()
)