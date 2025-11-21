package com.example.bossguer.features.registro.presentation

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel,
    onRegistrationSuccess: () -> Unit
) {
    val ci by viewModel.ci.collectAsState()
    val nombre by viewModel.nombre.collectAsState()
    val gmail by viewModel.gmail.collectAsState()
    val fechaNacimiento by viewModel.fechaNacimiento.collectAsState()
    val genero by viewModel.genero.collectAsState()
    val ciudad by viewModel.ciudad.collectAsState()
    val usuario by viewModel.usuario.collectAsState()
    val contrasena by viewModel.contrasena.collectAsState()

    val registrationState by viewModel.registrationState.collectAsState()

    LaunchedEffect(registrationState) {
        if (registrationState is RegisterUiState.Success) {
            onRegistrationSuccess()
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
            RegistroTextField(label = "C.I", value = ci, onValueChange = viewModel::onCiChange)
            RegistroTextField(label = "Nombre", value = nombre, onValueChange = viewModel::onNombreChange)
            RegistroTextField(label = "Gmail", value = gmail, onValueChange = viewModel::onGmailChange)
            RegistroTextField(label = "Fecha de Nacimiento", value = fechaNacimiento, onValueChange = viewModel::onFechaNacimientoChange)
            RegistroTextField(label = "Género", value = genero, onValueChange = viewModel::onGeneroChange)
            RegistroTextField(label = "Ciudad", value = ciudad, onValueChange = viewModel::onCiudadChange)
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
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB347))
            ) {
                Text(
                    text = "¡REGISTRARSE!",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun RegistroTextField(label: String, value: String, onValueChange: (String) -> Unit) {
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
