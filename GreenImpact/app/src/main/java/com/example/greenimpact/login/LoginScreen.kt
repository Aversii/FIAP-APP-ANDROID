package com.example.greenimpact.login
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.greenimpact.R
import com.example.greenimpact.ui.theme.GreenImpactTheme

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.img_login),
            contentDescription = "Logo Green Impact",
            modifier = Modifier.size(300.dp)
        )


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
                .offset(y = -50.dp)

        )

        Spacer(modifier = Modifier.height(22.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
                .offset(y = -50.dp)
            ,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    val iconText = if (passwordVisible) "🙈" else "👁️"
                    Text(iconText)
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { /* Lógica de autenticação */
                navController.navigate("home")

            },
            modifier = Modifier.fillMaxWidth()
                .offset(y = -30.dp)

        ) {
            Text(
                text = "Entrar",
                color = Color.White,
                fontSize = 16.sp
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    GreenImpactTheme(darkTheme = false) {
        val fakeNavController = rememberNavController()
        LoginScreen(navController = fakeNavController)
    }
}