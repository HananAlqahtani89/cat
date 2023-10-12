package net.hanan.home.presentation

import net.hanan.core.domain.model.CatInfo

data class HomeState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val cat: List<CatInfo> = emptyList()
)