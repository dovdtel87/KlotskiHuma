package com.dmgdavid2109.klotskihuma.puzzle.domain.usecase

import com.dmgdavid2109.klotskihuma.puzzle.domain.model.Board
import com.dmgdavid2109.klotskihuma.puzzle.domain.model.Piece
import javax.inject.Inject

class InitialiseBoardUseCase @Inject constructor() {

    companion object {
        const val FIRST_LINE = "XXXXXX"
        const val SECOND_LINE = "XabbcX"
        const val THIRD_LINE = "XabbcX"
        const val FOURTH_LINE = "XdeefX"
        const val FIFTH_LINE = "XdghfX"
        const val SIXTH_LINE = "Xi  jX"
        const val SEVENTH_LINE = "XXZZXX"
    }

    fun invoke(): Board {
        val matrix = arrayOf(
            FIRST_LINE.toCharArray().toTypedArray(),
            SECOND_LINE.toCharArray().toTypedArray(),
            THIRD_LINE.toCharArray().toTypedArray(),
            FOURTH_LINE.toCharArray().toTypedArray(),
            FIFTH_LINE.toCharArray().toTypedArray(),
            SIXTH_LINE.toCharArray().toTypedArray(),
            SEVENTH_LINE.toCharArray().toTypedArray()
        )

        val boardWidth = matrix[0].size
        val boardHeight = matrix.size

        val pieces = ArrayList<Piece>()
        lateinit var end: Piece
        var indexOfMain = 0

        //Find pieces
        matrix.flatten()
            .forEachIndexed { i, _ ->
                val x = i % boardWidth
                val y = i / boardWidth
                val name = matrix[y][x]
                if (name != ' ' && name != '&') {
                    var pieceWidth = 1
                    var pieceHeight = 1

                    while (x + pieceWidth < boardWidth && matrix[y][x + pieceWidth] == name) {
                        pieceWidth++
                    }

                    while (y + pieceHeight < boardHeight) {
                        val rowBelow = matrix[y + pieceHeight].drop(x).take(pieceWidth)
                        if (!rowBelow.all { it == name }) {
                            break
                        }
                        pieceHeight++
                    }

                    val type = findType(pieceWidth, pieceHeight)
                    val piece = Piece(x, y, pieceWidth, pieceHeight, type, name, pieces.size)
                    //Fill it with '&' for processed
                    for (xx in piece.left..piece.right) {
                        for (yy in piece.top..piece.bottom) {
                            matrix[yy][xx] = '&'
                        }
                    }
                    if (name == 'Z') {
                        end = piece
                    } else {
                        if (piece.type == '#') {
                            indexOfMain = piece.index
                        }
                        pieces.add(piece)
                    }
                }
            }

        return Board(
            width = boardWidth, height = boardHeight, pieces = pieces,
            end = end, indexOfMain = indexOfMain
        )
    }

    private fun findType(pieceWidth: Int, pieceHeight: Int) : Char {
        return when {
            pieceWidth == 1 && pieceHeight == 1 -> 'a'
            pieceWidth == 1 && pieceHeight == 2 -> 'b'
            pieceWidth == 2 && pieceHeight == 1 -> 'c'
            pieceWidth == 2 && pieceHeight == 2 -> '#'
            else -> '*'
        }
    }
}

