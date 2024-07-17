package cl.inventionchile.codemaker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.inventionchile.codemaker.R
import cl.inventionchile.codemaker.ui.components.MyAnimationLottie
import cl.inventionchile.codemaker.ui.screens.home.HomeScreen
import cl.inventionchile.codemaker.ui.screens.login.LoginScreen
import cl.inventionchile.codemaker.ui.theme.CodeMakerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm by viewModels<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeMakerTheme {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (vm.isLoading.value){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            MyAnimationLottie(
                                modifier = Modifier.size(200.dp),
                                animation = R.raw.anim_loading
                            )
                        }
                    }else{
                        NavHost(
                            navController = navController,
                            startDestination = if (vm.user.value == null) Destinations.LOGIN
                            else Destinations.HOME
                        ){
                            composable(
                                route = Destinations.LOGIN
                            ){
                                LoginScreen(
                                    onHome = { navController.navigate(Destinations.HOME) }
                                )
                            }
                            composable(
                                route = Destinations.HOME
                            ){
                                HomeScreen(
                                    onBack = {
                                        navController.navigate(Destinations.LOGIN) {
                                            popUpTo(Destinations.HOME) { inclusive = true }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}