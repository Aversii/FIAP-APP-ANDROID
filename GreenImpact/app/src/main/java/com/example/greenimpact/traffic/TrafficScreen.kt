import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Train
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
fun TransportScreen() {
    var distance by remember { mutableStateOf(0f) }
    var transportMode by remember { mutableStateOf(1f) }
    var passengers by remember { mutableStateOf(1) }

    val emissionFactor = when (transportMode.roundToInt()) {
        1 -> 0.05f // Moto
        2 -> 0.12f // Carro
        3 -> 0.08f // Ônibus
        4 -> 0.02f // Metrô
        else -> 0.1f
    }

    val icon = when (transportMode.roundToInt()) {
        1 -> Icons.Default.DirectionsBike
        2 -> Icons.Default.DirectionsCar
        3 -> Icons.Default.DirectionsBus
        4 -> Icons.Default.Train
        else -> Icons.Default.DirectionsCar
    }

    val co2EmissionTotal = distance * emissionFactor * passengers // Agora aumenta corretamente com o número de passageiros
    val equivalentTreesTotal = co2EmissionTotal / 21.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Impacto Ambiental - Transporte",
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
                    text = "Escolha o Meio de Transporte",
                    style = MaterialTheme.typography.headlineSmall
                )
                Slider(
                    value = transportMode,
                    onValueChange = { transportMode = it },
                    valueRange = 1f..4f,
                    steps = 2,
                    thumb = {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Ícone de Transporte",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(50.dp)
                        )
                    },
                    modifier = Modifier.width(300.dp)
                )
                Text(
                    text = when (transportMode.roundToInt()) {
                        1 -> "Moto"
                        2 -> "Carro"
                        3 -> "Ônibus"
                        4 -> "Metrô"
                        else -> ""
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = transportMode > 0,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Distância Percorrida",
                    style = MaterialTheme.typography.headlineSmall
                )
                Slider(
                    value = distance,
                    onValueChange = { distance = it },
                    valueRange = 0f..1000f,
                    modifier = Modifier.width(300.dp)
                )
                Text("${distance.roundToInt()} km", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = distance > 0,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Quantidade de Passageiros",
                    style = MaterialTheme.typography.headlineSmall
                )
                Slider(
                    value = passengers.toFloat(),
                    onValueChange = { passengers = it.roundToInt().coerceAtLeast(1) },
                    valueRange = 1f..10f,
                    steps = 9,
                    modifier = Modifier.width(300.dp)
                )
                Text("${passengers} passageiro(s)", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = passengers > 0,
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
fun TrafficScreenPreview() {
    TransportScreen()
}
