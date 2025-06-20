package brawijaya.example.tuteedaftarkelas.data

import brawijaya.example.tuteedaftarkelas.R

data class TutorData(
    val id: String,
    val name: String,
    val role: String,
    val program: String,
    val imageRes: Int,
    val availableDays: List<String>,
    val location: String,
    val degree: String
)

object TutorRepository {
    val tutors = listOf(
        TutorData(
            id = "tutor_1",
            name = "Kansha CaSa",
            role = "Project Management",
            program = "Project Management",
            imageRes = R.drawable.tutor_profile_1,
            availableDays = listOf("Senin", "Kamis", "Selasa", "Minggu"),
            location = "Malang, Jawa Timur",
            degree = "Sistem Informasi 2021"
        ),
        TutorData(
            id = "tutor_2",
            name = "Irsanty F.P",
            role = "Programming",
            program = "Pemrograman Dasar Java",
            imageRes = R.drawable.tutor_profile_2,
            availableDays = listOf("Senin", "Rabu", "Jumat"),
            location = "Malang, Jawa Timur",
            degree = "Sistem Informasi 2022"
        ),
        TutorData(
            id = "tutor_3",
            name = "Nisriina Dyan",
            role = "Programming",
            program = "Pemrograman Web",
            imageRes = R.drawable.tutor_profile_3,
            availableDays = listOf("Selasa", "Kamis", "Sabtu"),
            location = "Malang, Jawa Timur",
            degree = "Sistem Informasi 2022"
        ),
        TutorData(
            id = "tutor_4",
            name = "Sarah Dewi",
            role = "Data Science",
            program = "Data Mining",
            imageRes = R.drawable.tutor_profile_2,
            availableDays = listOf("Senin", "Rabu", "Minggu"),
            location = "Bandung, Jawa Barat",
            degree = "Teknologi Informasi 2022"
        ),
        TutorData(
            id = "tutor_5",
            name = "Budi Santoso",
            role = "Data Science",
            program = "Data Science",
            imageRes = R.drawable.tutor_profile_3,
            availableDays = listOf("Selasa", "Kamis", "Sabtu", "Minggu"),
            location = "Surabaya, Jawa Timur",
            degree = "Teknik Informatika 2021"
        ),
        TutorData(
            id = "tutor_6",
            name = "Linda Kusuma",
            role = "Programming",
            program = "Pemrograman Android",
            imageRes = R.drawable.tutor_profile_1,
            availableDays = listOf("Senin", "Rabu", "Jumat", "Sabtu"),
            location = "Jakarta Barat, DKI Jakarta",
            degree = "Teknologi Informasi 2023"
        ),
        TutorData(
            id = "tutor_7",
            name = "Tatang Koil",
            role = "Programming",
            program = "Pemrograman Swift",
            imageRes = R.drawable.tutor_profile_3,
            availableDays = listOf("Senin", "Rabu", "Kamis", "Sabtu"),
            location = "Semarang, Jawa Tengah",
            degree = "Teknik Informatika 2023"
        ),
        TutorData(
            id = "tutor_8",
            name = "Dadang Kopling",
            role = "Quality Assurance",
            program = "Quality Assurance",
            imageRes = R.drawable.tutor_profile_1,
            availableDays = listOf("Senin", "Rabu", "Kamis", "Sabtu"),
            location = "Bandung, Jawa Barat",
            degree = "Sistem Informasi 2021"
        ),
        TutorData(
            id = "tutor_9",
            name = "Ahmad Faisal",
            role = "Programming",
            program = "Pemrograman Web",
            imageRes = R.drawable.tutor_profile_2,
            availableDays = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"),
            location = "Bekasi, Jawa Barat",
            degree = "Teknologi Informasi 2022"
        )
    )

    fun getTutorsByProgram(program: String): List<TutorData> {
        return tutors.filter { it.program.equals(program, ignoreCase = true) }
    }
}