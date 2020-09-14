package com.dmgdavid2109.klotskihuma.puzzle.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmgdavid2109.klotskihuma.puzzle.domain.model.Board
import com.dmgdavid2109.klotskihuma.puzzle.domain.usecase.InitialiseBoardUseCase
import com.dmgdavid2109.klotskihuma.puzzle.domain.usecase.SolvePuzzleUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class PuzzleViewModel @Inject constructor(
    private val initialiseBoardUseCase: InitialiseBoardUseCase,
    private val solvePuzzleUseCase: SolvePuzzleUseCase
) : ViewModel() {

    private val _viewState = ViewStateLiveData(PuzzleViewState())
    val viewState: LiveData<PuzzleViewState>
        get() = _viewState

    private var board: Board = initialiseBoardUseCase.invoke()

    init {
        _viewState.updateCurrentState {
            copy(array = board.drawBoard(), buttonEnabled = true)
        }
    }

    fun solveBoard() {
        _viewState.updateCurrentState {
            copy(array = board.drawBoard(), buttonEnabled = false, showProgressBar = true)
        }
        val steps = solvePuzzleUseCase.invoke(board)
        showSteps(steps)
    }

    private fun showSteps(steps: List<Board>) {
        viewModelScope.launch {
            steps.forEach {
                _viewState.updateCurrentState {
                    copy(array = it.drawBoard(), buttonEnabled = false, showProgressBar = false)
                }
                delay(300)
            }

            _viewState.updateCurrentState {
                copy(buttonEnabled = true)
            }
        }
    }
}
