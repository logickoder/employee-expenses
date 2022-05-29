package dev.logickoder.employee_expenses.ui.screens.profile

import android.net.Uri
import androidx.compose.runtime.*
import dev.logickoder.employee_expenses.ui.screens.shared.ImageSelect

class ProfileState(
    val navigateToHomeScreen: () -> Unit,
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

@Composable
fun rememberProfileState(
    navigateToHomeScreen: () -> Unit,
    logout: () -> Unit,
) = remember {
    ProfileState(navigateToHomeScreen, logout)
}
