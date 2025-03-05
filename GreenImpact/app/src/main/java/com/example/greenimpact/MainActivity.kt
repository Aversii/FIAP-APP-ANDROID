package com.example.greenimpact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.greenimpact.ui.theme.GreenImpactTheme
import com.example.greenimpact.ui.theme.ThemeViewModel
import com.example.greenimpact.navigation.AppNavigation

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            GreenImpactTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()

                // Carregar o tema na tela de splash
                LaunchedEffect(Unit) {
                    // Definir o tema antes da navegação
                    themeViewModel.toggleTheme() // Alterna o tema logo ao iniciar
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Green Impact") },
                            navigationIcon = {
                                if (navController.currentBackStackEntryAsState().value?.destination?.route != "login") {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                                    }
                                }
                            },
                            actions = {
                                IconButton(onClick = { themeViewModel.toggleTheme() }) {
                                    val iconText = if (isDarkTheme) "🌞" else "🌙"
                                    Text(iconText)
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        AppNavigation(navController = navController)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    GreenImpactTheme(darkTheme = false) {
        val fakeNavController = rememberNavController()
        AppNavigation(navController = fakeNavController)
    }
}