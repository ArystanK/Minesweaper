package kz.arctan.minesweaper

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random

data class CellData(
    var value: Char,
    var opened: Boolean = false,
) {
    val isBomb = value == '*'

    override fun toString(): String {
        return if (opened) value.toString() else "#"
    }

    fun toChar(): Char {
        return if (opened) value else '#'
    }
}

fun generateGrid(n: Int, m: Int, numberOfBombs: Int): Array<Array<CellData>> {
    val result = Array(n) { Array(m) { CellData('0') } }
    repeat(numberOfBombs) {
        var y: Int
        var x: Int
        do {
            x = Random.nextInt(m)
            y = Random.nextInt(n)
        } while (result[y][x].value == '*')
        result[y][x].value = '*'

        val dy = intArrayOf(1, 0, -1, 0)
        val dx = intArrayOf(0, 1, 0, -1)

        repeat(4) {
            if (y + dy[it] in 0 until n &&
                x + dx[it] in 0 until m &&
                result[y + dy[it]][x + dx[it]].value != '*'
            )
                result[y + dy[it]][x + dx[it]].value =
                    (result[y + dy[it]][x + dx[it]].value.digitToInt() + 1).digitToChar()
        }
    }
    return result
}

sealed class Result(code: Int) {
    object Empty : Result(1)
    object Number : Result(2)
    object Bomb : Result(3)
    object Default : Result(0)

    fun toBoolean(): Boolean {
        return when (this) {
            Bomb -> true
            Default -> false
            Empty -> true
            Number -> true
        }
    }
}


fun Array<Array<CellData>>.open(rowId: Int, colId: Int): Result {
    if (rowId !in this.indices ||
        colId !in this[0].indices ||
        this[rowId][colId].opened
    ) return Result.Default

    this[rowId][colId].opened = true

    if (this[rowId][colId].isBomb) return Result.Bomb
    if (this[rowId][colId].value != '0') return Result.Number

    val dy = intArrayOf(1, 0, -1, 0)
    val dx = intArrayOf(0, 1, 0, -1)
    repeat(4) {
        open(rowId + dy[it], colId + dx[it])
    }
    return Result.Empty
}
