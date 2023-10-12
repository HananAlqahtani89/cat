package net.hanan.details.presenntation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hanan.core_ui.components.snackbar.ContentWithMessageBar
import net.hanan.core_ui.components.snackbar.rememberMessageBarState
import net.hanan.core_ui.components.text.HeadlineText
import net.hanan.core_ui.components.text.SubheadText
import net.hanan.core_ui.components.text.TitleText
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

    val state = rememberMessageBarState()

    ContentWithMessageBar(messageBarState = state) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // TODO: Loading

            viewModel.state.cat?.let { cat ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cat.info.url)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                TitleText(text = cat.breeds[0].name)

                Spacer(modifier = Modifier.padding(top = 16.dp))

                HeadlineText(text = "Description")
                Spacer(modifier = Modifier.padding(top = 8.dp))

                SubheadText(text = cat.breeds[0].description)
            }
        }
    }
}