package net.hanan.core_ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import net.hanan.core_ui.ui.theme.DarkGray
import net.hanan.core_ui.ui.theme.textStyle

@Composable
fun CalloutText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = DarkGray,
    ) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = textStyle.Callout
    )
}

@Preview
@Composable
fun CalloutText() {
    CalloutText(text = "Callout")
}