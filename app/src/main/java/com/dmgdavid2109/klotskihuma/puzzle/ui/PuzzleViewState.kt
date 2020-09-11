package com.dmgdavid2109.klotskihuma.puzzle.ui

data class PuzzleViewState(
    val array: Array<CharArray> = emptyArray(),
    var showProgressBar: Boolean = false,
    val buttonEnabled: Boolean = true
)
