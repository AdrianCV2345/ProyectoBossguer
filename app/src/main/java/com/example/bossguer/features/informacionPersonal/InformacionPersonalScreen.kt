package com.example.bossguer.features.informacionPersonal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R
import com.example.bossguer.features.informacionPersonal.domain.model.User
import com.example.bossguer.features.informacionPersonal.presentation.InformacionPersonalUiState
import com.example.bossguer.features.informacionPersonal.presentation.InformacionPersonalViewModel

@Composable
fun InformacionPersonalScreen(
    viewModel: InformacionPersonalViewModel,
    onNavigateToMenu: () -> Unit,
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onNavigateToMenu = onNavigateToMenu,
                onNavigateToSobreNosotros = onNavigateToSobreNosotros,
                onNavigateToPerfil = onNavigateToPerfil
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when (val state = uiState) {
                is InformacionPersonalUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is InformacionPersonalUiState.Error -> {
                    Text(
                        text = "Error: ${state.message}",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is InformacionPersonalUiState.Success -> {
                    InformacionPersonalContent(user = state.user)
                }
            }
        }
    }
}

@Composable
fun InformacionPersonalContent(user: User) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item { Header() }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { ProfileSection(username = user.usuario) }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { MyDataSection(user = user) }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { EditDataButton() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFFA3121C)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Informacion personal",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileSection(username: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = "Perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Nombre de usuario:", fontSize = 16.sp, color = Color.Black)
        Text(text = username, fontSize = 16.sp, color = Color.Gray)
    }
}

@Composable
fun MyDataSection(user: User) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = "Mis datos", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        DataField(label = "CI:", value = user.ci)
        DataField(label = "Nombre:", value = user.nombre)
        DataField(label = "Gmail:", value = user.gmail)
        DataField(label = "Cumpleaños:", value = user.fechaNacimiento)
        DataField(label = "Género:", value = user.genero)
        DataField(label = "Ciudad:", value = user.ciudad)
        DataField(label = "Usuario:", value = user.usuario)
        DataField(label = "Contraseña:", value = "●●●●●●●●●●") // Never show the real password
    }
}

@Composable
fun DataField(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFFFEF8F2), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = label, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.weight(0.4f))
            Text(text = value, color = Color(0xFFA8A8A8), modifier = Modifier.weight(0.6f))
        }
    }
}

@Composable
fun EditDataButton() {
    Button(
        onClick = { /* TODO: Implement edit functionality */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp, pressedElevation = 2.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Text(text = "Editar datos", color = Color.Black, fontSize = 16.sp)
    }
}

@Composable
fun BottomNavigationBar(
    onNavigateToMenu: () -> Unit,
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color(0xFFF79E2E)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(iconRes = R.drawable.ic_nosotros, label = "Sobre Nosotros", onClick = onNavigateToSobreNosotros)
        BottomNavItem(iconRes = R.drawable.ic_menu, label = "Menú", onClick = onNavigateToMenu)
        BottomNavItem(iconRes = R.drawable.ic_cuenta, label = "Cuenta", onClick = onNavigateToPerfil, isSelected = true)
    }
}

@Composable
fun BottomNavItem(iconRes: Int, label: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(30.dp),
            colorFilter = if (isSelected) ColorFilter.tint(Color(0xFFA3121C)) else null
        )
        Text(text = label, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}
