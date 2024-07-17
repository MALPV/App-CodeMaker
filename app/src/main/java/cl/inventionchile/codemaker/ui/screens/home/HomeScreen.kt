package cl.inventionchile.codemaker.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.inventionchile.codemaker.R
import cl.inventionchile.codemaker.ui.components.MyButton

@Preview
@Composable
fun LoginScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { }) {
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
                val items = List(8) { it.toString() }
                val progress = 0.5f

                Row {
                    items.forEach { item ->
                        Card(
                            modifier = Modifier.padding(4.dp),
                            shape = RoundedCornerShape(100.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp),
                                text = item,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }
                }

                LinearProgressIndicator(progress = { progress })

                TextButton(
                    shape = MaterialTheme.shapes.medium,
                    onClick = {}
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
                onClick = {
                    //focusManager.clearFocus()
                    //vm.login(onNext = { navigator.push(EventTypesListScreen()) })
                }
            )
        }
    }
}