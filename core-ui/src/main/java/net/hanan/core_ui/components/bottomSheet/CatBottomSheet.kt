package net.hanan.core_ui.components.bottomSheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.hanan.core_ui.ui.theme.DarkGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBottomSheet(
    sheetContent: @Composable ColumnScope.() -> Unit,
    sheetState: BottomSheetScaffoldState,
    content: @Composable () -> Unit
) {

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            sheetContent()
        },
        sheetPeekHeight = 40.dp,
        sheetShape = RoundedCornerShape(
            topEnd = 20.dp,
            topStart = 20.dp
        ),
        sheetContainerColor = Color.White,
        sheetContentColor = DarkGray
    ) {
        content()
    }
}