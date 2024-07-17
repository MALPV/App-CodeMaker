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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.inventionchile.codemaker.ui.components.MyButton
import cl.inventionchile.codemaker.ui.components.MyCheckBox
import cl.inventionchile.codemaker.ui.components.MyLoading
import cl.inventionchile.codemaker.ui.components.MyMessage
import cl.inventionchile.codemaker.ui.components.MySecureField
import cl.inventionchile.codemaker.ui.components.MyTextField

@Composable
fun LoginScreen(
    vm: LoginVM = hiltViewModel(),
    onHome: () -> Unit
){

    val focusManager = LocalFocusManager.current

    Scaffold{
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
                        text = "Ingresa tu usuario y contraseña para iniciar sesión.",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column {
                    MyTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Usuario",
                        value = vm.username.value,
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null
                            )
                        },
                        onValueChange = { value -> vm.username.value = value}
                    )

                    MySecureField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Contraseña",
                        value = vm.password.value,
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.Lock,
                                contentDescription = null
                            )
                        },
                        onValueChange = { value -> vm.password.value = value }
                    )
                }
                MyCheckBox(
                    label = "Recordar usuario",
                    isChecked = vm.remember.value,
                    onCheckedChange = { value -> vm.remember.value = value }
                )
                MyButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Iniciar sesión",
                    enabled = true,
                    onClick = {
                        focusManager.clearFocus()
                        vm.authenticate(onHome)
                    }
                )
            }
        }

        if (vm.isLoading.value){
            MyLoading()
        }

        if (vm.errorMsg.value.isNotEmpty()){
            MyMessage(
                message = vm.errorMsg.value,
                onDismissRequest = { vm.errorMsg.value = "" }
            )
        }
    }
}