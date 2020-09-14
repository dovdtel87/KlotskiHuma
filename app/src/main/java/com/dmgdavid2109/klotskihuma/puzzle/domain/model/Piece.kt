package com.dmgdavid2109.klotskihuma.puzzle.domain.model

data class Piece(
    val x: Int, val y: Int, val w: Int, val h: Int,
    val type: Char, val name: Char, val index: Int
) {

    val canMove get() = name != 'Z' && name != 'X'

    inline val left get() = x
    inline val top get() = y
    inline val right get() = x + w - 1
    inline val bottom get() = y + h - 1

    fun contains(x: Int, y: Int): Boolean {
        return x >= this.x && x < this.x + this.w
                && y >= this.y && y < this.y + this.h
    }

    fun contains(piece: Piece): Boolean {
        return contains(piece.left, piece.top)
                && contains(piece.right, piece.top)
                && contains(piece.left, piece.bottom)
                && contains(piece.right, piece.bottom)
    }

    fun intersects(piece: Piece): Boolean {
        return contains(piece.left, piece.top)
                || contains(piece.right, piece.top)
                || contains(piece.left, piece.bottom)
                || contains(piece.right, piece.bottom)
    }

    fun offset(direction: Side, times: Int = 1): Piece {
        return this.copy(
            x = this.x + direction.dx * times,
            y = this.y + direction.dy * times
        )
    }
}
