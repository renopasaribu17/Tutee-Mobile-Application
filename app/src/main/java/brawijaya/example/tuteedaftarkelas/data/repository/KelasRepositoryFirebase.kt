package brawijaya.example.tuteedaftarkelas.data.repository

import com.google.firebase.database.*
import brawijaya.example.tuteedaftarkelas.data.model.Kelas
import brawijaya.example.tuteedaftarkelas.data.model.PendaftaranRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import javax.inject.Inject

class KelasRepositoryFirebase @Inject constructor() : KelasRepository {


    private val db = FirebaseDatabase.getInstance().getReference("kelas")
    private val bookingDb = FirebaseDatabase.getInstance().getReference("pendaftaran")

    override suspend fun getAllKelas(): List<Kelas> = suspendCancellableCoroutine { cont ->
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Kelas>()
                for (data in snapshot.children) {
                    data.getValue(Kelas::class.java)?.let { list.add(it) }
                }
                cont.resume(list)
            }

            override fun onCancelled(error: DatabaseError) {
                cont.resume(emptyList())
            }
        })
    }

    override suspend fun submitBooking(request: PendaftaranRequest): Boolean = suspendCancellableCoroutine { cont ->
        val id = bookingDb.push().key
        if (id != null) {
            bookingDb.child(id).setValue(request)
                .addOnSuccessListener { cont.resume(true) }
                .addOnFailureListener { cont.resume(false) }
        } else {
            cont.resume(false)
        }
    }
}
