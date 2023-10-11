package net.hanan.core.util

sealed class UiEvent {
    data class Navigate(val rout: String) : UiEvent()
    object NavigateUp : UiEvent()
}