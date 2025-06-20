package brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import brawijaya.example.tuteedaftarkelas.data.ModuleData
import brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas.components.ModuleCard

@Composable
fun PilihModulBelajarScreen(
    modifier: Modifier,
    selectedModules: Set<Int>,
    onModuleSelectionChanged: (Set<Int>) -> Unit
) {
    var expandedModules by remember { mutableStateOf(setOf<Int>()) }

    val modules = listOf(
        ModuleData(1, "Modul 1", "Pengenalan Dasar Programming", "Mempelajari konsep dasar pemrograman, variabel, dan struktur data fundamental yang akan menjadi fondasi pembelajaran selanjutnya."),
        ModuleData(2, "Modul 2", "Kontrol Flow dan Fungsi", "Memahami penggunaan kondisi, perulangan, dan cara membuat fungsi yang efektif untuk mengorganisir kode program."),
        ModuleData(3, "Modul 3", "Object Oriented Programming", "Konsep OOP meliputi class, object, inheritance, polymorphism, dan encapsulation untuk pengembangan aplikasi yang terstruktur."),
        ModuleData(4, "Modul 4", "Database dan SQL", "Pengenalan database relasional, query SQL, dan cara mengintegrasikan database dengan aplikasi yang dikembangkan."),
        ModuleData(5, "Modul 5", "Web Development Frontend", "Mempelajari HTML, CSS, JavaScript, dan framework modern untuk membangun antarmuka pengguna yang interaktif."),
        ModuleData(6, "Modul 6", "Backend Development", "Pengembangan sisi server menggunakan REST API, authentication, dan integrasi dengan database."),
        ModuleData(7, "Modul 7", "Mobile App Development", "Membangun aplikasi mobile native menggunakan Android Studio dan Kotlin dengan UI yang responsif."),
        ModuleData(8, "Modul 8", "Testing dan Debugging", "Teknik testing unit, integration testing, dan debugging untuk memastikan kualitas aplikasi yang dikembangkan."),
        ModuleData(9, "Modul 9", "Version Control Git", "Penggunaan Git untuk version control, branching, merging, dan kolaborasi tim dalam pengembangan software."),
        ModuleData(10, "Modul 10", "Deployment dan DevOps", "Proses deployment aplikasi ke server, CI/CD pipeline, dan monitoring aplikasi di production environment."),
        ModuleData(11, "Modul 11", "Security Best Practices", "Implementasi keamanan aplikasi, enkripsi data, authentication, authorization, dan pencegahan vulnerability."),
        ModuleData(12, "Modul 12", "Project Capstone", "Mengintegrasikan semua pengetahuan yang telah dipelajari dalam project akhir yang komprehensif dan real-world.")
    )

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Pilih modul belajar-mu!",
            color = Color(0xFF031A2F),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(vertical = 24.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(modules) { module ->
                ModuleCard(
                    module = module,
                    isSelected = selectedModules.contains(module.id),
                    isExpanded = expandedModules.contains(module.id),
                    onSelectionChanged = { isSelected ->
                        val newSelectedModules = if (isSelected) {
                            selectedModules + module.id
                        } else {
                            selectedModules - module.id
                        }
                        onModuleSelectionChanged(newSelectedModules)
                    },
                    onExpandToggle = {
                        expandedModules = if (expandedModules.contains(module.id)) {
                            expandedModules - module.id
                        } else {
                            expandedModules + module.id
                        }
                    }
                )
            }
        }
    }
}