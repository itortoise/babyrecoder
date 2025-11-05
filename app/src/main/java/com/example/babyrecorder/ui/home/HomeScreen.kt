package com.example.babyrecorder.ui.home

import android.os.VibrationEffect
import android.os.Vibrator
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.babyrecorder.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val primaryColor = MaterialTheme.colorScheme.primary
    var buttonColor by remember { mutableStateOf(primaryColor) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                // 改变按钮颜色
                buttonColor = Color.Green
                
                // 添加震动反馈
                vibrate(context)
                
                // 保存记录到数据库
                viewModel.addClickRecord()
                
                // 使用 LaunchedEffect 延迟恢复按钮颜色
                // 这是 Compose 推荐的方式，替代 Timer 和 runOnUiThread
            },
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text("点击", color = Color.White)
        }
    }
    
    // 使用 LaunchedEffect 实现延迟恢复按钮颜色
    LaunchedEffect(buttonColor) {
        if (buttonColor == Color.Green) {
            delay(200)
            buttonColor = primaryColor
        }
    }
}

private fun vibrate(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (vibrator.hasVibrator()) {
        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
    }
}