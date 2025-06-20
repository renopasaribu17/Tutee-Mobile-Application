package brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DayChip(
    day: String,
    isSelected: Boolean = false,
    onDayClick: (String) -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color(0xFFFFB61A)),
        color = if (isSelected) Color(0xFFFFB61A) else Color.White,
        contentColor = if (isSelected) Color.White else Color(0xFFFFB61A),
        modifier = Modifier.clickable { onDayClick(day) }
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 8.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}