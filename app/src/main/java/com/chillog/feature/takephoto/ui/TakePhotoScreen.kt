package com.chillog.feature.takephoto.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chillog.core.component.CameraPreview
import com.chillog.core.enum.CameraLens

@Composable
fun TakePhotoScreen(
    viewModel: TakePhotoViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Kiểm tra và yêu cầu quyền camera
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCameraPermission = granted }
    )

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    TakePhotoContent(
        state = state,
        hasCameraPermission = hasCameraPermission,
        onCaptureClick = { /* logic capture */ },
        onSwitchCameraClick = { viewModel.switchCamera() },
        cameraPreview = { modifier ->
            if (hasCameraPermission) {
                CameraPreview(
                    imageCapture = viewModel.imageCapture,
                    lens = state.lens,
                    modifier = modifier
                )
            }
        }
    )
}

@Composable
fun TakePhotoContent(
    state: TakePhotoUiState,
    hasCameraPermission: Boolean,
    onCaptureClick: () -> Unit,
    onSwitchCameraClick: () -> Unit,
    cameraPreview: @Composable (Modifier) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (hasCameraPermission) {
            cameraPreview(Modifier.fillMaxSize())
        } else {
            // Placeholder khi không có quyền hoặc trong Preview
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Photo,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(64.dp)
                )
            }
        }

        CameraTopBar(
            modifier = Modifier.align(Alignment.TopCenter)
        )

        CameraBottomBar(
            onCaptureClick = onCaptureClick,
            onSwitchCameraClick = onSwitchCameraClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun CameraTopBar(
    modifier: Modifier = Modifier
) {
    // Thêm nội dung cho TopBar nếu cần
}

@Composable
fun CameraBottomBar(
    onCaptureClick: () -> Unit,
    onSwitchCameraClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Photo,
                contentDescription = "Gallery",
                tint = Color.White
            )
        }

        CaptureButton(onClick = onCaptureClick)

        IconButton(onClick = onSwitchCameraClick) {
            Icon(
                imageVector = Icons.Default.Cameraswitch,
                contentDescription = "Switch",
                tint = Color.White
            )
        }
    }
}

@Composable
fun CaptureButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.4f))
            .padding(4.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() }
    )
}

@Preview(showSystemUi = true)
@Composable
fun TakePhotoScreenPreview() {
    Surface {
        TakePhotoContent(
            state = TakePhotoUiState(lens = CameraLens.BACK),
            hasCameraPermission = true,
            onCaptureClick = {},
            onSwitchCameraClick = {},
            cameraPreview = { modifier ->
                // Trong Preview, ta vẽ một Box giả lập thay vì Camera thật
                Box(
                    modifier = modifier.background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Photo, null, tint = Color.White)
                }
            }
        )
    }
}
