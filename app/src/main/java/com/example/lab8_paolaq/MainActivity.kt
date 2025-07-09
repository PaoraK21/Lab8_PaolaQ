package com.example.lab8_paolaq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.businesscard.DataStoreViewModel
import com.example.lab8_paolaq.ui.theme.Lab8_PaolaQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: DataStoreViewModel = viewModel()
            Lab8_PaolaQTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "card") {
                        composable("card") {
                            BusinessCard(
                                onDoubleTap = { navController.navigate("edit") },
                                viewModel = viewModel
                            )
                        }
                        composable("edit") {
                            DataStoreScreen(
                                onSave = { navController.popBackStack() },
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier : Modifier = Modifier, onDoubleTap: () -> Unit, viewModel: DataStoreViewModel) {
    val gestureModifier = Modifier.pointerInput(Unit) {
        detectTapGestures(onDoubleTap = { onDoubleTap() })
    }

    Box(modifier = modifier
        .then(gestureModifier)
        .fillMaxSize()) {
        ProfileInfo(modifier = modifier.align(Alignment.Center), viewModel = viewModel)
        ContactInfo(modifier = modifier.align(Alignment.BottomCenter), viewModel = viewModel)
    }
}

@Composable
fun ProfileInfo(modifier : Modifier = Modifier, viewModel: DataStoreViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.android),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .background(Color(0xFF073042))
                .size(height = 100.dp, width = 100.dp)

        )

        Text(
            text = uiState.name,
            fontSize = 40.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
        )

        Text(
            text = uiState.role,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF006D3A),
        )
        Text(
            text = "${uiState.year} years of Experience",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}
@Composable
fun ContactInfo(modifier : Modifier = Modifier, viewModel: DataStoreViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = modifier.padding(bottom = 20.dp)) {
        ContactRow(text = uiState.phone, icon = Icons.Filled.Call)
        ContactRow(text = uiState.handle, icon = Icons.Filled.Share)
        ContactRow(text = uiState.email, icon = Icons.Filled.Email)
    }
}

@Composable
fun ContactRow(
    text: String, icon: ImageVector, textBlur: Dp = 0.dp,
    useWeight : Boolean = false, useBasic : Boolean = false
) {
    Row {

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(start = 10.dp),
            Color(0xFF006D3A)
        )

        Text(
            text = text,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 18.dp, top = 5.dp)
        )
    }
}