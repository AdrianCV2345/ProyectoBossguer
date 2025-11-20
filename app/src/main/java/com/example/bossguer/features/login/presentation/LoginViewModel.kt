package com.example.bossguer.features.login.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun onLoginClick() {
        // Aquí iría la lógica de login
    }

    fun onCreateAccountClick() {
        // Aquí iría la lógica de crear cuenta
    }

    fun onSocialLoginClick(provider: SocialProvider) {
        // Aquí iría la lógica de login social
    }
}

// Estado de la pantalla de login
data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null
)

// Enum para los proveedores sociales
enum class SocialProvider {
    FACEBOOK, GOOGLE, APPLE, OTHER
}