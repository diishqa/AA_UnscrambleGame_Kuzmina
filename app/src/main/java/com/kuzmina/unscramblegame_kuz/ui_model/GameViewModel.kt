package com.kuzmina.unscramblegame_kuz.ui_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kuzmina.unscramblegame_kuz.data.GameUiState
import com.kuzmina.unscramblegame_kuz.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()
    private fun shuffleCurrentWord(word: String): String{
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while (String(tempWord) == word){
            tempWord.shuffle()
        }
        return String(tempWord)
    }
    private fun pickRandomWordShuffle(): String {
        currentWord = allWords.random()
        while (usedWords.contains(currentWord)) {
            currentWord = allWords.random()
        }
        usedWords.add(currentWord)
        return shuffleCurrentWord((currentWord))
    }
    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(
            currentScrambledWord = pickRandomWordShuffle()
        )
    }
    init {
        resetGame()
    }

}