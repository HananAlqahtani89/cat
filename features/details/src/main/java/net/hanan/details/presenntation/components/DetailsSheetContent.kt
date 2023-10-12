package net.hanan.details.presenntation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.hanan.core.domain.model.Cat
import net.hanan.core_ui.components.text.CalloutText
import net.hanan.core_ui.components.text.TitleText

@Composable
fun DetailsSheetContent(cat: Cat) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 80.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText(text = cat.breeds[0].name)
        Spacer(modifier = Modifier.padding(top = 30.dp))
        CalloutText(text = cat.breeds[0].description)
    }
}