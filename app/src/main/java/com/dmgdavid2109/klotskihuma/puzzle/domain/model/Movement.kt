package com.dmgdavid2109.klotskihuma.puzzle.domain.model

data class Movement(val piece: Piece, val side: Side, val steps: Int = 1)
