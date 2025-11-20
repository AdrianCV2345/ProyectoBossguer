package com.example.bossguer.features.registro.data.source.remote

import com.example.bossguer.core.common.Result
import com.example.bossguer.features.user.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class RegisterRemoteDataSource(private val firebaseAuth: FirebaseAuth) : IRegisterRemoteDataSource {
    override suspend fun register(email: String, password: String): Result<User> {
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                return Result.Success(User(userId = firebaseUser.uid, email = firebaseUser.email ?: ""))
            } else {
                // CORRECCIÓN: Especificar el tipo genérico para Error
                return Result.Error<User>(Exception("Error: Firebase user is null after registration."))
            }
        } catch (e: Exception) {
            // CORRECIÓN: Especificar el tipo genérico para Error
            return Result.Error<User>(e)
        }
    }
}
