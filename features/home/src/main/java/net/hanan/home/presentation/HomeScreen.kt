package net.hanan.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.hanan.core_ui.components.snackbar.ContentWithMessageBar
import net.hanan.core_ui.components.snackbar.rememberMessageBarState
import net.hanan.core_ui.components.text.HeadlineText
import net.hanan.home.presentation.components.CatItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCatClicked: (String) -> Unit
) {
    val state = rememberMessageBarState()

    LaunchedEffect(key1 = viewModel.state.error) {
        if (viewModel.state.error) state.error()
    }

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
                if (viewModel.state.cat.isEmpty()) {
                    HeadlineText(
                        text = "There are no cats..",
                        color = Color.White
                    )
                } else {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(200.dp),
                        verticalItemSpacing = 2.dp,
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        content = {
                            items(viewModel.state.cat) {
                                CatItem(
                                    catInfo = it,
                                    onCatClicked = { onCatClicked.invoke(it.id) }
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}