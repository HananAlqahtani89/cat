package net.hanan.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hanan.core.domain.model.CatInfo

@Composable
fun CatItem(
    catInfo: CatInfo,
    onCatClicked: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onCatClicked.invoke()
        }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(catInfo.url).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}