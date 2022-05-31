package dev.logickoder.employee_expenses.ui.screens.profile

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.logickoder.employee_expenses.ui.screens.shared.ImageSelect

class ProfileState(
    val goBack: () -> Unit,
    val logout: () -> Unit,
) {
    var name by mutableStateOf("")
    var jobDescription by mutableStateOf("")
    var department by mutableStateOf("")
    var location by mutableStateOf("")

    var avatar by mutableStateOf<Uri?>(null)
        private set

    var showGallery by mutableStateOf(false)

    @Composable
    fun SelectAvatar() {
        ImageSelect(
            onImageSelected = {
                avatar = it
                showGallery = false
            }
        )
    }
}
