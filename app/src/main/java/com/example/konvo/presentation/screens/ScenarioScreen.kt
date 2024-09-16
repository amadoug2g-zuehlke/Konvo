package com.example.konvo.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.konvo.components.ScenarioChips
import com.example.konvo.presentation.viewmodel.KonvoViewModel

/*
@Composable
fun ScenarioScreen(
    viewModel: KonvoViewModel,
    onScenarioSelected: () -> Unit
) {
    val scenarios = listOf("Restaurant", "Mall", "Dentist", "Party")
    val languages = listOf("English", "French", "Spanish", "German")
    val selectedScenario by viewModel.selectedScenario.collectAsState()
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Select a Scenario", style = MaterialTheme.typography.bodySmall)
        ScenarioChips(
            scenarios = scenarios,
            selectedScenario = selectedScenario,
            onScenarioSelected = viewModel::setScenario
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Select a Language", style = MaterialTheme.typography.bodySmall)
        ScenarioChips(
            scenarios = languages,
            selectedScenario = selectedLanguage,
            onScenarioSelected = viewModel::setLanguage
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    viewModel.initializeAssistant()
                },
                enabled = selectedScenario.isNotEmpty() && selectedLanguage.isNotEmpty()
            ) {
                Text("Initialise Thread")
            }

            Button(
                onClick = {
                    onScenarioSelected()
                },
                enabled = selectedScenario.isNotEmpty() && selectedLanguage.isNotEmpty()
            ) {
                Text("Start Conversation")
            }
        }
    }
}
*/
