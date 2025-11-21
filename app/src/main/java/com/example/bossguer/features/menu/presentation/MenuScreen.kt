package com.example.bossguer.features.menu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R
import com.example.bossguer.features.carrito.data.Product
import com.example.bossguer.features.carrito.ui.CartViewModel

// Data class para representar una hamburguesa, facilita la gestión
data class Burger(val name: String, val desc: String, val price: String, val imageRes: Int)

@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    cartViewModel: CartViewModel,
    onBack: () -> Unit,
    onCartClick: () -> Unit,
    onProductAdded: () -> Unit
) {
    // Lista de hamburguesas (puedes mover esto a tu ViewModel)
    val burgers = listOf(
        Burger("Bossguer", "La original, inigualable e inconfundible.", "35,00 Bs.", R.drawable.burger_bossguer),
        Burger("La Jefaza", "Cargada con todo, solo para los verdaderos jefes.", "37,00 Bs.", R.drawable.burger_jefaza),
        Burger("La Ejecutiva", "Elegante, potente y llena de poder.", "37,00 Bs.", R.drawable.burger_ejecutiva),
        Burger("Arma La Tuya!", "Hoy, tú eres el Boss!", "35,00 Bs.", R.drawable.burger_custom)
    )
    val cartState by cartViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Usamos LazyColumn para un rendimiento óptimo en listas largas y para facilitar el espaciado
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp), // Padding para no superponer con la barra inferior
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp) // 1. AUMENTADA LA SEPARACIÓN ENTRE ELEMENTOS
        ) {
            item {
                // Contenedor para la imagen principal y los encabezados
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu_header),
                        contentDescription = "Menu header",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // 3. ENCABEZADO CENTRADO
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "NUESTRO MENÚ",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Pedí tu Boss Favorita!",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                    // Barra de navegación superior
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF4D8CD).copy(alpha = 0.85f))
                            .align(Alignment.TopCenter)
                            .statusBarsPadding(), // <- AQUÍ ESTÁ LA SOLUCIÓN
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Atrás",
                                tint = Color(0xFF5A2A06),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.bossguer_logo),
                            contentDescription = "Logo Bossguer",
                            modifier = Modifier.size(56.dp)
                        )
                        IconButton(onClick = onCartClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cart),
                                contentDescription = "Carrito",
                                tint = Color(0xFF5A2A06),
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }
            item {
                // Título de la sección "HAMBURGUESAS"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF8C1C1C))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "HAMBURGUESAS",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            // Lista de tarjetas de hamburguesas
            items(burgers) { burger ->
                val product = Product(
                    id = burger.name, // Using name as ID is not ideal, but works for this scope
                    name = burger.name,
                    price = burger.price.replace(",", ".").replace(" Bs.", "").trim().toDouble(),
                    image = burger.imageRes
                )
                val quantity = cartState.items.find { it.product.id == product.id }?.quantity ?: 0

                MenuBurgerCard(
                    burger = burger,
                    quantityInCart = quantity,
                    onAddToCart = {
                        cartViewModel.addProduct(product)
                        onProductAdded()
                    },
                    onRemoveFromCart = {
                        cartViewModel.removeProduct(product)
                    }
                )
            }

            item {
                // Sección final con aclaración, bebidas y extras
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ACLARACIÓN: Todas nuestras Boss incluyen porción de papas :)",
                        fontSize = 12.sp,
                        color = Color(0xFF5A2A06),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF8C1C1C))
                    ) {
                        Text(
                            text = "BEBIDAS",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                    MenuDrinkRow(name = "Agua", price = "9,00 Bs.", onAddToCart = {
                        val product = Product(id = "Agua", name = "Agua", price = 9.0, image = R.drawable.ic_agua)
                        cartViewModel.addProduct(product)
                    })
                    MenuDrinkRow(name = "Jugo", price = "7,00 Bs.", onAddToCart = {
                        val product = Product(id = "Jugo", name = "Jugo", price = 7.0, image = R.drawable.ic_jugo)
                        cartViewModel.addProduct(product)
                    })
                    MenuDrinkRow(name = "Soda", price = "9,00 Bs.", onAddToCart = {
                        val product = Product(id = "Soda", name = "Soda", price = 9.0, image = R.drawable.ic_soda)
                        cartViewModel.addProduct(product)
                    })
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF387C3B))
                    ) {
                        Text(
                            text = "EXTRAS",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                    MenuDrinkRow(name = "Papas", price = "9,00 Bs.", onAddToCart = {
                        val product = Product(id = "Papas", name = "Papas", price = 9.0, image = R.drawable.ic_papas)
                        cartViewModel.addProduct(product)
                    })
                    MenuDrinkRow(name = "Aros de Cebolla", price = "9,00 Bs.", onAddToCart = {
                        val product = Product(id = "Aros de Cebolla", name = "Aros de Cebolla", price = 9.0, image = R.drawable.ic_aros_de_cebolla)
                        cartViewModel.addProduct(product)
                    })
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
        // Barra de navegación inferior fija
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color(0xFFFFB347))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(R.drawable.ic_nosotros, "")
            BottomNavItem(R.drawable.ic_menu, "")
            BottomNavItem(R.drawable.ic_chat, "")
            BottomNavItem(R.drawable.ic_cuenta, "")
        }
    }
}

@Composable
fun MenuBurgerCard(
    burger: Burger,
    quantityInCart: Int,
    onAddToCart: () -> Unit,
    onRemoveFromCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Padding horizontal para el card
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = burger.imageRes),
                contentDescription = burger.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp) // Espaciado interno
            ) {
                Text(
                    text = burger.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = burger.desc,
                    fontSize = 14.sp,
                    color = Color(0xFF555555) // Gris oscuro
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = burger.price,
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )

                    // 2. CONTADOR DE CANTIDAD REDISEÑADO
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onRemoveFromCart,
                            enabled = quantityInCart > 0,
                            modifier = Modifier.size(38.dp), // Botón más grande
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)), // Fondo amarillo
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_minus),
                                contentDescription = "Menos",
                                tint = Color.Black ,// Ícono negro
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Text(
                            text = quantityInCart.toString(),
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Button(
                            onClick = onAddToCart,
                            modifier = Modifier.size(38.dp), // Botón más grande
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)), // Fondo amarillo
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_plus),
                                contentDescription = "Más",
                                tint = Color.Black, // Ícono negro
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuDrinkRow(name: String, price: String, onAddToCart: () -> Unit) { // <-- 1. AÑADIDO onAddToCart
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            color = Color(0xFF222222),
            fontWeight = FontWeight.Normal
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = price,
                fontSize = 16.sp,
                color = Color(0xFF5A2A06),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            IconButton(onClick = onAddToCart) { // <-- 2. USADO onAddToCart
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Agregar",
                    tint = Color(0xFFFFB347),
                    modifier =Modifier.size(24.dp)

                )
            }
        }
    }
}

@Composable
fun BottomNavItem(iconRes: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(Color(0xFFFFB347), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {

            IconButton(onClick = { /* TODO: Agregar */ }) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = label,
                    modifier = Modifier.size(50.dp),
                    tint = Color.White

                )
            }

        }

    }
}
