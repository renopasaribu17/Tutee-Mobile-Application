package brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brawijaya.example.tuteedaftarkelas.data.TutorData
import brawijaya.example.tuteedaftarkelas.data.TutorRepository
import brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas.components.TutorCard

@Composable
fun PilihTutorScreen(
    modifier: Modifier,
    selectedProgram: String?,
    selectedDay: String?,
    selectedTutor: TutorData?,
    onDaySelectionChanged: (String?) -> Unit,
    onTutorSelectionChanged: (TutorData?) -> Unit
) {
    val availableTutors = remember(selectedProgram) {
        if (selectedProgram != null) {
            TutorRepository.getTutorsByProgram(selectedProgram)
        } else {
            emptyList()
        }
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Pilih Tutor dan jadwal belajar-mu!",
            color = Color(0xFF031A2F),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(vertical = 24.dp)
        )

        if (availableTutors.isEmpty()) {
            Text(
                text = "Tidak ada tutor tersedia untuk program yang dipilih",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            availableTutors.forEachIndexed { index, tutor ->
                TutorCard(
                    tutorData = tutor,
                    isSelected = selectedTutor == tutor,
                    selectedDay = if (selectedTutor == tutor) selectedDay else null,
                    onTutorSelected = { newSelectedTutor ->
                        if (selectedTutor == newSelectedTutor) {
                            onTutorSelectionChanged(null)
                            onDaySelectionChanged(null)
                        } else {
                            onTutorSelectionChanged(newSelectedTutor)
                            onDaySelectionChanged(null)
                        }
                    },
                    onDaySelectionChanged = { day ->
                        if (selectedTutor == tutor) {
                            onDaySelectionChanged(day)
                        }
                    }
                )

                if (index < availableTutors.size - 1) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}