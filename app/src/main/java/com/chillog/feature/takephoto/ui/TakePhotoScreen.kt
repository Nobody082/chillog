package com.chillog.feature.takephoto.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chillog.core.enum.CameraLens

class TakePhotoScreen
{

    @Composable
    fun Main(
        viewModel: TakePhotoViewModel = hiltViewModel()
    )
    {
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        Box(
            modifier= Modifier.fillMaxSize()
        )
        {
            CameraPreview(
                lens=state.lens,
                modifier=Modifier.fillMaxSize()
            )

            CameraTopBar(
                modifier=Modifier.align(Alignment.TopCenter)
            )


            CameraBottomBar(
                onCaptureClick={

                },
                onSwitchCameraClick={
                    viewModel.switchCamera()
                },
                modifier=Modifier.align(Alignment.BottomCenter)
            )
        }
    }

    @Composable
    fun CameraPreview(modifier: Modifier, lens: CameraLens) {
        TODO("Not yet implemented")
    }

    @Composable
    fun CameraTopBar(
        modifier: Modifier
    ){}

    @Composable
    fun CameraBottomBar(
        onCaptureClick: ()-> Unit,
        onSwitchCameraClick: ()-> Unit,
        modifier: Modifier
    ){
        Row(
            modifier=modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement=Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(
                onClick={}
            )
            {
                Icon(
                    imageVector = Icons.Default.Photo,
                    contentDescription=null
                )
            }
        }

        CaptureButton(
            onClick=onCaptureClick
        )

        IconButton(
            onClick=onSwitchCameraClick
        )
        {
            Icon(
                imageVector=Icons.Default.Cameraswitch,
                contentDescription=null
            )
        }
    }

    @Composable
    fun CaptureButton(
        onClick:()-> Unit
    )
    {
        Box(
            modifier= Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable{onClick()}
        )
    }
}


