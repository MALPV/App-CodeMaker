package cl.inventionchile.codemaker.ui.screens.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.inventionchile.codemaker.ui.components.MyButton
import cl.inventionchile.codemaker.ui.components.MyTextField

@Composable
fun DialogService(
    onSave: (title: String, url: String, api: String) -> Unit,
    onClose: () -> Unit
){

    val focusManager = LocalFocusManager.current

    var title by remember { mutableStateOf("") }
    var urlBase by remember { mutableStateOf("") }
    var api by remember { mutableStateOf("") }

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.Bottom
                ){
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Nueva API",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.primary
                    )
                    SmallFloatingActionButton(
                        onClick = {
                            focusManager.clearFocus()
                            onClose()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null
                        )
                    }
                }
                Text(
                    text = "Ingresa los parametros para la nueva API.",
                    style = MaterialTheme.typography.labelMedium
                )
            }

            MyTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Titulo API",
                value = title,
                onValueChange = { value -> title = value}
            )

            Column {
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Dominio HTTPS",
                    value = urlBase,
                    onValueChange = { value -> urlBase = value}
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ejemplo: www.ejemplo.com",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column {
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Base API",
                    value = api,
                    onValueChange = { value -> api = value}
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ejemplo: /api/test/",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(visible = urlBase.isNotEmpty()) {
                Card(
                    modifier = Modifier.padding(vertical = 16.dp)
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "URL completa:",
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "<https://$urlBase$api>",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row {
                Spacer(modifier = Modifier.weight(1f))
                MyButton(
                    modifier = Modifier.weight(1f),
                    text = "Crear",
                    enabled = true,
                    onClick = {
                        focusManager.clearFocus()
                        onSave(title, urlBase, api)
                    }
                )
            }
        }
    }
}