package cl.inventionchile.codemaker.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cl.inventionchile.codemaker.R
import kotlinx.coroutines.delay

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    singleLine: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
){
    Card(
        shape = shape
    ){
        TextField(
            modifier = modifier,
            value = value,
            label = { Text(text = label) },
            shape = shape,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            onValueChange = onValueChange,
            singleLine = singleLine
        )
    }
}

@Composable
fun MySecureField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    shape: Shape = MaterialTheme.shapes.medium,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
){
    var hidePassword by remember { mutableStateOf(true) }

    LaunchedEffect(hidePassword) {
        if (!hidePassword) {
            delay(1000)
            hidePassword = true
        }
    }

    Card(
        shape = shape
    ){
        TextField(
            modifier = modifier,
            value = value,
            label = { Text(text = label) },
            shape = shape,
            visualTransformation = if (hidePassword) PasswordVisualTransformation()
            else VisualTransformation.None,
            leadingIcon = leadingIcon,
            trailingIcon = {
                IconButton(onClick = { hidePassword = !hidePassword }){
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = if (hidePassword) painterResource(R.drawable.ic_visibility_off)
                        else painterResource(R.drawable.ic_visibility),
                        contentDescription = null
                    )
                }
            },
            onValueChange = onValueChange,
            singleLine = true
        )
    }
}