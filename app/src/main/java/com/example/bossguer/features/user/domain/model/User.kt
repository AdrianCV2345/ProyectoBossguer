package com.example.bossguer.features.user.domain.model

/**
 * Represents a registered user in our system.
 */
data class User(
    val userId: String, // The unique identifier from the authentication provider (e.g., Firebase UID)
    val email: String
)
