data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

data class QuestionState(
    val question: Question,
    var selectedAnswerIndex: Int? = null,
    var isAnsweredCorrectly: Boolean? = null
)

object QuizData {
    val quizQuestions = listOf(
        Question(
            questionText = "Qual é a principal fonte de CO₂ em transporte?",
            options = listOf("Carros", "Aviões", "Navios", "Trens"),
            correctAnswerIndex = 0,
            explanation = "Os carros são responsáveis pela maior parte das emissões de CO₂ no setor de transporte devido à sua grande quantidade e uso diário."
        ),
        Question(
            questionText = "Qual setor gera mais emissões de CO₂ globalmente?",
            options = listOf("Agricultura", "Indústria", "Transporte", "Energia elétrica"),
            correctAnswerIndex = 3,
            explanation = "O setor de energia elétrica é responsável pela maior parte das emissões de CO₂ globalmente, devido à queima de combustíveis fósseis."
        ),
        Question(
            questionText = "Qual é o maior contribuinte para as emissões de gases de efeito estufa?",
            options = listOf("Desmatamento", "Queima de combustíveis fósseis", "Resíduos industriais", "Agricultura"),
            correctAnswerIndex = 1,
            explanation = "A queima de combustíveis fósseis é o maior contribuinte para as emissões de gases de efeito estufa, com grande impacto na mudança climática."
        ),
        Question(
            questionText = "Qual combustível é mais utilizado para gerar eletricidade e causa mais emissões?",
            options = listOf("Carvão", "Solar", "Nuclear", "Hidrelétricas"),
            correctAnswerIndex = 0,
            explanation = "O carvão é o combustível fóssil mais utilizado na geração de eletricidade e é o principal responsável pelas emissões de CO₂."
        ),
        Question(
            questionText = "Quais setores mais contribuem para a emissão de metano, outro gás de efeito estufa?",
            options = listOf("Transporte e Indústria", "Petróleo e Gás", "Agricultura e Pecuária", "Comércio e Serviços"),
            correctAnswerIndex = 2,
            explanation = "A agricultura e pecuária, especialmente a criação de gado, são grandes fontes de emissão de metano, um potente gás de efeito estufa."
        ),
        Question(
            questionText = "O que é o efeito estufa?",
            options = listOf("Aquecimento natural da Terra", "Aquecimento causado pela queima de combustível", "Aquecimento causado pela emissão de CO₂", "Aquecimento causado pela queima de madeira"),
            correctAnswerIndex = 0,
            explanation = "O efeito estufa é um processo natural pelo qual a atmosfera retém calor, mas é intensificado pela emissão de gases como o CO₂."
        ),
        Question(
            questionText = "A que temperatura média o planeta aumentaria se não tomássemos medidas para reduzir as emissões?",
            options = listOf("1,5°C", "3°C", "5°C", "7°C"),
            correctAnswerIndex = 1,
            explanation = "O painel intergovernamental sobre mudanças climáticas (IPCC) estima que o planeta pode aquecer até 3°C se não houver ações urgentes."
        ),
        Question(
            questionText = "Qual é o impacto do desmatamento na emissão de CO₂?",
            options = listOf("Reduz a emissão de CO₂", "Não tem impacto", "Aumenta a emissão de CO₂", "Diminui o CO₂ no ar"),
            correctAnswerIndex = 2,
            explanation = "O desmatamento aumenta a emissão de CO₂ porque as árvores que normalmente absorvem o gás são removidas, liberando o CO₂ armazenado."
        ),
        Question(
            questionText = "O que é a pegada de carbono?",
            options = listOf("A quantidade de CO₂ que uma pessoa ou atividade emite", "O peso de CO₂ na atmosfera", "A quantidade de CO₂ que é absorvida por árvores", "O impacto do CO₂ na camada de ozônio"),
            correctAnswerIndex = 0,
            explanation = "A pegada de carbono é a quantidade total de CO₂ emitida por uma pessoa, organização ou atividade."
        ),
        Question(
            questionText = "Qual é a principal forma de energia renovável no Brasil?",
            options = listOf("Energia solar", "Energia eólica", "Hidrelétricas", "Energia nuclear"),
            correctAnswerIndex = 2,
            explanation = "As hidrelétricas são a principal fonte de energia renovável no Brasil, representando mais de 60% da geração de eletricidade."
        ),
        Question(
            questionText = "Quais países são os maiores emissores de CO₂?",
            options = listOf("Brasil e Argentina", "China e Estados Unidos", "Rússia e Canadá", "Alemanha e Japão"),
            correctAnswerIndex = 1,
            explanation = "China e Estados Unidos são os maiores emissores de CO₂ devido às suas grandes indústrias e populações."
        ),
        Question(
            questionText = "Quais são os impactos da mudança climática no meio ambiente?",
            options = listOf("Redução da biodiversidade", "Aumento da temperatura global", "Mudança nos padrões climáticos", "Todos os anteriores"),
            correctAnswerIndex = 3,
            explanation = "A mudança climática causa impactos como a redução da biodiversidade, aumento das temperaturas globais e mudanças nos padrões climáticos."
        ),
        Question(
            questionText = "O que são os gases de efeito estufa?",
            options = listOf("Gases que promovem a fotossíntese", "Gases que ajudam a criar a camada de ozônio", "Gases que aprisionam o calor na atmosfera", "Gases que purificam o ar"),
            correctAnswerIndex = 2,
            explanation = "Os gases de efeito estufa, como o CO₂, aprisionam o calor na atmosfera, o que contribui para o aquecimento global."
        ),
        Question(
            questionText = "O que é uma energia renovável?",
            options = listOf("Energia proveniente de fontes esgotáveis", "Energia proveniente de fontes que se renovam naturalmente", "Energia de combustíveis fósseis", "Energia que não gera impacto ambiental"),
            correctAnswerIndex = 1,
            explanation = "Energia renovável é proveniente de fontes que se renovam naturalmente, como a solar, eólica e hidrelétrica."
        ),
        Question(
            questionText = "Como as energias renováveis podem ajudar a reduzir as emissões de CO₂?",
            options = listOf("Substituindo fontes de energia de baixo impacto", "Reduzindo o consumo de energia", "Substituindo fontes de energia que emitem CO₂", "Aumentando a demanda por energia"),
            correctAnswerIndex = 2,
            explanation = "As energias renováveis, como a solar e a eólica, ajudam a reduzir as emissões de CO₂ ao substituir fontes de energia baseadas em combustíveis fósseis."
        ),
        Question(
            questionText = "O que é o Protocolo de Kyoto?",
            options = listOf("Acordo para reduzir emissões de CO₂ globalmente", "Acordo para aumentar as emissões de CO₂", "Acordo sobre controle de resíduos", "Acordo sobre biodiversidade"),
            correctAnswerIndex = 0,
            explanation = "O Protocolo de Kyoto é um tratado internacional que busca reduzir as emissões de gases de efeito estufa, incluindo o CO₂."
        ),
        Question(
            questionText = "Quais alternativas ajudam a reduzir as emissões de CO₂ nas cidades?",
            options = listOf("Uso de transporte público", "Uso de combustíveis fósseis", "Queima de carvão", "Desmatamento"),
            correctAnswerIndex = 0,
            explanation = "O uso de transporte público é uma alternativa importante para reduzir as emissões de CO₂ nas cidades, pois diminui a quantidade de carros em circulação."
        ),
        Question(
            questionText = "O que é a captura de carbono?",
            options = listOf("Processo de captura do oxigênio", "Processo de captura de CO₂ para armazená-lo", "Processo de eliminar gases tóxicos", "Processo de renovação da camada de ozônio"),
            correctAnswerIndex = 1,
            explanation = "A captura de carbono é o processo de capturar CO₂ da atmosfera para armazená-lo, ajudando a mitigar os impactos das emissões."
        ),
        Question(
            questionText = "Qual a principal fonte de energia no Brasil?",
            options = listOf("Carvão", "Nuclear", "Hidrelétricas", "Solar"),
            correctAnswerIndex = 2,
            explanation = "O Brasil depende principalmente das hidrelétricas para gerar eletricidade, sendo esta a principal fonte de energia do país."
        ),
        Question(
            questionText = "O que é o aquecimento global?",
            options = listOf("Aquecimento dos oceanos apenas", "Aumento da temperatura média global", "Aquecimento apenas nas áreas urbanas", "Aquecimento apenas nas áreas agrícolas"),
            correctAnswerIndex = 1,
            explanation = "O aquecimento global é o aumento da temperatura média global devido à maior concentração de gases de efeito estufa, como o CO₂."
        )
    )

    val quizQuestionsState = quizQuestions.map { QuestionState(it) }
}
