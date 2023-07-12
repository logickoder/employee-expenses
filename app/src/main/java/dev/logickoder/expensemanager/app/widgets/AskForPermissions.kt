package dev.logickoder.expensemanager.app.widgets

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.logickoder.expensemanager.R
import dev.logickoder.expensemanager.app.theme.ExpenseManagerTheme


@Composable
fun AskForPermissions(
    modifier: Modifier = Modifier,
    permissions: Array<String>,
    onDismissRequest: () -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { result ->
            val allPermissionsGranted = result.entries.all { (_, isGranted) -> isGranted }
            if (allPermissionsGranted) {
                onDismissRequest()
            }
        }
    )

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(R.string.permissions_title))
        },
        text = {
            Text(
                text = stringResource(
                    R.string.permissions_text,
                    stringResource(id = R.string.app_name)
                )
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    launcher.launch(permissions)
                },
                content = {
                    Text(text = stringResource(R.string.accept))
                }
            )
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest,
                content = {
                    Text(text = stringResource(R.string.decline))
                }
            )
        }
    )
}

@Preview
@Composable
private fun AskForPermissionsPreview() = ExpenseManagerTheme {
    AskForPermissions(permissions = arrayOf()) {

    }
}