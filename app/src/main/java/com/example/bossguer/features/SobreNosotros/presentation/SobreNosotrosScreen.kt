package com.example.bossguer.features.SobreNosotros.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R

@Composable
fun SobreNosotrosScreen(
    onNavigateToMenu: () -> Unit,
    onNavigateToSobreNosotros: () -> Unit,
    onNavigateToPerfil: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 80.dp) 
        ) {
            item {
                Header()
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.imagen_equipo),
                    contentDescription = "Equipo BossGuer",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                TextContent(
                    text = "BossGuer es sabor con actitud. No somos solo una hamburguesería, somos una experiencia gourmet con calle. Usamos ingredientes frescos, técnicas de calidad y un estilo urbano que se siente en cada detalle.\n\nNuestras hamburguesas están pensadas para quienes comen sin miedo, con personalidad, con hambre de algo diferente. No importa si vienes a comer rápido o a quedarte a vibear: el sabor es lo que manda. Si eres un Bossguer es para los que comen como verdaderos jefes."
                )
            }

            item {
                SectionTitleBar(title = "VISION", color = Color(0xFFF4A623))
            }
            item {
                Image(
                    painter = painterResource(id = R.drawable.imagen_vision),
                    contentDescription = "Visión BossGuer",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                TextContent(
                    text = "Nuestra visión es convertirnos en la hamburguesería líder en estilo urbano, donde cada bocado no sea solo una comida, sino una experiencia que te haga sentir como un verdadero boss. Queremos que cada persona que entre a nuestros locales viva un espacio con sabor, actitud y estilo callejero en cada hamburguesa."
                )
            }

            item {
                SectionTitleBar(title = "MISION", color = Color(0xFFF7A441))
            }
            item {
                Image(
                    painter = painterResource(id = R.drawable.imagen_mision),
                    contentDescription = "Misión BossGuer",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                TextContent(
                    text = "Nuestra misión es servir hamburguesas gourmet con actitud, hechas con ingredientes frescos y técnicas especiales, ofreciendo un toque único con pasión. Cada mordida tiene un propósito: que no solo disfrutes el sabor, sino que también sientas la diferencia desde el primer mordisco. No seguimos tendencias, las creamos."
                )
            }
        }

        BottomNav(
            modifier = Modifier.align(Alignment.BottomCenter),
            onNavigateToMenu = onNavigateToMenu,
            onNavigateToSobreNosotros = onNavigateToSobreNosotros,
            onNavigateToPerfil = onNavigateToPerfil
        )
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFA3121C))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_sobre_nosotros),
            contentDescription = "Logo Sobre Nosotros",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Sobre nosotros",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun TextContent(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(24.dp),
        textAlign = TextAlign.Justify,
        fontSize = 16.sp,
        color = Color.Black
    )
}

@Composable
private fun SectionTitleBar(title: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color)
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
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
            .background(Color(0xFFF79E2E))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(iconRes = R.drawable.ic_nosotros, label = "Sobre nosotros", onClick = onNavigateToSobreNosotros)
        BottomNavItem(iconRes = R.drawable.ic_menu, label = "Menú", onClick = onNavigateToMenu)
        BottomNavItem(iconRes = R.drawable.ic_cuenta, label = "perfil", onClick = onNavigateToPerfil)
    }
}

@Composable
private fun BottomNavItem(iconRes: Int, label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(Color(0xFFFFB347), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(28.dp),
                tint = Color.White
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SobreNosotrosScreenPreview() {
    SobreNosotrosScreen(onNavigateToMenu = {}, onNavigateToSobreNosotros = {}, onNavigateToPerfil = {})
}
