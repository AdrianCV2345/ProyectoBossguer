package com.example.bossguer.features.perfil.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R

@Composable
fun PerfilScreen(
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToMenu: () -> Unit,
    onNavigateToPerfil: () -> Unit
    // onLogoutClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 80.dp), // Espacio para la barra de navegación
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { PerfilTopAppBar() }

            // SECCIÓN 2: Imagen de portada
            item {
                Image(
                    painter = painterResource(id = R.drawable.imagen_hamburguesa_header),
                    contentDescription = "Hamburguesa Header",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // SECCIÓN 3: Bloque “Perfil”
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Perfil",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ProfileOptionCard(
                        text = "Información personal",
                        iconRes = R.drawable.icono_informacion,
                        onClick = { /* TODO: Navegar a info personal */ }
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // SECCIÓN 4: Bloque “Soporte técnico”
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Soporte tecnico",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ProfileOptionCard(
                        text = "Terminos y condiciones",
                        iconRes = R.drawable.icono_terminos,
                        onClick = { /* TODO: Navegar a terminos */ }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ProfileOptionCard(
                        text = "Políticas y privacidad",
                        iconRes = R.drawable.icono_politicas,
                        onClick = { /* TODO: Navegar a politicas */ }
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // SECCIÓN 5: Botón “Cerrar sesión”
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable { /* onLogoutClick() */ }
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cerrar sesión",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }

        // Barra de navegación inferior
        BottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onNavigateToMenu = onNavigateToMenu,
            onNavigateToSobreNosotros = onNavigateToSobreNosotros,
            onNavigateToPerfil = onNavigateToPerfil
        )
    }
}

@Composable
fun PerfilTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp) // Aprox 10%
            .background(Color(0xFFA3121C))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_sobre_nosotros),
            contentDescription = "Logo Izquierda",
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = "Mi cuenta",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.logo_sobre_nosotros),
            contentDescription = "Logo Derecha",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun ProfileOptionCard(
    text: String,
    iconRes: Int,
    onClick: () -> Unit,
    backgroundColor: Color = Color.White
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = Color.Unspecified // Usa el color original del icono
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun BottomNav(
    modifier: Modifier = Modifier,
    onNavigateToMenu: () -> Unit,
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFFB347))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(iconRes = R.drawable.ic_nosotros, label = "Sobre Nosotros", onClick = onNavigateToSobreNosotros)
        BottomNavItem(iconRes = R.drawable.ic_menu, label = "Menu", onClick = onNavigateToMenu)
        BottomNavItem(iconRes = R.drawable.ic_cuenta, label = "Cuenta", onClick = onNavigateToPerfil)
    }
}

@Composable
private fun BottomNavItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(Color(0xFFFFB347), shape = CircleShape)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(50.dp),
                tint = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    PerfilScreen(onNavigateToSobreNosotros = {}, onNavigateToMenu = {}, onNavigateToPerfil = {})
}
