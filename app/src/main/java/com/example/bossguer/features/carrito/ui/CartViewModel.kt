package com.example.bossguer.features.carrito.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bossguer.features.carrito.data.CartItem
import com.example.bossguer.features.carrito.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val total: Double = 0.0
)

class CartViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    fun addProduct(product: Product) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                val existingItem = currentState.items.find { it.product.id == product.id }
                val updatedItems = if (existingItem != null) {
                    currentState.items.map {
                        if (it.product.id == product.id) {
                            it.copy(quantity = it.quantity + 1)
                        } else {
                            it
                        }
                    }
                } else {
                    currentState.items + CartItem(product, 1)
                }
                currentState.copy(
                    items = updatedItems,
                    total = calculateTotal(updatedItems)
                )
            }
        }
    }

    fun removeProduct(product: Product) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                val existingItem = currentState.items.find { it.product.id == product.id }
                val updatedItems = if (existingItem != null && existingItem.quantity > 1) {
                    currentState.items.map {
                        if (it.product.id == product.id) {
                            it.copy(quantity = it.quantity - 1)
                        } else {
                            it
                        }
                    }
                } else {
                    currentState.items.filterNot { it.product.id == product.id }
                }
                currentState.copy(
                    items = updatedItems,
                    total = calculateTotal(updatedItems)
                )
            }
        }
    }

    private fun calculateTotal(items: List<CartItem>): Double {
        return items.sumOf { it.product.price * it.quantity }
    }
}
