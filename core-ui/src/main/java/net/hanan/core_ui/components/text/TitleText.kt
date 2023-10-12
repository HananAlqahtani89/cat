package net.hanan.core_ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import net.hanan.core_ui.ui.theme.textStyle

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle.Title,
        color = color
    )
}

@Preview
@Composable
fun PreviewTitleText() {
    TitleText(
        text = "Title Text"
    )
}