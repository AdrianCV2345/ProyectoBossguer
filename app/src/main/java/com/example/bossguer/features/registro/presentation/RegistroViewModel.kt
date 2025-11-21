package com.example.bossguer.features.registro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bossguer.features.informacionPersonal.domain.model.User
import com.example.bossguer.features.registro.domain.usecase.RegisterUseCase
import com.example.bossguer.features.registro.domain.usecase.SaveUserDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class RegistroViewModel(
    private val registerUseCase: RegisterUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase
) : ViewModel() {

    // --- State for TextFields ---
    private val _ci = MutableStateFlow("")
    val ci: StateFlow<String> = _ci

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre

    private val _gmail = MutableStateFlow("")
    val gmail: StateFlow<String> = _gmail

    private val _usuario = MutableStateFlow("")
    val usuario: StateFlow<String> = _usuario

    private val _contrasena = MutableStateFlow("")
    val contrasena: StateFlow<String> = _contrasena

    // --- State for DatePicker ---
    private val _fechaNacimiento = MutableStateFlow("")
    val fechaNacimiento: StateFlow<String> = _fechaNacimiento

    private val _showDatePicker = MutableStateFlow(false)
    val showDatePicker: StateFlow<Boolean> = _showDatePicker

    private val _ageError = MutableStateFlow<String?>(null)
    val ageError: StateFlow<String?> = _ageError


    // --- State for Dropdowns ---
    private val _genero = MutableStateFlow("")
    val genero: StateFlow<String> = _genero

    private val _ciudad = MutableStateFlow("")
    val ciudad: StateFlow<String> = _ciudad

    val genderOptions = listOf("Masculino", "Femenino", "Otro")
    val cityOptions = listOf(
        "Cochabamba", "La Paz", "Santa Cruz", "Oruro", "Potosí",
        "Chuquisaca", "Tarija", "Beni", "Pando"
    )

    private val _isGenderMenuExpanded = MutableStateFlow(false)
    val isGenderMenuExpanded: StateFlow<Boolean> = _isGenderMenuExpanded

    private val _isCityMenuExpanded = MutableStateFlow(false)
    val isCityMenuExpanded: StateFlow<Boolean> = _isCityMenuExpanded


    // --- State for Registration Process ---
    private val _registrationState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val registrationState: StateFlow<RegisterUiState> = _registrationState

    // --- Event Handlers for TextFields ---
    fun onCiChange(value: String) { _ci.value = value }
    fun onNombreChange(value: String) { _nombre.value = value }
    fun onGmailChange(value: String) { _gmail.value = value }
    fun onUsuarioChange(value: String) { _usuario.value = value }
    fun onContrasenaChange(value: String) { _contrasena.value = value }

    // --- Event Handlers for DatePicker ---
    fun onShowDatePicker(show: Boolean) {
        _showDatePicker.value = show
    }

    fun onDateSelected(date: LocalDate) {
        val age = Period.between(date, LocalDate.now()).years
        if (age < 0 || age > 90) {
            _ageError.value = "La edad debe ser entre 0 y 90 años"
            _fechaNacimiento.value = ""
        } else {
            _ageError.value = null
            _fechaNacimiento.value = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }
    }


    // --- Event Handlers for Dropdowns ---
    fun onGeneroSelected(selectedGenero: String) {
        _genero.value = selectedGenero
        _isGenderMenuExpanded.value = false
    }

    fun onCiudadSelected(selectedCiudad: String) {
        _ciudad.value = selectedCiudad
        _isCityMenuExpanded.value = false
    }

    fun onGenderMenuDismiss() {
        _isGenderMenuExpanded.value = false
    }

    fun onCityMenuDismiss() {
        _isCityMenuExpanded.value = false
    }

    fun onGenderMenuClick() {
        _isGenderMenuExpanded.value = true
    }

    fun onCityMenuClick() {
        _isCityMenuExpanded.value = true
    }


    // --- Registration Logic ---
    fun onRegisterClick() {
        viewModelScope.launch {
            _registrationState.value = RegisterUiState.Loading

            registerUseCase(_gmail.value, _contrasena.value).fold(
                onSuccess = { firebaseUser ->
                    val userData = User(
                        uid = firebaseUser.uid,
                        ci = _ci.value,
                        nombre = _nombre.value,
                        gmail = _gmail.value,
                        usuario = _usuario.value,
                        fechaNacimiento = _fechaNacimiento.value,
                        genero = _genero.value,
                        ciudad = _ciudad.value
                    )
                    saveUserDataUseCase(firebaseUser.uid, userData).fold(
                        onSuccess = { _registrationState.value = RegisterUiState.Success },
                        onFailure = { e -> _registrationState.value = RegisterUiState.Error(e.message ?: "Error al guardar datos.") }
                    )
                },
                onFailure = { e ->
                    _registrationState.value = RegisterUiState.Error(e.message ?: "Error de registro.")
                }
            )
        }
    }
}

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    object Success : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}
