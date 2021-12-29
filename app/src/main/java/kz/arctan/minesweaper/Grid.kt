package kz.arctan.minesweaper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Grid(
    numberOfRows: Int,
    numberOfColumns: Int,
    gridData: Array<Array<CellData>>,
    onCellClick: (Int, Int) -> Result,
) {
    val opened = Array(gridData.size) { i ->
        Array(gridData[0].size) { j ->
            mutableStateOf(gridData[i][j].opened)
        }
    }
    Column(verticalArrangement = Arrangement.spacedBy(1.dp),
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        repeat(numberOfRows) { i ->
            Row(horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                repeat(numberOfColumns) { j ->
                    Cell(gridData[i][j].value, opened[i][j].value, color = Color.Blue) {
                        opened[i][j].value = onCellClick(i, j).toBoolean()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GridPreview() {
    val numberOfRows = 10
    val numberOfColumns = 10
    Grid(numberOfRows,
        numberOfColumns,
        generateGrid(numberOfRows, numberOfColumns, 10)) { _, _ -> Result.Default }
}