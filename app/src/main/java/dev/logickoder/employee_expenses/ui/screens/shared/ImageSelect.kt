package dev.logickoder.employee_expenses.ui.screens.shared

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.window.Dialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageSelect(
    type: String = "*",
    onImageSelected: (Uri) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) onImageSelected(uri)
        }
    )

    @Composable
    fun LaunchGallery() {
        SideEffect {
            launcher.launch("image/$type")
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        @Composable
        fun RequestGallery() = Dialog(
            onDismissRequest = { /*TODO*/ },
            content = {
                Text("Gallery Permission Required")
            }
        )

        val permissionState = rememberPermissionState(
            permission = Manifest.permission.ACCESS_MEDIA_LOCATION,
        )
        PermissionRequired(
            permissionState = permissionState,
            permissionNotGrantedContent = {
                LaunchedEffect(key1 = Unit) {
                    permissionState.launchPermissionRequest()
                }
                RequestGallery()
            },
            permissionNotAvailableContent = {
                RequestGallery()
            },
            content = {
                LaunchGallery()
            }
        )
    } else {
        LaunchGallery()
    }
}