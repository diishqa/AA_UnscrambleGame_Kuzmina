package com.kuzmina.unscramblegame_kuz.ui_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kuzmina.unscramblegame_kuz.data.GameUiState
import com.kuzmina.unscramblegame_kuz.data.MAX_NO_OF_WORDS
import com.kuzmina.unscramblegame_kuz.data.SCORE_INCREASE
import com.kuzmina.unscramblegame_kuz.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()
    var userGuess by mutableStateOf("")
        private set
    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }
    private fun updateGameState(updateScore: Int){
        if (usedWords.size === MAX_NO_OF_WORDS) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updateScore,
                    isGameOver = true
                )
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordShuffle(),
                    score = updateScore,
                    currentWordCount = currentState.currentWordCount + 1
                )
            }
        }
    }
    fun checkUserGuess(){

    }
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