package net.hanan.details.presenntation

abstract class CatDetailsEvent {
    data class GetCat(val id: String): CatDetailsEvent()
}