package com.chillog.feature.takephoto.ui

import androidx.camera.core.CameraSelector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.update;

import com.chillog.feature.takephoto.ui.TakePhotoUiState;
import com.chillog.core.enum.CameraLens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TakePhotoViewModel @Inject constructor(): ViewModel()
{
    val uiState=MutableStateFlow(
        TakePhotoUiState()
    )

    fun switchCamera()
    {
        uiState.update()
        {
            it.copy(
                lens=
                    if(it.lens==CameraLens.BACK) CameraLens.FRONT
                    else CameraLens.BACK
            )
        }
    }
}