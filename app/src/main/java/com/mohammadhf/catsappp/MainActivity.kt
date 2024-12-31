package com.mohammadhf.catsappp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mohammadhf.catdetails.BreedDetailsRoute
import com.mohammadhf.catslist.CatsListRoute
import com.mohammadhf.coreui.theme.CatsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = CatsList) {
                    composable<CatsList> {
                        CatsListRoute(
                            onNavigateToDetails = { breedId ->
                                navController.navigate(BreedDetails(breedId))
                            },
                            launchToast = {}
                        )
                    }

                    composable<BreedDetails> {
                        val args = it.toRoute<BreedDetails>()
                        BreedDetailsRoute(
                            breedId = args.breedId,
                            onNavigateBack = {},
                            launchToast = {}
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object CatsList

@Serializable
data class BreedDetails(val breedId: String)