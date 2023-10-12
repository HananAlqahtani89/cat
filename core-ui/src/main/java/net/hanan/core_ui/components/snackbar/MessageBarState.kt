package net.hanan.core_ui.components.snackbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MessageBarState {
    var success by mutableStateOf<Boolean?>(null)
        private set
    var error by mutableStateOf<Boolean?>(null)
        private set
    internal var updated by mutableStateOf(false)
        private set
    fun success() {
        error = null
        success = true
        updated = !updated
    }
    fun error() {
        success = null
        error = false
        updated = !updated
    }
}