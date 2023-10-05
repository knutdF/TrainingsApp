package backend.model


import java.time.LocalDateTime

data class Training(
    val id: String, // Eindeutige ID des Trainings
    val title: String, // Titel des Trainings
    val description: String, // Beschreibung des Trainings
    val startDate: LocalDateTime, // Startdatum und -zeit des Trainings
    val endDate: LocalDateTime, // Enddatum und -zeit des Trainings
    val attendees: List<User>, // Liste der Benutzer, die am Training teilnehmen
    val tests: List<Test> // Liste der Tests, die nach dem Training durchgeführt werden sollen
) {
    // Sie können zusätzliche Methoden oder Felder hier hinzufügen
}
