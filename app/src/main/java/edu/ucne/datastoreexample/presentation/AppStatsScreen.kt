package edu.ucne.datastoreexample.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AppStatsScreen(
    viewModel: AppStatsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AppStatsBodyScreen(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppStatsBodyScreen(state: AppStatsUiState) {
    val dateFormatter = remember {
        SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("DataStore Example") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Estadísticas de la App",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    HorizontalDivider()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Veces abierta:",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            "${state.openCount}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Última apertura:",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            if (state.lastOpenedTimestamp == 0L) "Nunca"
                            else dateFormatter.format(Date(state.lastOpenedTimestamp)),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppStatsBodyScreenPreview() {
    MaterialTheme {
        AppStatsBodyScreen(
            state = AppStatsUiState(
                openCount = 5,
                lastOpenedTimestamp = System.currentTimeMillis()
            )
        )
    }
}