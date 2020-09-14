package com.dmgdavid2109.klotskihuma.puzzle.domain.model

enum class Side(val dx: Int, val dy: Int) {
    LEFT(-1, 0), TOP(0, -1), RIGHT(1, 0), BOTTOM(0, 1)
}
