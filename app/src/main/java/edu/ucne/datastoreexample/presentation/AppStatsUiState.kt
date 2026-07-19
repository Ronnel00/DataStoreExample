package edu.ucne.datastoreexample.presentation

data class AppStatsUiState(
    val openCount: Int = 0,
    val lastOpenedTimestamp: Long = 0L
)