package backend.model

data class Test(
    val id: String, // Eindeutige ID des Tests
    val questions: List<Question>, // Liste der Fragen im Test
    val passingScore: Int // Mindestpunktzahl zum Bestehen des Tests
)

data class Question(
    val text: String, // Text der Frage
    val choices: List<String>, // Liste der Antwortmöglichkeiten
    val correctAnswer: Int // Index der richtigen Antwort in der Liste der Antwortmöglichkeiten
)
