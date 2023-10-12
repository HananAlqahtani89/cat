package net.hanan.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.hanan.core_ui.components.snackbar.ContentWithMessageBar
import net.hanan.core_ui.components.snackbar.rememberMessageBarState
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
                .background(Color.Black)
        ) {
            if (viewModel.state.loading) {

            } else {
                if (viewModel.state.cat.isEmpty()) {
                    // TODO: Empty state
                } else {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(200.dp),
                        verticalItemSpacing = 4.dp,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
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