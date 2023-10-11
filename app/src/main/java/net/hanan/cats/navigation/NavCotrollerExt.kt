package net.hanan.cats.navigation

import androidx.navigation.NavController
import net.hanan.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.rout)
}