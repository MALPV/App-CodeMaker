package cl.inventionchile.codemaker.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.inventionchile.codemaker.ui.components.MyButton
import cl.inventionchile.codemaker.ui.components.MyCheckBox
import cl.inventionchile.codemaker.ui.components.MySecureField
import cl.inventionchile.codemaker.ui.components.MyTextField

@Preview
@Composable
fun LoginScreen(){
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column {
                    Text(
                        text = "Iniciar sesión",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Ingresa tu correo y contraseña para iniciar sesión.",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column {
                    MyTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Correo",
                        value = "",
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null
                            )
                        },
                        onValueChange = { value ->  }
                    )

                    MySecureField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Contraseña",
                        value = "",
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        },
                        onValueChange = { value ->  }
                    )
                }
                MyCheckBox(
                    label = "Recordar usuario",
                    isChecked = true,
                    onCheckedChange = { value ->  }
                )
                MyButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Iniciar sesión",
                    enabled = true,
                    onClick = {
                        //focusManager.clearFocus()
                        //vm.login(onNext = { navigator.push(EventTypesListScreen()) })
                    }
                )
            }
        }
    }
}