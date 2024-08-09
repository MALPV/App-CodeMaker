package cl.inventionchile.codemaker.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.inventionchile.codemaker.R
import cl.inventionchile.codemaker.data.core.copyToClipboard
import cl.inventionchile.codemaker.ui.components.MyButton
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    vm: HomeVM = hiltViewModel(),
    onBack: () -> Unit
) {

    val context = LocalContext.current
    var copyPressed by rememberSaveable { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (copyPressed) 2f else 1f,
        label = "scale"
    )

    LaunchedEffect(copyPressed) {
        if (copyPressed){
            delay(500)
            copyPressed = false
        }
    }

    BackHandler { }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onBack) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_logout),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            }
            Column {
                Text(
                    text = "Clave dinámica",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Usa esta clave para aprobar tus transacciones por internet.",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin.Center
                        },
                    text = vm.code.value,
                    color = MaterialTheme.colorScheme.onBackground,
                    letterSpacing = 8.sp,
                    style = MaterialTheme.typography.headlineLarge
                )

                LinearProgressIndicator(
                    progress = { vm.progress.floatValue },
                    color = Color.Green
                )

                TextButton(
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        copyPressed = true
                        context.copyToClipboard(vm.code.value)
                    }
                ) {
                    Text(
                        text = "Toca para copiar",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            MyButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Generar nueva clave",
                painter = painterResource(id = R.drawable.ic_new_code),
                enabled = true,
                onClick = { vm.refreshCode() }
            )
        }
    }
}