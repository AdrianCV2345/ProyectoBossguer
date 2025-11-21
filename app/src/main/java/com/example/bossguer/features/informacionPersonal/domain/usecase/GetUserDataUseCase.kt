package com.example.bossguer.features.informacionPersonal.domain.usecase

import com.example.bossguer.features.informacionPersonal.domain.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GetUserDataUseCase(private val database: FirebaseDatabase) {

    suspend operator fun invoke(uid: String): Result<User> = runCatching {
        suspendCancellableCoroutine { continuation ->
            val userRef = database.getReference("Users").child(uid)

            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    if (user != null) {
                        continuation.resume(user)
                    } else {
                        continuation.resumeWithException(Exception("User data not found"))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resumeWithException(error.toException())
                }
            }

            userRef.addListenerForSingleValueEvent(listener)

            continuation.invokeOnCancellation {
                userRef.removeEventListener(listener)
            }
        }
    }
}
