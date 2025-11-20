package com.example.bossguer.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bossguer.features.registro.presentation.RegistroScreen
import com.example.bossguer.features.registro.presentation.RegistroViewModel
import com.example.bossguer.features.carrito.ui.CartViewModel
import com.example.bossguer.features.carrito.ui.OrderScreen
import com.example.bossguer.features.carrito.ui.OrderSuccessScreen
import com.example.bossguer.features.login.presentation.LoginScreen
import com.example.bossguer.features.login.presentation.LoginViewModel
import com.example.bossguer.features.login.presentation.SplashScreen.SplashScreen
import com.example.bossguer.features.loginPart.presentation.LoginPartScreen
import com.example.bossguer.features.loginPart.presentation.LoginPartViewModel
import com.example.bossguer.features.menu.presentation.MenuScreen
import com.example.bossguer.features.menu.presentation.MenuViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val cartViewModel: CartViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(onNavigate = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Login.route) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }
        composable(Screen.LoginPart.route) {
            val loginPartViewModel: LoginPartViewModel = koinViewModel()
            LoginPartScreen(viewModel = loginPartViewModel, navController = navController)
        }
        composable(Screen.Home.route) {

        }


        composable(Screen.Registro.route) {
            val registroViewModel: RegistroViewModel = koinViewModel()
            RegistroScreen(viewModel = registroViewModel)
        }
        composable(Screen.Menu.route) {
            val menuViewModel: MenuViewModel = koinViewModel()
            MenuScreen(
                viewModel = menuViewModel,
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() },
                onCartClick = { navController.navigate(Screen.Order.route) },
                onProductAdded = {}
            )
        }

        composable(Screen.Order.route) {
            OrderScreen(
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() },
                onOrderSuccess = { navController.navigate("orderSuccess") }
            )
        }
        composable("orderSuccess") {
            OrderSuccessScreen(
                onTimeout = {
                    navController.navigate(Screen.Menu.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}