package brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import brawijaya.example.tuteedaftarkelas.data.TutorData
import brawijaya.example.tuteedaftarkelas.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarKelasScreen(
    navController: NavController
) {
    var progress by remember { mutableIntStateOf(1) }
    var selectedProgram by remember { mutableStateOf<String?>(null) }
    var selectedPackage by remember { mutableStateOf<String?>(null) }
    var selectedModules by remember { mutableStateOf(setOf<Int>()) }
    var selectedDay by remember { mutableStateOf<String?>(null) }
    var selectedTutor by remember { mutableStateOf<TutorData?>(null) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress / 5f,
        animationSpec = tween(durationMillis = 500, easing = EaseInOutCubic),
        label = "progress_animation"
    )

    Scaffold(
        topBar = {
            Surface(
                shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp),
                color = Color(0xFF052B4F),
                shadowElevation = 4.dp
            ) {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                if (progress > 1) {
                                    progress -= 1
                                    if (progress == 3) {
                                        selectedDay = null
                                        selectedTutor = null
                                    }
                                    if (progress == 2) selectedModules = emptySet()
                                    if (progress == 1) {
                                        selectedProgram = null
                                        selectedPackage = null
                                    }
                                }
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color.White
                            ),
                            modifier = Modifier.size(35.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ChevronLeft,
                                contentDescription = "Back"
                            )
                        }
                    },
                    title = {
                        Text(
                            text = "Daftar Kelas",
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .padding(bottom = 16.dp)
                )
            }
        },
        floatingActionButton = {
            val canProceed = when (progress) {
                1 -> selectedProgram != null
                2 -> selectedPackage != null
                3 -> selectedModules.isNotEmpty()
                4 -> selectedTutor != null && selectedDay != null
                else -> false
            }

            if (canProceed) {
                ExtendedFloatingActionButton(
                    onClick = {
                        when (progress) {
                            1 -> progress = 2
                            2 -> progress = 3
                            3 -> progress = 4
                            4 -> progress = 5
                        }
                    },
                    containerColor = Color(0xFF052B4F),
                    contentColor = Color.White,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .shadow(8.dp, RoundedCornerShape(10.dp))
                ) {
                    Text(
                        text = "Selanjutnya",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF031A2F))
            ) {
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    color = Color(0xFFFFB61A),
                    trackColor = Color.Transparent,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp),
                )
            }

            when (progress) {
                1 -> {
                    PilihProgramBelajarScreen(
                        selectedProgram = selectedProgram,
                        onSelectionChanged = { newSelectedProgram ->
                            selectedProgram = newSelectedProgram
                            selectedPackage = null
                            selectedTutor = null
                            selectedDay = null
                            println("DEBUG: Program selected - $selectedProgram")
                        }
                    )
                }

                2 -> {
                    PilihPaketBelajarScreen(
                        modifier = Modifier.align(Alignment.Start),
                        selectedPackage = selectedPackage,
                        onPackageSelectionChanged = { newSelectedPackage ->
                            selectedPackage = newSelectedPackage
                            println("DEBUG: Package selected - $selectedPackage")
                        }
                    )
                }

                3 -> {
                    PilihModulBelajarScreen(
                        modifier = Modifier.align(Alignment.Start),
                        selectedModules = selectedModules,
                        onModuleSelectionChanged = { newSelectedModules ->
                            selectedModules = newSelectedModules
                        }
                    )
                }

                4 -> {
                    PilihTutorScreen(
                        modifier = Modifier.align(Alignment.Start),
                        selectedProgram = selectedProgram,
                        selectedDay = selectedDay,
                        selectedTutor = selectedTutor,
                        onDaySelectionChanged = { newSelectedDay ->
                            selectedDay = newSelectedDay
                        },
                        onTutorSelectionChanged = { newSelectedTutor ->
                            selectedTutor = newSelectedTutor
                        }
                    )
                }

                5 -> {
                    BookingScreen(
                        selectedTutor = selectedTutor,
                        selectedPackage = selectedPackage,
                        selectedProgram = selectedProgram,
                        onBookingSuccess = { navController.navigate(Screen.SuccessBook.route) }
                    )
                }
            }
        }
    }
}