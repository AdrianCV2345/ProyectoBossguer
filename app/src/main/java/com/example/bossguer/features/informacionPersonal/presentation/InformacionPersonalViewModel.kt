package com.example.bossguer.features.informacionPersonal.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bossguer.features.informacionPersonal.domain.model.User
import com.example.bossguer.features.informacionPersonal.domain.usecase.GetCurrentUserUseCase
import com.example.bossguer.features.informacionPersonal.domain.usecase.GetUserDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InformacionPersonalViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<InformacionPersonalUiState>(InformacionPersonalUiState.Loading)
    val uiState: StateFlow<InformacionPersonalUiState> = _uiState

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        viewModelScope.launch {
            _uiState.value = InformacionPersonalUiState.Loading
            val currentUser = getCurrentUserUseCase()
            if (currentUser == null) {
                _uiState.value = InformacionPersonalUiState.Error("No user is currently signed in.")
                return@launch
            }

            val result = getUserDataUseCase(currentUser.uid)

            _uiState.value = result.fold(
                onSuccess = { user -> InformacionPersonalUiState.Success(user) },
                onFailure = { error -> InformacionPersonalUiState.Error(error.message ?: "Failed to fetch user data.") }
            )
        }
    }
}

sealed class InformacionPersonalUiState {
    object Loading : InformacionPersonalUiState()
    data class Success(val user: User) : InformacionPersonalUiState()
    data class Error(val message: String) : InformacionPersonalUiState()
}
