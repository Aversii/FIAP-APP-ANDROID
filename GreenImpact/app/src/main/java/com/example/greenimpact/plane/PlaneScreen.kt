import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Factory
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
fun PlaneScreen() {
    var distance by remember { mutableStateOf(0f) }
    var passengers by remember { mutableStateOf(1f) }
    var classType by remember { mutableStateOf(1f) }

    val emissionFactorPerKm = 0.115f

    val classMultiplier = when (classType.toInt()) {
        1 -> 1.0f  // Econômica
        2 -> 1.5f  // Executiva (1.5x mais emissão)
        3 -> 2.0f  // Primeira Classe (2x mais emissão)
        else -> 1.0f
    }

    val validPassengers = passengers.toInt().coerceAtLeast(1)

    val co2EmissionTotal = distance * emissionFactorPerKm * validPassengers * classMultiplier

    val equivalentTreesTotal = if (co2EmissionTotal > 0) co2EmissionTotal / 21.0 else 0.0

    val co2EmissionPerPassenger = if (validPassengers > 0) co2EmissionTotal / validPassengers else 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Impacto Ambiental - Viagem de Avião",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Slider de Distância
        Text(
            text = "Distância da Viagem",
            style = MaterialTheme.typography.headlineSmall
        )
        Slider(
            value = distance,
            onValueChange = { distance = it },
            valueRange = 0f..16000f,
            modifier = Modifier.width(300.dp)
        )
        Text("${distance.toInt()} km", style = MaterialTheme.typography.bodyLarge)

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

        if (distance > 3000) {
            Text(
                text = "⚠️ Voos longos geram alta emissão! Considere alternativas sustentáveis.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Yellow
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedVisibility(visible = distance > 0) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Número de Passageiros", style = MaterialTheme.typography.headlineSmall)
                Slider(
                    value = passengers,
                    onValueChange = { passengers = it },
                    valueRange = 1f..500f,
                    steps = 499,
                    modifier = Modifier.width(300.dp)
                )
                Text("${validPassengers} passageiros", style = MaterialTheme.typography.bodyLarge)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Emissão por passageiro: ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "%.2f kg CO₂".format(co2EmissionPerPassenger),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red
                    )
                }

                Text(
                    text = "Equivale a %.1f árvores/passageiro".format(equivalentTreesTotal / validPassengers),
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
fun PlaneScreenPreview() {
    PlaneScreen()
}