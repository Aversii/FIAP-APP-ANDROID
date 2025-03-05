package com.example.quizapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(navController: NavController) {
    val MAX_QUESTIONS = 10 // Definindo o número máximo de questões
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableStateOf(0) }
    var showScore by remember { mutableStateOf(false) }
    var isQuestionAnswered by remember { mutableStateOf(false) } // Nova variável para verificar se a questão foi respondida

    // Mapa para armazenar as respostas selecionadas
    val answers = remember { mutableStateMapOf<Int, Int?>() }

    // Limita o número de questões
    val questions = QuizData.quizQuestions.take(MAX_QUESTIONS)
    val question = questions[currentQuestionIndex]

    // Verifica se a resposta selecionada está correta
    val isAnswerCorrect = selectedAnswerIndex == question.correctAnswerIndex

    // Função para avançar para a próxima pergunta
    fun nextQuestion() {
        if (selectedAnswerIndex != null && !showScore) {
            // Salvar a resposta
            answers[currentQuestionIndex] = selectedAnswerIndex

            if (isAnswerCorrect) {
                score++
            }

            // Avançar para a próxima pergunta ou mostrar a pontuação
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                selectedAnswerIndex = answers[currentQuestionIndex] // Carregar a resposta persistida
                isQuestionAnswered = false // Reinicia o estado para a nova questão
            } else {
                showScore = true
            }
        }
    }

    // Função para voltar à pergunta anterior
    fun previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            selectedAnswerIndex = answers[currentQuestionIndex] // Carregar a resposta persistida
            isQuestionAnswered = true // Define como true, pois a questão já foi respondida anteriormente
        }
    }

    // Função para exibir uma mensagem personalizada com base na pontuação
    fun getScoreMessage(): String {
        return when (score) {
            in 0..3 -> "Não desista! Você vai melhorar na próxima!"
            in 4..6 -> "Bom trabalho! Continue assim!"
            in 7..8 -> "Excelente! Você está quase lá!"
            else -> "Perfeito! Você é um expert!"
        }
    }

    // Efeito para avançar automaticamente se a questão foi respondida corretamente pela primeira vez
    LaunchedEffect(isAnswerCorrect, isQuestionAnswered) {
        if (isAnswerCorrect && isQuestionAnswered && answers[currentQuestionIndex] == null) {
            // Aguarda 1 segundo antes de avançar para a próxima questão
            delay(1000)
            nextQuestion()
        }
    }

    // Layout da tela de quiz
    if (showScore) {
        // Tela final com pontuação
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Você acertou $score de ${questions.size} perguntas!")
            Spacer(modifier = Modifier.height(16.dp))
            Text(getScoreMessage(), style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // Reiniciar o quiz
                score = 0
                currentQuestionIndex = 0
                showScore = false
                answers.clear()
                isQuestionAnswered = false
            }) {
                Text("Jogar novamente")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                navController.navigate("home")
            }) {
                Text("Voltar")
            }
        }
    } else {
        // Pergunta atual e opções de resposta
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Exibir contador de questões
            Text(
                text = "Questão ${currentQuestionIndex + 1} de ${questions.size}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Exibir a pergunta
            Text(text = question.questionText, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar as opções de resposta
            question.options.forEachIndexed { index, option ->
                val isOptionSelected = selectedAnswerIndex == index
                val isCorrectAnswer = index == question.correctAnswerIndex

                // Cor do botão dependendo se a questão foi respondida ou não
                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        selectedAnswerIndex != null -> when {
                            isCorrectAnswer -> Color.Green // Resposta correta
                            isOptionSelected -> Color.Red // Resposta errada
                            else -> Color.Gray // Resposta não escolhida
                        }
                        else -> Color.Gray // Resposta não escolhida
                    }
                )

                Button(
                    onClick = {
                        if (selectedAnswerIndex == null) {
                            selectedAnswerIndex = index
                            isQuestionAnswered = true // Marca a questão como respondida
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = buttonColors
                ) {
                    Text(text = option, color = Color.White, style = MaterialTheme.typography.titleMedium)

                }
            }

            // Exibir explicação ou mensagem de sucesso
            if (selectedAnswerIndex != null) {
                Spacer(modifier = Modifier.height(16.dp))
                if (isAnswerCorrect) {
                    Text("Parabéns! Você acertou!", color = Color.Green)
                } else {
                    Text("Explicação: ${question.explanation}", color = Color.Red)
                }
            }

            // Navegação entre questões (Próxima e Anterior)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { previousQuestion() },
                    enabled = currentQuestionIndex > 0
                ) {
                    Text("Anterior")
                }

                Button(
                    onClick = { nextQuestion() },
                    enabled = selectedAnswerIndex != null
                ) {
                    Text("Próxima")
                }
            }

            // Pontuação fixa na tela
            Spacer(modifier = Modifier.height(16.dp))
            Text("Pontuação: $score", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    val fakeNavController = rememberNavController()
    QuizScreen(navController = fakeNavController)
}
