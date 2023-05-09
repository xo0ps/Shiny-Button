package info.msadeghi.shinybutton.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import info.msadeghi.shinybutton.R

@ExperimentalPermissionsApi
@Composable
fun ShinyButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current

    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = "Camera permission is required to have a Shiny button!",
        permissionNotAvailableContent = {
            Column(modifier) {
                Text("O noes! No Camera!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                        )
                    }
                ) {
                    Text("Open Settings")
                }
            }
        }
    ) {
        Button(
            modifier = modifier
                .size(
                    width = BUTTON_WIDTH,
                    height = BUTTON_HEIGHT
                ),
            shape = RoundedCornerShape(48.dp),
            border = BorderStroke(
                width = 2.dp,
                color = colorResource(id = R.color.screen_background)
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 16.dp
            ),
            contentPadding = PaddingValues(0.dp),
            onClick = onClick ?: {}
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                CameraPreview(
                    width = BUTTON_WIDTH,
                    height = BUTTON_HEIGHT,
                )
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(id = R.color.button_background)
                        ),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.metal_reflection),
                    contentDescription = null
                )
                Text(
                    text = text,
                    color = colorResource(id = R.color.button_text),
                    fontSize = 32.sp
                )
            }
        }
    }
}

private val BUTTON_WIDTH = 260.dp
private val BUTTON_HEIGHT = 96.dp
