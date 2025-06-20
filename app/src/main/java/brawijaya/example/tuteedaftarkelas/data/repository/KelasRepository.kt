package brawijaya.example.tuteedaftarkelas.data.repository

import brawijaya.example.tuteedaftarkelas.data.model.Kelas
import brawijaya.example.tuteedaftarkelas.data.model.PendaftaranRequest

interface KelasRepository {
    suspend fun getAllKelas(): List<Kelas>
    suspend fun submitBooking(request: PendaftaranRequest): Boolean
}
