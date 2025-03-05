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
fun ServiceScreen() {
    var serviceType by remember { mutableStateOf(1f) }
    var serviceUsage by remember { mutableStateOf(1f) }

    val emissionFactor = when (serviceType.roundToInt()) {
        1 -> 0.3f // Energia elétrica
        2 -> 0.6f // Serviços bancários
        3 -> 0.4f // Tecnologia (data centers, etc.)
        4 -> 0.2f // Serviços públicos
        else -> 0.5f
    }

    val co2EmissionTotal = serviceUsage * emissionFactor
    val equivalentTreesTotal = co2EmissionTotal / 21.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Impacto Ambiental - Serviços",
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
                    text = "Escolha o Tipo de Serviço",
                    style = MaterialTheme.typography.headlineSmall
                )
                Slider(
                    value = serviceType,
                    onValueChange = { serviceType = it },
                    valueRange = 1f..4f,
                    steps = 2,
                    modifier = Modifier.width(300.dp)
                )
                Text(
                    text = when (serviceType.roundToInt()) {
                        1 -> "Energia elétrica"
                        2 -> "Serviços bancários"
                        3 -> "Tecnologia"
                        4 -> "Serviços públicos"
                        else -> ""
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = serviceType > 0,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Uso do Serviço (horas/mês)",
                    style = MaterialTheme.typography.headlineSmall
                )
                Slider(
                    value = serviceUsage,
                    onValueChange = { serviceUsage = it },
                    valueRange = 1f..200f,
                    modifier = Modifier.width(300.dp)
                )
                Text("${serviceUsage.roundToInt()} horas", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = serviceUsage > 0,
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
fun ServiceScreenPreview() {
   ServiceScreen()
}
