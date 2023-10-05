package backend.model


data class User(
    val id: String, // Eindeutige ID des Benutzers
    val username: String, // Benutzername
    val email: String, // E-Mail-Adresse des Benutzers
    val passwordHash: String, // Gehashtes Passwort des Benutzers
    val fullName: String?, // Vollständiger Name des Benutzers (optional)
    val role: UserRole // Rolle des Benutzers (z.B. Admin, User, etc.)
) {
    // Sie können zusätzliche Methoden oder Felder hier hinzufügen
}

enum class UserRole {
    ADMIN,
    USER
}
