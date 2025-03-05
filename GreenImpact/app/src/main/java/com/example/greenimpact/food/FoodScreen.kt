package com.example.greenimpact.food

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen() {
    var foodType by remember { mutableStateOf(1f) }
    var foodQuantity by remember { mutableStateOf(1f) }

    val emissionFactor = when (foodType.roundToInt()) {
        1 -> 0.8f // Carne
        2 -> 0.5f // Frutas e vegetais
        3 -> 0.6f // Grãos e cereais
        4 -> 0.3f // Laticínios
        else -> 0.5f
    }

    val co2EmissionTotal = foodQuantity * emissionFactor
    val equivalentTreesTotal = co2EmissionTotal / 21.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Impacto Ambiental - Alimentação",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Escolha o Tipo de Alimento",
                    style = MaterialTheme.typography.headlineSmall
                )
                Slider(
                    value = foodType,
                    onValueChange = { foodType = it },
                    valueRange = 1f..4f,
                    steps = 2,
                    modifier = Modifier.width(300.dp)
                )
                Text(
                    text = when (foodType.roundToInt()) {
                        1 -> "Carne"
                        2 -> "Frutas e vegetais"
                        3 -> "Grãos e cereais"
                        4 -> "Laticínios"
                        else -> ""
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = foodType > 0,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Quantidade Consumida (kg/mês)",
                    style = MaterialTheme.typography.headlineSmall
                )
                Slider(
                    value = foodQuantity,
                    onValueChange = { foodQuantity = it },
                    valueRange = 1f..200f,
                    modifier = Modifier.width(300.dp)
                )
                Text("${foodQuantity.roundToInt()} kg", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = foodQuantity > 0,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Estimativa de CO₂: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = "%.2f kg".format(co2EmissionTotal),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red
                    )
                }
                Text(
                    text = "Isso equivale a %.1f árvores absorvendo CO₂ por um ano".format(equivalentTreesTotal),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.width(200.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodScreen() {
    FoodScreen()
}
