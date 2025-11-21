package com.example.bossguer.features.registro.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel,
    onRegistrationSuccess: () -> Unit
) {
    val ci by viewModel.ci.collectAsState()
    val nombre by viewModel.nombre.collectAsState()
    val gmail by viewModel.gmail.collectAsState()
    val usuario by viewModel.usuario.collectAsState()
    val contrasena by viewModel.contrasena.collectAsState()

    // States for new components
    val fechaNacimiento by viewModel.fechaNacimiento.collectAsState()
    val genero by viewModel.genero.collectAsState()
    val ciudad by viewModel.ciudad.collectAsState()
    val registrationState by viewModel.registrationState.collectAsState()

    // UI Control States
    val showDatePicker by viewModel.showDatePicker.collectAsState()
    val ageError by viewModel.ageError.collectAsState()
    val isGenderMenuExpanded by viewModel.isGenderMenuExpanded.collectAsState()
    val isCityMenuExpanded by viewModel.isCityMenuExpanded.collectAsState()

    LaunchedEffect(registrationState) {
        if (registrationState is RegisterUiState.Success) {
            onRegistrationSuccess()
        }
    }

    // --- Date Picker Dialog ---
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { viewModel.onShowDatePicker(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selectedDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                            viewModel.onDateSelected(selectedDate)
                        }
                        viewModel.onShowDatePicker(false)
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.onShowDatePicker(false) }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Llena los\nsiguientes datos",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF5A2A06),
                        lineHeight = 38.sp
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.bossguer_logo),
                    contentDescription = "Logo Bossguer",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            // --- Form Fields ---
            RegistroTextField(label = "C.I", value = ci, onValueChange = viewModel::onCiChange)
            RegistroTextField(label = "Nombre", value = nombre, onValueChange = viewModel::onNombreChange)
            RegistroTextField(label = "Gmail", value = gmail, onValueChange = viewModel::onGmailChange)

            // --- New Interactive Fields ---
            DatePickerField(
                label = "Fecha de Nacimiento",
                value = fechaNacimiento,
                onClick = { viewModel.onShowDatePicker(true) },
                errorMessage = ageError
            )
            DropdownField(
                label = "Género",
                selectedValue = genero,
                options = viewModel.genderOptions,
                expanded = isGenderMenuExpanded,
                onClick = viewModel::onGenderMenuClick,
                onDismissRequest = viewModel::onGenderMenuDismiss,
                onItemSelected = viewModel::onGeneroSelected
            )
            DropdownField(
                label = "Ciudad",
                selectedValue = ciudad,
                options = viewModel.cityOptions,
                expanded = isCityMenuExpanded,
                onClick = viewModel::onCityMenuClick,
                onDismissRequest = viewModel::onCityMenuDismiss,
                onItemSelected = viewModel::onCiudadSelected
            )

            RegistroTextField(label = "Usuario", value = usuario, onValueChange = viewModel::onUsuarioChange)
            RegistroTextField(label = "Contraseña", value = contrasena, onValueChange = viewModel::onContrasenaChange)

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { viewModel.onRegisterClick() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(220.dp)
                    .height(56.dp)
                    .shadow(8.dp, RoundedCornerShape(28.dp)),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB347)),
                // Disable button if there's an age error or fields are empty (optional)
                enabled = ageError == null
            ) {
                Text(
                    text = "¡REGISTRARSE!",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
private fun RegistroTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color(0xFF222222),
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFA726),
                unfocusedBorderColor = Color(0xFFFFA726),
                focusedContainerColor = Color(0xFFF9F6F6),
                unfocusedContainerColor = Color(0xFFF9F6F6)
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun DatePickerField(label: String, value: String, onClick: () -> Unit, errorMessage: String?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color(0xFF222222),
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable { onClick() },
            readOnly = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = if (value.isNotBlank()) Color.Black else Color.Gray,
                focusedBorderColor = Color(0xFFFFA726),
                unfocusedBorderColor = Color(0xFFFFA726),
                focusedContainerColor = Color(0xFFF9F6F6),
                unfocusedContainerColor = Color(0xFFF9F6F6),
                disabledBorderColor = Color(0xFFFFA726),
                disabledContainerColor = Color(0xFFF9F6F6)
            ),
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = "Calendar Icon")
            },
            enabled = false
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun DropdownField(
    label: String,
    selectedValue: String,
    options: List<String>,
    expanded: Boolean,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onItemSelected: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color(0xFF222222),
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable { onClick() },
                readOnly = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = if (selectedValue.isNotBlank()) Color.Black else Color.Gray,
                    focusedBorderColor = Color(0xFFFFA726),
                    unfocusedBorderColor = Color(0xFFFFA726),
                    focusedContainerColor = Color(0xFFF9F6F6),
                    unfocusedContainerColor = Color(0xFFF9F6F6),
                    disabledBorderColor = Color(0xFFFFA726),
                    disabledContainerColor = Color(0xFFF9F6F6)
                ),
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                },
                enabled = false
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismissRequest,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = { onItemSelected(option) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}
