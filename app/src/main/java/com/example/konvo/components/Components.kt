package com.example.konvo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScenarioChips(
    scenarios: List<String>,
    selectedScenario: String?,
    onScenarioSelected: (String) -> Unit
) {
    scenarios.forEach { scenario ->
        ElevatedFilterChip(
            selected = selectedScenario == scenario,
            onClick = { onScenarioSelected(scenario) },
            label = { Text(scenario) },
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .widthIn(max = 300.dp)
        ) {
            Text(
                text = message,
                color = if (isUser) Color.White else Color.Black,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
