package com.example.bossguer.features.menu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MenuViewModel : ViewModel() {
    // Contadores para cada hamburguesa
    var bossguerCount by mutableStateOf(0)
    var jefazaCount by mutableStateOf(0)
    var ejecutivaCount by mutableStateOf(0)
    var customCount by mutableStateOf(0)

    // Bebidas y extras
    var aguaCount by mutableStateOf(0)
    var jugoCount by mutableStateOf(0)
    var sodaCount by mutableStateOf(0)
    var papasCount by mutableStateOf(0)
    var arosCebollaCount by mutableStateOf(0)

    fun addBossguer() { bossguerCount++ }
    fun removeBossguer() { if (bossguerCount > 0) bossguerCount-- }
    fun addJefaza() { jefazaCount++ }
    fun removeJefaza() { if (jefazaCount > 0) jefazaCount-- }
    fun addEjecutiva() { ejecutivaCount++ }
    fun removeEjecutiva() { if (ejecutivaCount > 0) ejecutivaCount-- }
    fun addCustom() { customCount++ }
    fun removeCustom() { if (customCount > 0) customCount-- }

    fun addAgua() { aguaCount++ }
    fun addJugo() { jugoCount++ }
    fun addSoda() { sodaCount++ }
    fun addPapas() { papasCount++ }
    fun addArosCebolla() { arosCebollaCount++ }
}