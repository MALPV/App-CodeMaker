package cl.inventionchile.codemaker.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    text: String,
    painter: Painter? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        enabled = enabled
    ){
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        painter?.let { icon ->
            Icon(
                modifier = Modifier.size(20.dp),
                painter = icon,
                contentDescription = null
            )
        }
    }
}
