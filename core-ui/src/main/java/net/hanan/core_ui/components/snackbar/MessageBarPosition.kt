package net.hanan.core_ui.components.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.hanan.core.ui.R
import net.hanan.core_ui.ui.theme.Inter
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun rememberMessageBarState(): MessageBarState {
    return remember { MessageBarState() }
}

enum class MessageBarPosition {
    TOP,
    BOTTOM
}

@Composable
fun ContentWithMessageBar(
    modifier: Modifier = Modifier,
    messageBarState: MessageBarState,
    position: MessageBarPosition = MessageBarPosition.BOTTOM,
    successContainerColor: Color = Color(0xFF20A054),
    contentColor: Color = Color(0xFFFFFFFF),
    errorContainerColor: Color = Color(0xFFEC2E2E),
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(durationMillis = 300),
        expandFrom = if (position == MessageBarPosition.TOP)
            Alignment.Top else Alignment.Bottom
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(durationMillis = 300),
        shrinkTowards = if (position == MessageBarPosition.TOP)
            Alignment.Top else Alignment.Bottom
    ),
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        content()
        MessageBarComponent(
            messageBarState = messageBarState,
            position = position,
            successContainerColor = successContainerColor,
            successContentColor = contentColor,
            errorContainerColor = errorContainerColor,
            errorContentColor = contentColor,
            enterAnimation = enterAnimation,
            exitAnimation = exitAnimation,
            verticalPadding = verticalPadding,
            horizontalPadding = horizontalPadding
        )
    }
}

@Composable
internal fun MessageBarComponent(
    messageBarState: MessageBarState,
    position: MessageBarPosition,
    successContainerColor: Color,
    successContentColor: Color,
    errorContainerColor: Color,
    errorContentColor: Color,
    enterAnimation: EnterTransition,
    exitAnimation: ExitTransition,
    verticalPadding: Dp,
    horizontalPadding: Dp
) {
    var showMessageBar by remember { mutableStateOf(false) }
    val error by rememberUpdatedState(newValue = messageBarState.error)
    val message by rememberUpdatedState(newValue = messageBarState.success)

    DisposableEffect(key1 = messageBarState.updated) {
        showMessageBar = true
        val timer = Timer("Animation Timer", true)
        timer.schedule(3500) {
            showMessageBar = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = if (position == MessageBarPosition.TOP)
            Arrangement.Top else Arrangement.Bottom
    ) {
        AnimatedVisibility(
            visible = messageBarState.error != null && showMessageBar
                    || messageBarState.success != null && showMessageBar,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            MessageBar(
                message = message,
                error = error,
                successContainerColor = successContainerColor,
                successContentColor = successContentColor,
                errorContainerColor = errorContainerColor,
                errorContentColor = errorContentColor,
                verticalPadding = verticalPadding,
                horizontalPadding = horizontalPadding
            )
        }
    }
}

@Composable
internal fun MessageBar(
    message: Boolean?,
    error: Boolean?,
    successContainerColor: Color,
    successContentColor: Color,
    errorContainerColor: Color,
    errorContentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (error != null) errorContainerColor
                else successContainerColor
            )
            .padding(vertical = verticalPadding)
            .padding(horizontal = horizontalPadding)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.something_wrong_msg) ,
                color = if (error != null) errorContentColor else successContentColor,
                fontFamily = Inter,
                fontSize = 13.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}