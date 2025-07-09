package com.example.lab8_paolaq

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.businesscard.DataStoreViewModel

@Composable
fun DataStoreScreen(viewModel: DataStoreViewModel = viewModel(), onSave: () -> Unit) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .background(Color(0xFF073042))
                    .size(height = 100.dp, width = 100.dp)

            )
        }

        item {
            TextField(
                value = uiState.name,
                onValueChange = {
                    //stringInput = it
                    viewModel.updateName(it)
                },
                label = { Text("Name") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            TextField(
                value = uiState.role,
                onValueChange = {
                    //roleInput = it
                    viewModel.updateRole(it)
                },
                label = { Text("Role") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            Column {
                Text(
                    text = "Experience",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 55.dp, end = 15.dp)
                )
                Row (
                    modifier = Modifier
                        .padding(start = 55.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${uiState.year}")
                    Slider(
                        value = uiState.year.toFloat(),
                        onValueChange = {
                            viewModel.updateYear(it.toInt())
                        },
                        valueRange = 0f..50f,
                    )
                }
            }
        }

        item {
            TextField(
                value = uiState.phone,
                onValueChange = {
                    viewModel.updatePhone(it)
                },
                label = { Text("Phone") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            TextField(
                value = uiState.handle,
                onValueChange = {
                    viewModel.updateHandle(it)
                },
                label = { Text("Handle") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            TextField(
                value = uiState.email,
                onValueChange = {
                    viewModel.updateEmail(it)
                },
                label = { Text("Email") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color.Red
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Show Contact Info")
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = uiState.showContactInfo,
                    onCheckedChange = {
                        viewModel.toggleContactInfo()
                    }
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(onClick = {
                viewModel.saveValuesInDataStore()
                viewModel.toggleSettings()
                onSave()
            }) {
                Text("Save & Close")
            }
        }
    }
}