package dev.logickoder.expensemanager.ui.screens.shared

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import dev.logickoder.expensemanager.app.widgets.AskForPermissions
import dev.logickoder.expensemanager.utils.getPath

@Composable
fun ImageSelect(
    type: String = "*",
    content: @Composable (() -> Unit) -> Unit,
    onImageSelected: (String) -> Unit,
) {
    val context = LocalContext.current

    val permissions = remember {
        buildList {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.READ_MEDIA_IMAGES)
            } else add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }.toTypedArray()
    }

    var showPermissionsDialog by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val uri = result.data?.data ?: return@rememberLauncherForActivityResult
            val path = context.getPath(uri) ?: return@rememberLauncherForActivityResult
            onImageSelected(path)
        }
    )

    val onClick = remember {
        {
            when {
                // check if all permissions have been granted
                permissions.any { permission ->
                    ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                } -> showPermissionsDialog = true

                // if they have been, launch the gallery
                else -> {
                    val intent = Intent(
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            MediaStore.ACTION_PICK_IMAGES
                        } else Intent.ACTION_PICK
                    )
                    intent.type = "image/$type"
                    launcher.launch(intent)
                }
            }

        }
    }

    if (showPermissionsDialog) {
        AskForPermissions(
            permissions = permissions,
            onDismissRequest = {
                showPermissionsDialog = false
            }
        )
    }

    content(onClick)
}