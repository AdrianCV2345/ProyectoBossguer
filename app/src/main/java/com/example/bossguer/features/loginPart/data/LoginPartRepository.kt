package com.example.bossguer.features.loginPart.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginPartRepository(private val auth: FirebaseAuth) {
    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
