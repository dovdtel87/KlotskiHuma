package com.dmgdavid2109.klotskihuma.puzzle.domain.usecase

import com.dmgdavid2109.klotskihuma.puzzle.domain.model.Board
import com.dmgdavid2109.klotskihuma.puzzle.domain.model.Movement
import com.dmgdavid2109.klotskihuma.puzzle.domain.model.Side
import java.util.*
import javax.inject.Inject
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashSet

class SolvePuzzleUseCase @Inject constructor() {

    fun invoke(board: Board) : ArrayList<Board> {
        val solved = solve(false, board).minBy { it.movements.size }

        val steps = ArrayList<Board>()
        var current: Board? = solved
        while (current != null) {
            steps.add(current)
            current = current.parent
        }
        steps.reverse()
        return steps
    }

    fun solve(findAll: Boolean, board: Board): List<Board> {

        val solvedBoards = ArrayList<Board>()
        val evaluatedBoards = LinkedHashSet<Any>()
        //Sort by least amount of moves
        val queue = PriorityQueue<Board>(Comparator { a, b ->
            a.movements.size.compareTo(b.movements.size)
        })

        queue.add(board)
        evaluatedBoards.add(board.encode())
        var currentSize = -1

        while (true) {
            if (queue.isEmpty()) return solvedBoards
            val current = queue.remove()
            if (current.movements.size > currentSize) {
                currentSize = current.movements.size
                println("Evaluating boards solved in $currentSize movements. ${queue.size}")
            }

            for (piece in current.pieces) {
                if (!piece.canMove) continue
                Side.values().forEach { side ->
                    if (current.canMove(piece, side)) {
                        val movement = Movement(piece = piece, side = side, steps = 1)
                        val newBoard = current.movePiece(movement)

                        if (newBoard.isSolved()) {
                            solvedBoards.add(newBoard)
                            if(!findAll) {
                                return solvedBoards
                            }
                        }
                        if (evaluatedBoards.add(newBoard.encode())) {
                            queue.add(newBoard)
                        }
                    }
                }
            }
        }
    }
}
