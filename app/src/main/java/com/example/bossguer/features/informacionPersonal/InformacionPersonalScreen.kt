package com.example.bossguer.features.informacionPersonal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R
import com.example.bossguer.ui.theme.BossguerTheme

@Composable
fun InformacionPersonalScreen(
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToMenu: () -> Unit,
    onNavigateToPerfil: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp), // Espacio para la barra de navegación
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TopAppBarInfo()
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    // --- SECCIÓN PERFIL ---
                    Text(
                        text = "Perfil",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Nombre de usuario:",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "12345678",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    // --- SECCIÓN MIS DATOS ---
                    Text(
                        text = "Mis datos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Lista de datos personales
            item { InfoRow("CI:", "12345678") }
            item { InfoRow("Nombre:", "Pedro Perez") }
            item { InfoRow("Gmail:", "pedroboss@gmail.com") }
            item { InfoRow("Cumpleaños:", "01/01/2024") }
            item { InfoRow("Género:", "Masculino") }
            item { InfoRow("Ciudad:", "Cochabamba") }
            item { InfoRow("Usuario:", "12345678") }
            item { InfoRow("Contraseña:", "megustalaburguer123") }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                // --- BOTÓN EDITAR DATOS ---
                Button(
                    onClick = { /* TODO: Acción de editar datos */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    border = BorderStroke(1.dp, Color.LightGray)
                ) {
                    Text("Editar datos", color = Color.Black, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // --- BARRA DE NAVEGACIÓN INFERIOR ---
        BottomNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onNavigateToSobreNosotros = onNavigateToSobreNosotros,
            onNavigateToMenu = onNavigateToMenu,
            onNavigateToPerfil = onNavigateToPerfil
        )
    }
}

@Composable
private fun TopAppBarInfo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFFA3121C)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Informacion personal",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .background(Color(0xFFFEF8F2), RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(
                text = value,
                color = Color(0xFFA8A8A8),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToMenu: () -> Unit,
    onNavigateToPerfil: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF79E2E))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            iconRes = R.drawable.ic_nosotros,
            label = "Sobre Nosotros",
            onClick = onNavigateToSobreNosotros
        )
        BottomNavItem(
            iconRes = R.drawable.ic_menu,
            label = "Menú",
            onClick = onNavigateToMenu
        )
        // La cuenta es la sección activa
        BottomNavItem(
            iconRes = R.drawable.ic_cuenta,
            label = "Cuenta",
            onClick = onNavigateToPerfil,
            isSelected = true
        )
    }
}

@Composable
private fun BottomNavItem(
    iconRes: Int,
    label: String,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    IconButton(onClick = onClick) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    if (isSelected) Color(0xFFA3121C) else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InformacionPersonalScreenPreview() {
    BossguerTheme {
        InformacionPersonalScreen({}, {}, {})
    }
}
