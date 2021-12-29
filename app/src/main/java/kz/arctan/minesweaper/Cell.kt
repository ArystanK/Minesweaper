package kz.arctan.minesweaper

import  androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Cell(value: Char, color: Color) {
    var opened by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .size(25.dp)
            .clickable { opened = !opened },
        shape = MaterialTheme.shapes.medium,
        backgroundColor = color,
        contentColor = Color.White
    ) {
        Text(
            if (opened) value.toString() else "#",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun TilePreview() {
    Cell('#', Color.Blue)
}