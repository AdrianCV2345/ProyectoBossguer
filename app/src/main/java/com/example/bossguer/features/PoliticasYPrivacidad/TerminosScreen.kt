package com.example.bossguer.features.PoliticasYPrivacidad

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R

@Composable
fun TerminosScreen(
    onNavigateToMenu: () -> Unit,
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp), // Space for the bottom bar
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Header() }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { SectionTitle("Soporte tecnico") }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { SelectedCard() }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { TermsContent() }
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item { ContactInfo() }
            item { Spacer(modifier = Modifier.height(32.dp)) }
        }

        // Bottom Navigation Bar
        BottomNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onNavigateToMenu = onNavigateToMenu,
            onNavigateToSobreNosotros = onNavigateToSobreNosotros,
            onNavigateToPerfil = onNavigateToPerfil
        )
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFFA3121C)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Terminos y condiciones",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
}

@Composable
private fun SelectedCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDE5C6))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icono_terminos),
                contentDescription = "Términos y condiciones",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Terminos y condiciones",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun TermsContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Bossguer - Pedidos Online\nÚltima actualización: 28/04/25",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Al usar la app Bossguer, aceptas los siguientes términos:",
            fontSize = 14.sp,
            color = Color.DarkGray
        )
        TermItem(
            iconRes = R.drawable.icono_servicio,
            term = "Servicio",
            description = "Bossguer permite pedir hamburguesas para entrega o retiro…"
        )
        TermItem(
            iconRes = R.drawable.icono_pedidos_pagos,
            term = "Pedidos y pagos",
            description = "precios incluyen impuestos, pagos con métodos indicados…"
        )
        TermItem(
            iconRes = R.drawable.icono_entregas,
            term = "Entregas",
            description = "Tiempos estimados, usuario debe dar datos correctos…"
        )
        TermItem(
            iconRes = R.drawable.icono_devoluciones,
            term = "Devoluciones",
            description = "Reclamos solo por errores o mal estado…"
        )
        TermItem(
            iconRes = R.drawable.icono_cuenta_usuario,
            term = "Cuenta de usuario",
            description = "Usuario responsable de su info y contraseña…"
        )
        TermItem(
            iconRes = R.drawable.icono_propiedad,
            term = "Propiedad",
            description = "Contenidos exclusivos de la empresa…"
        )
         TermItem(
            iconRes = R.drawable.icono_responsabilidad,
            term = "Responsabilidad",
            description = "No garantiza disponibilidad continua del servicio…"
        )
        TermItem(
            iconRes = R.drawable.icono_cambios,
            term = "Cambios",
            description = "Derecho de modificar términos en cualquier momento…"
        )
    }
}

@Composable
private fun TermItem(iconRes: Int, term: String, description: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = term,
            modifier = Modifier.size(22.dp).padding(top = 2.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(term)
                }
                append(": ")
                append(description)
            },
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun ContactInfo() {
    Text(
        text = "Contacto:\ndudas@bossguer.com",
        fontSize = 14.sp,
        color = Color(0xFF2D7A3F),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    )
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    onNavigateToMenu: () -> Unit,
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF79E2E))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(iconRes = R.drawable.ic_nosotros, label = "Sobre Nosotros", onClick = onNavigateToSobreNosotros)
        BottomNavItem(iconRes = R.drawable.ic_menu, label = "Menu", onClick = onNavigateToMenu)
        // Chat icon removed for consistency
        BottomNavItem(iconRes = R.drawable.ic_cuenta, label = "Cuenta", onClick = onNavigateToPerfil, isSelected = true)
    }
}

@Composable
private fun BottomNavItem(iconRes: Int, label: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color(0xFFA3121C) else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(28.dp),
                tint = Color.White
            )
        }
    }
}
