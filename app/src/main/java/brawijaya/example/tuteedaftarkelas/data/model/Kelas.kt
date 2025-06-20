package brawijaya.example.tuteedaftarkelas.data.model

class Kelas {
    data class Kelas(
        val id_kelas: String? = null,
        val nama_kelas: String? = null,
        val deskripsi: String? = null,
        val harga: Int? = null,
        val paket: String? = null,
        val modul: List<String>? = null
    )

}