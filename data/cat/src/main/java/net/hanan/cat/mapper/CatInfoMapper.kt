package net.hanan.cat.mapper

import net.hanan.cat.local.entity.CatInfoEntity
import net.hanan.cat.local.entity.relations.CatWithBreeds
import net.hanan.cat.remote.dto.CatDto
import net.hanan.core.domain.exception.MappingException
import net.hanan.core.domain.model.CatInfo

fun CatDto.toCatEntity(
): CatInfoEntity = CatInfoEntity(
    catId = id ?: throw MappingException("id cannot be null"),
    url = url ?: throw MappingException("url cannot be null"),
)

fun CatInfoEntity.toCatInfo(
): CatInfo = CatInfo(
    id = catId,
    url = url
)

fun CatWithBreeds.toCatInfo(
):CatInfo = CatInfo(
    id = cat.catId,
    url = cat.url
)