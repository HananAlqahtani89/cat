package net.hanan.details.presenntation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hanan.core_ui.components.bottomSheet.CatBottomSheet
import net.hanan.core_ui.components.snackbar.ContentWithMessageBar
import net.hanan.core_ui.components.snackbar.rememberMessageBarState
import net.hanan.core_ui.util.OnLifecycleEvent
import net.hanan.details.presenntation.components.DetailsSheetContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailsScreen(
    viewModel: CatDetailsViewModel = hiltViewModel(), catId: String
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val state = rememberMessageBarState()

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> viewModel.onEvent(CatDetailsEvent.GetCat(catId))

            else -> {}
        }
    }

    CatBottomSheet(
        sheetContent = {
            viewModel.state.cat?.let { cat ->
                DetailsSheetContent(cat)
            }
        }, sheetState = scaffoldState
    ) {
        ContentWithMessageBar(messageBarState = state) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.state.loading)
                    CircularProgressIndicator(color = Color.White)
                else {
                    viewModel.state.cat?.let { cat ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(cat.info.url)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}