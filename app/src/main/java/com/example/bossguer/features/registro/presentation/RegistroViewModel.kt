package com.example.bossguer.features.registro.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bossguer.core.common.Result
import com.example.bossguer.features.registro.domain.usecase.RegisterUseCase
import com.example.bossguer.features.user.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Estado para la UI de registro, representa el resultado de la operación
sealed interface RegisterUiState {
    object Idle : RegisterUiState
    object Loading : RegisterUiState
    data class Success(val user: User) : RegisterUiState
    data class Error(val message: String) : RegisterUiState
}

class RegistroViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    // --- Estados para cada campo del formulario --- //
    private val _ci = MutableStateFlow("")
    val ci: StateFlow<String> = _ci.asStateFlow()

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _gmail = MutableStateFlow("")
    val gmail: StateFlow<String> = _gmail.asStateFlow()

    private val _fechaNacimiento = MutableStateFlow("")
    val fechaNacimiento: StateFlow<String> = _fechaNacimiento.asStateFlow()

    private val _genero = MutableStateFlow("")
    val genero: StateFlow<String> = _genero.asStateFlow()

    private val _ciudad = MutableStateFlow("")
    val ciudad: StateFlow<String> = _ciudad.asStateFlow()

    private val _usuario = MutableStateFlow("")
    val usuario: StateFlow<String> = _usuario.asStateFlow()

    private val _contrasena = MutableStateFlow("")
    val contrasena: StateFlow<String> = _contrasena.asStateFlow()

    // --- Estado para el resultado del registro --- //
    private val _registrationState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val registrationState: StateFlow<RegisterUiState> = _registrationState.asStateFlow()

    // --- Funciones para actualizar los campos desde la UI --- //
    fun onCiChange(newValue: String) {
        _ci.update { newValue }
    }

    fun onNombreChange(newValue: String) {
        _nombre.update { newValue }
    }

    fun onGmailChange(newValue: String) {
        _gmail.update { newValue }
    }

    fun onFechaNacimientoChange(newValue: String) {
        _fechaNacimiento.update { newValue }
    }

    fun onGeneroChange(newValue: String) {
        _genero.update { newValue }
    }

    fun onCiudadChange(newValue: String) {
        _ciudad.update { newValue }
    }

    fun onUsuarioChange(newValue: String) {
        _usuario.update { newValue }
    }

    fun onContrasenaChange(newValue: String) {
        _contrasena.update { newValue }
    }

    // --- Lógica de negocio --- //

    /**
     * Se llama cuando el usuario presiona el botón de registrar ("¡REGISTRARSE!").
     */
    fun onRegisterClick() {
        // Validaciones básicas (se pueden mejorar)
        if (gmail.value.isBlank() || contrasena.value.isBlank()) {
            _registrationState.value = RegisterUiState.Error("El correo y la contraseña no pueden estar vacíos.")
            return
        }

        viewModelScope.launch {
            _registrationState.value = RegisterUiState.Loading
            
            val result = registerUseCase(gmail.value, contrasena.value)
            
            _registrationState.value = when (result) {
                is Result.Success<*> -> RegisterUiState.Success(result.data as User)
                is Result.Error -> {
                    val errorMessage = result.exception.message ?: "Error desconocido"
                    // ¡AÑADIDO! Imprimimos el error en el Logcat para poder verlo.
                    Log.e("RegistroViewModel", "Error en el registro: $errorMessage")
                    RegisterUiState.Error(errorMessage)
                }
            }
        }
    }
}