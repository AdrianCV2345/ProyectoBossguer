package com.example.bossguer.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bossguer.features.SobreNosotros.presentation.SobreNosotrosScreen
import com.example.bossguer.features.PoliticasYPrivacidad.TerminosScreen
import com.example.bossguer.features.carrito.ui.CartViewModel
import com.example.bossguer.features.carrito.ui.OrderScreen
import com.example.bossguer.features.carrito.ui.OrderSuccessScreen
import com.example.bossguer.features.informacionPersonal.InformacionPersonalScreen
import com.example.bossguer.features.informacionPersonal.presentation.InformacionPersonalViewModel
import com.example.bossguer.features.login.presentation.LoginScreen
import com.example.bossguer.features.login.presentation.LoginViewModel
import com.example.bossguer.features.login.presentation.SplashScreen.SplashScreen
import com.example.bossguer.features.loginPart.presentation.LoginPartScreen
import com.example.bossguer.features.loginPart.presentation.LoginPartViewModel
import com.example.bossguer.features.logout.presentation.LogoutSplashScreen
import com.example.bossguer.features.menu.presentation.MenuScreen
import com.example.bossguer.features.menu.presentation.MenuViewModel
import com.example.bossguer.features.perfil.presentation.PerfilScreen
import com.example.bossguer.features.registro.presentation.RegistroScreen
import com.example.bossguer.features.registro.presentation.RegistroViewModel
import com.example.bossguer.features.registro.presentation.SplashScreenRegister.RegisterSplashScreen
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
            RegistroScreen(
                viewModel = registroViewModel,
                onRegistrationSuccess = {
                    navController.navigate(Screen.RegisterSuccessSplash.route)
                }
            )
        }

        composable(Screen.RegisterSuccessSplash.route) {
            RegisterSplashScreen(
                onNavigateToMenu = {
                    navController.navigate(Screen.Menu.route) {
                        popUpTo(Screen.Login.route)
                    }
                }
            )
        }

        composable(Screen.Menu.route) {
            val menuViewModel: MenuViewModel = koinViewModel()
            MenuScreen(
                viewModel = menuViewModel,
                cartViewModel = cartViewModel,
                onBack = { navController.popBackStack() },
                onCartClick = { navController.navigate(Screen.Order.route) },
                onProductAdded = {},
                onNavigateToSobreNosotros = { navController.navigate(Screen.SobreNosotros.route) },
                onNavigateToMenu = { /* Ya estamos aquí */ },
                onNavigateToPerfil = { navController.navigate(Screen.Perfil.route) }
            )
        }

        composable(Screen.SobreNosotros.route) {
            SobreNosotrosScreen(
                onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                onNavigateToSobreNosotros = { /* Ya estamos aquí */ },
                onNavigateToPerfil = { navController.navigate(Screen.Perfil.route) }
            )
        }

        composable(Screen.Perfil.route) {
            PerfilScreen(
                 onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                 onNavigateToSobreNosotros = { navController.navigate(Screen.SobreNosotros.route) },
                 onNavigateToPerfil = { /* Ya estamos aquí */ },
                 onNavigateToTerminos = { navController.navigate(Screen.Terminos.route) },
                 onLogout = { navController.navigate(Screen.LogoutSplash.route) },
                 onNavigateToInformacionPersonal = { navController.navigate(Screen.InformacionPersonal.route) }
            )
        }

        composable(Screen.LogoutSplash.route) {
            LogoutSplashScreen(onTimeout = {
                navController.navigate(Screen.Login.route) {
                    // Limpia toda la pila de navegación hasta el inicio
                    popUpTo(0)
                }
            })
        }

        composable(Screen.Terminos.route) {
            TerminosScreen(
                onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                onNavigateToSobreNosotros = { navController.navigate(Screen.SobreNosotros.route) },
                onNavigateToPerfil = { navController.navigate(Screen.Perfil.route) }
            )
        }

        composable(Screen.InformacionPersonal.route) {
            val viewModel: InformacionPersonalViewModel = koinViewModel()
            InformacionPersonalScreen(
                 viewModel = viewModel,
                 onNavigateToMenu = { navController.navigate(Screen.Menu.route) },
                 onNavigateToSobreNosotros = { navController.navigate(Screen.SobreNosotros.route) },
                 onNavigateToPerfil = { navController.navigate(Screen.Perfil.route) }
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
