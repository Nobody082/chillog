package com.chillog.feature.takephoto.ui

import com.chillog.core.enum.CameraLens;

data class TakePhotoUiState(
    val isLoading: Boolean = false,
    val flashEnabled: Boolean = false,
    val lens: CameraLens = CameraLens.BACK
)