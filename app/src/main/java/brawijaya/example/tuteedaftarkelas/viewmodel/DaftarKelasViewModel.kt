package brawijaya.example.tuteedaftarkelas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brawijaya.example.tuteedaftarkelas.data.model.PendaftaranRequest
import brawijaya.example.tuteedaftarkelas.data.repository.KelasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaftarKelasViewModel @Inject constructor(
    private val repository: KelasRepository
) : ViewModel() {

    fun submitPendaftaran(request: PendaftaranRequest, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = repository.submitBooking(request)
            onResult(result)
        }
    }
}
