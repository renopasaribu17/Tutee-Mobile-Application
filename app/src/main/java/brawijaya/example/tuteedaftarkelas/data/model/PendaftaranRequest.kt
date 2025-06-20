package brawijaya.example.tuteedaftarkelas.data.model

data class PendaftaranRequest(
    val id_user: String? = null,
    val id_kelas: String? = null,
    val id_tutor: String? = null,
    val jadwal: String? = null,
    val metode_pembayaran: String? = null
)
