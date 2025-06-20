package brawijaya.example.tuteedaftarkelas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas.DaftarKelasScreen
import brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas.SuccessBookScreen

sealed class Screen (val route: String) {
    object DaftarKelas : Screen("daftar_kelas")
    object SuccessBook : Screen("success_book")
}

@Composable
fun AppNavigation (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.DaftarKelas.route
    ) {
        composable(Screen.DaftarKelas.route) {
            DaftarKelasScreen(navController = navController)
        }
        composable(Screen.SuccessBook.route) {
            SuccessBookScreen(navController = navController)
        }
    }
}