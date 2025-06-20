package brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brawijaya.example.tuteedaftarkelas.R
import brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas.components.ProgramBelajarCard

@Composable
fun PilihProgramBelajarScreen(
    selectedProgram: String? = null,
    onSelectionChanged: (String?) -> Unit = {}
) {
    val imageList = listOf(
        R.drawable.bg_card_1,
        R.drawable.bg_card_3,
        R.drawable.bg_card_5,
        R.drawable.bg_card_7,
        R.drawable.bg_card_2,
        R.drawable.bg_card_4,
        R.drawable.bg_card_6,
        R.drawable.bg_card_8,
    )

    val programTitles = listOf(
        "Pemrograman Dasar Java",
        "Quality Assurance",
        "Data Mining",
        "Pemrograman Swift",
        "Pemrograman Android",
        "Data Science",
        "Pemrograman Web",
        "Project Management"
    )

    var searchBar by remember { mutableStateOf("") }

    val allPrograms = programTitles.mapIndexed { index, title ->
        Pair(imageList[index], title)
    }

    val filteredPrograms = if (searchBar.isEmpty()) {
        allPrograms
    } else {
        allPrograms.filter { (_, title) ->
            title.contains(searchBar, ignoreCase = true)
        }
    }

    Column {
        TextField(
            value = searchBar,
            onValueChange = { searchBar = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Cari program belajar yang kamu mau!",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFCBD5E1),
                unfocusedPlaceholderColor = Color(0xFF031A2F),
                focusedContainerColor = Color(0xFFCBD5E1),
                focusedPlaceholderColor = Color(0xFF031A2F),
                unfocusedTextColor = Color(0xFF031A2F),
                focusedTextColor = Color(0xFF031A2F),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search",
                        tint = Color(0xFF031A2F)
                    )
                }
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        )

        if (filteredPrograms.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada program yang cocok dengan pencarian \"$searchBar\"",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = if (selectedProgram != null) 80.dp else 24.dp)
            ) {
                items(filteredPrograms) { (imageRes, title) ->
                    ProgramBelajarCard(
                        modifier = Modifier.height(120.dp),
                        imageRes = imageRes,
                        title = title,
                        isSelected = selectedProgram == title,
                        onSelectionChanged = { isSelected ->
                            if (isSelected) {
                                onSelectionChanged(title)
                            } else {
                                onSelectionChanged(null)
                            }
                        }
                    )
                }
            }
        }
    }
}