package com.example.bossguer.features.loginPart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bossguer.features.loginPart.domain.LoginPartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginPartViewModel(private val loginPartUseCase: LoginPartUseCase) : ViewModel() {
    private val _usuario = MutableStateFlow("")
    val usuario: StateFlow<String> = _usuario

    private val _contrasena = MutableStateFlow("")
    val contrasena: StateFlow<String> = _contrasena

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun onUsuarioChange(newUsuario: String) {
        _usuario.value = newUsuario
    }

    fun onContrasenaChange(newContrasena: String) {
        _contrasena.value = newContrasena
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            loginPartUseCase.login(_usuario.value, _contrasena.value)
                .onSuccess {
                    _loginState.value = LoginState.Success
                }
                .onFailure {
                    _loginState.value = LoginState.Error(it.message ?: "Error desconocido")
                }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
