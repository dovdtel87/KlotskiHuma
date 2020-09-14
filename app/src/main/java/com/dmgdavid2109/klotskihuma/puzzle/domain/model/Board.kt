package com.dmgdavid2109.klotskihuma.puzzle.domain.model

data class Board(
    val width: Int,
    val height: Int,
    val pieces: List<Piece>,
    val movements: List<Movement> = emptyList(),
    val end: Piece,
    val indexOfMain: Int,
    val parent: Board? = null
) {
    fun canMove(piece: Piece, direction: Side): Boolean {
        if (!piece.canMove) return false
        val moved = piece.offset(direction)
        if (moved.left < 0
            || moved.right >= width
            || moved.top < 0
            || moved.bottom >= height
        ) {
            return false
        }

        val collisionWithAny = pieces.any {
            if (it.index == piece.index) false //It's same piece
            else it.intersects(moved)
        }

        val collisionWithEnd = moved.intersects(end) && moved.type != '#'

        return !(collisionWithAny || collisionWithEnd)
    }

    fun isSolved(): Boolean {
        return pieces[indexOfMain].contains(end)
    }

    fun movePiece(movement: Movement): Board {
        val newPieces = pieces.toMutableList()
        val oldPiece = pieces[movement.piece.index]
        val newPiece = oldPiece.offset(movement.side, movement.steps)
        newPieces[movement.piece.index] = newPiece
        val newMovements = movements.toMutableList()
        val lastMovement = newMovements.lastOrNull()

        if (lastMovement != null
            && lastMovement.side == movement.side
            && lastMovement.piece.index == movement.piece.index
        ) {
            newMovements[newMovements.lastIndex] = lastMovement.copy(steps = lastMovement.steps + 1)
        } else {
            newMovements.add(movement)
        }

        return this.copy(pieces = newPieces, movements = newMovements, parent = this)
    }

    fun drawBoard() : Array<CharArray> {
        val out = Array(height) {
            CharArray(width) { '_' }
        }

        pieces.forEach { piece ->
            for (x in piece.left..piece.right) {
                for (y in piece.top..piece.bottom) {
                    out[y][x] = piece.name
                }
            }
        }

        return out
    }

    /**
     * Encode board state into easy hashable string.
     */
    fun encode(): String {
        val out = CharArray(width * height)
        pieces.forEach { piece ->
            for (x in piece.left..piece.right) {
                for (y in piece.top..piece.bottom) {
                    out[y * width + x] = piece.type
                }
            }
        }
        return String(out)
    }
}
