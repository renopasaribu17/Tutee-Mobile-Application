package brawijaya.example.tuteedaftarkelas.ui.screens.daftarkelas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import brawijaya.example.tuteedaftarkelas.R
import brawijaya.example.tuteedaftarkelas.data.TutorData
import brawijaya.example.tuteedaftarkelas.data.model.PendaftaranRequest
import brawijaya.example.tuteedaftarkelas.viewmodel.DaftarKelasViewModel
import java.text.NumberFormat
import java.util.Locale


@Composable
fun BookingScreen(
    selectedTutor: TutorData?,
    selectedPackage: String?,
    selectedProgram: String?,
    onBookingSuccess: () -> Unit,
    viewModel: DaftarKelasViewModel = hiltViewModel()
) {
    var selectedPaymentMethod by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val totalPrice =
        if (selectedPackage?.contains("One") == true) 30000 else if (selectedPackage?.contains("Short") == true) 100000 else if (selectedPackage?.contains("Long") == true) 300000 else 30000

    val ppn = (totalPrice * 0.1).toInt()
    val transactionFee = 1000
    val finalTotal = totalPrice + ppn + transactionFee

    val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    numberFormat.maximumFractionDigits = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp), clip = false)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = selectedTutor?.imageRes ?: R.drawable.ic_launcher_foreground
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Selected Tutor Profile Picture",
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = selectedTutor?.name ?: "Kansha CaSa",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            lineHeight = 18.sp
                        )
                    )

                    Text(
                        text = selectedTutor?.role ?: "Project Management",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Place,
                            contentDescription = "Place",
                            tint = Color(0xFF031A2F),
                            modifier = Modifier.size(12.dp)
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = selectedTutor?.location ?: "Malang, Jawa Timur",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 10.sp,
                                lineHeight = 12.sp
                            )
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_degree),
                            contentDescription = "Degree",
                            modifier = Modifier.size(12.dp)
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = selectedTutor?.degree ?: "Sistem Informasi 2021",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 10.sp,
                                lineHeight = 12.sp
                            )
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = numberFormat.format(totalPrice).replace("Rp", "Rp "),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFCC9215))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF052B4F))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFB61A),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    text = "5",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                )
            }
        }

        Text(
            text = "Pembayaran",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            ),
        )

        HorizontalDivider(color = Color(0xFF031A2F) , modifier = Modifier.padding(vertical = 8.dp))

        Text(
            text = "Ringkasan Produk",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "$selectedPackage $selectedProgram (Individu)",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = numberFormat.format(totalPrice),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box {
            OutlinedTextField(
                value = if (selectedPaymentMethod.isEmpty()) "Pilih Metode Pembayaran" else selectedPaymentMethod,
                onValueChange = { },
                readOnly = true,
                enabled = false,
                trailingIcon = {
                    Icon(
                        imageVector = if (isDropdownExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        tint = Color(0xFFCC9215)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isDropdownExpanded = !isDropdownExpanded },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFCC9215),
                    unfocusedBorderColor = Color(0xFFCC9215),
                    disabledTextColor = Color(0xFFCC9215),
                    disabledBorderColor = Color(0xFFCC9215)
                ),
                shape = RoundedCornerShape(8.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            )

            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false },
                modifier = Modifier.fillMaxWidth(0.766f)
            ) {
                listOf("Transfer Bank", "E-Wallet", "Kartu Kredit").forEach { method ->
                    DropdownMenuItem(
                        text = { Text(method) },
                        onClick = {
                            selectedPaymentMethod = method
                            isDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal", color = Color.Gray, fontSize = 12.sp)
                Text(numberFormat.format(totalPrice), fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("PPN (10%)", color = Color.Gray, fontSize = 12.sp)
                Text(numberFormat.format(ppn), fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Biaya Transaksi", color = Color.Gray, fontSize = 12.sp)
                Text(numberFormat.format(transactionFee), fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(numberFormat.format(finalTotal), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (selectedTutor != null && selectedPackage != null && selectedProgram != null && selectedPaymentMethod.isNotEmpty()) {
                    val request = PendaftaranRequest(
                        id_user = "USR001", // nanti bisa ganti dengan user login sesungguhnya
                        id_kelas = "${selectedProgram}_${selectedPackage}", // bisa juga pakai UUID
                        id_tutor = selectedTutor.name,
                        jadwal = "2025-06-21", // bisa dibuat pilihan nanti
                        metode_pembayaran = selectedPaymentMethod
                    )
                    viewModel.submitPendaftaran(request) { success ->
                        if (success) onBookingSuccess()
                        // else bisa tambahkan snackbar/error toast
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB61A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Booking",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                ),
                color = Color(0xFF031A2F)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}