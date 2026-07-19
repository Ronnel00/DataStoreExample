package edu.ucne.datastoreexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.datastoreexample.presentation.AppStatsScreen
import edu.ucne.datastoreexample.ui.theme.DataStoreExampleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStoreExampleTheme {
                AppStatsScreen()
            }
        }
    }
}