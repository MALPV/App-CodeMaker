package cl.inventionchile.codemaker.ui.screens.services

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.inventionchile.codemaker.R
import cl.inventionchile.codemaker.ui.components.MyAnimatedFullContent
import cl.inventionchile.codemaker.ui.screens.dialog.DialogService

@Composable
fun ServicesScreen(
    vm: ServicesVM = hiltViewModel(),
    onBack: () -> Unit
){
    var showDialogServices by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialogServices = !showDialogServices }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row {
                IconButton(onClick = onBack) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Column {
                Text(
                    text = "APIS",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Configura las apis de tus transacciones.",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            LazyColumn(
                Modifier.padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(vm.services){ service ->
                    ServiceItem(
                        text = service.label,
                        isSelected = service.selected,
                        onSelected = { vm.updateServicesByUser(service) },
                        onDelete = { vm.deleteServices(service.id) }
                    )
                }
            }
        }
    }

    MyAnimatedFullContent(visible = showDialogServices) {
        DialogService(
            onSave = { title, url, api ->
                showDialogServices = !showDialogServices
                vm.addServices(title, url, api)
            },
            onClose = { showDialogServices = !showDialogServices }
        )
    }
}

@Composable
private fun ServiceItem(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onDelete: () -> Unit
){
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(onClick = onSelected) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = if (isSelected) painterResource(id = R.drawable.ic_checkbox)
                        else painterResource(id = R.drawable.ic_checkbox_outline),
                    tint = if (isSelected) Color.Green
                    else MaterialTheme.colorScheme.onBackground,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .size(width = 2.dp, height = 40.dp)
                    .background(
                        if (isSelected) Color.Green
                        else MaterialTheme.colorScheme.onBackground
                    )
            )

            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            IconButton(onClick = onDelete) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Rounded.Delete,
                    tint = Color.Red,
                    contentDescription = null
                )
            }
        }
    }
}
