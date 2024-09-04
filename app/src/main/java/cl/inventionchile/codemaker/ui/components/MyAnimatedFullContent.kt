package cl.inventionchile.codemaker.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyAnimatedFullContent(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
){
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = scaleIn() + slideInVertically { it },
        exit = scaleOut() + slideOutVertically { it },
        content = content
    )
}