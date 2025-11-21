package com.example.bossguer.features.informacionPersonal.domain.usecase

import com.google.firebase.auth.FirebaseAuth

class GetCurrentUserUseCase(private val auth: FirebaseAuth) {
    operator fun invoke() = auth.currentUser
}
