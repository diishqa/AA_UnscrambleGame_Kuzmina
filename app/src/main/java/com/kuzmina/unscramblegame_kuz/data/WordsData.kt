package com.kuzmina.unscramblegame_kuz.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

const val MAX_NO_OF_WORDS = 10
const val SCORE_INCREASE =20
val allWords: List<String> = listOf(
    "android", "kotlin", "compose", "jetpack", "viewmodel", "coroutine", "firebase",
    "material", "database", "network", "retrofit", "room", "navigation", "lifecycle",
    "dependency", "injection", "testing", "debugging", "gradle", "studio"
)