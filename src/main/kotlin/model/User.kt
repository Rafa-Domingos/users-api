package model

data class User(val _id: String? = null, val name: String, val email: String, val institutionalId: String, val role: UserRole)

enum class UserRole {
    TEACHER, STUDENT
}