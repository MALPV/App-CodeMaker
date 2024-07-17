package cl.inventionchile.codemaker.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun MyAnimationLottie(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    isLoading: Boolean = true,
    speed: Float = 1f,
    animation: Int
) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(animation)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLoading,
        speed = speed,
        restartOnPlay = false
    )

    LottieAnimation(
        modifier = modifier,
        contentScale = contentScale,
        composition = composition,
        progress = { progress },
    )
}