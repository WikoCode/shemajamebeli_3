package com.example.shemajamebeli_4

class TicTacToeGame(private val dimension: Int) {
    enum class Player {
        X, O, NONE
    }

    private val board = Array(dimension) { Array(dimension) { Player.NONE } }
    private var currentPlayer = Player.X

    fun getDimension(): Int {
        return dimension
    }

    fun getPlayerState(row: Int, col: Int): Player {
        return board[row][col]
    }

    fun getBoard(): List<List<Player>> {
        return board.map { it.toList() }
    }

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col] == Player.NONE) {
            board[row][col] = currentPlayer
            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
            return true
        }
        return false
    }

    fun checkForWinner(): Player {
        for (i in 0 until dimension) {
            if (board[i].all { it == Player.X }) return Player.X
            if (board[i].all { it == Player.O }) return Player.O

            if ((0 until dimension).all { board[it][i] == Player.X }) return Player.X
            if ((0 until dimension).all { board[it][i] == Player.O }) return Player.O
        }

        if ((0 until dimension).all { board[it][it] == Player.X }) return Player.X
        if ((0 until dimension).all { board[it][it] == Player.O }) return Player.O

        if ((0 until dimension).all { board[it][dimension - 1 - it] == Player.X }) return Player.X
        if ((0 until dimension).all { board[it][dimension - 1 - it] == Player.O }) return Player.O

        return Player.NONE
    }

    fun isBoardFull(): Boolean {
        return board.flatten().none { it == Player.NONE }
    }

    fun resetGame() {
        for (i in 0 until dimension) {
            for (j in 0 until dimension) {
                board[i][j] = Player.NONE
            }
        }
        currentPlayer = Player.X
    }
}
