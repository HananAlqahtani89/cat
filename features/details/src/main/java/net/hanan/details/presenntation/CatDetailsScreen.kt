package net.hanan.details.presenntation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import net.hanan.core_ui.util.OnLifecycleEvent

@Composable
fun CatDetailsScreen(
    viewModel: CatDetailsViewModel = hiltViewModel(),
    catId: String
) {
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE ->
                viewModel.onEvent(CatDetailsEvent.GetCat(catId))

            else -> {}
        }
    }
}