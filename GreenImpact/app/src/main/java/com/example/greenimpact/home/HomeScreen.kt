package com.example.greenimpact.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenimpact.ui.theme.GreenImpactTheme

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo ao Green Impact!",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Clique nas áreas para calcular seu impacto de CO2.")

        Spacer(modifier = Modifier.height(24.dp))

        // Linhas de botões para navegação
        ActivityRow(
            listOf(
                ActivityData(Icons.Default.DirectionsCar, "Trânsito") {
                    navController.navigate("traffic")
                },
                ActivityData(Icons.Default.Fastfood, "Alimentação") {
                    navController.navigate("food")
                }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        ActivityRow(
            listOf(
                ActivityData(Icons.Default.Flight, "Viagem de Avião") {
                    navController.navigate("plane")
                },
                ActivityData(Icons.Default.Build, "Serviços") {
                    navController.navigate("services")
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        ActivityRow(
            listOf(
                ActivityData(Icons.Default.Help, "Quiz sobre CO₂") {
                    navController.navigate("quiz")
                }
            )
        )
    }
}

@Composable
fun ActivityRow(activities: List<ActivityData>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        activities.forEach { activity ->
            ActivityButton(activity.icon, activity.label, activity.onClick)
        }
    }
}

@Composable
fun ActivityButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                isClicked = !isClicked
                onClick()
            }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(55.dp)
                .background(if (isClicked) Color.White else Color.Green, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(40.dp),
                tint = if (isClicked) Color.Green else Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(100.dp)
        )
    }
}

data class ActivityData(val icon: ImageVector, val label: String, val onClick: () -> Unit)

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    GreenImpactTheme(darkTheme = false) {
        val fakeNavController = rememberNavController()
        HomeScreen(navController = fakeNavController)
    }
}
