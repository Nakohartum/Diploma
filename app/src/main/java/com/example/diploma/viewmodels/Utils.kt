package com.example.diploma.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.ui.graphics.Color
import com.example.diploma.models.TargetData
import com.example.diploma.ui.theme.DarkOrange
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun formatDate(milliseconds: Long?): String {
        if (milliseconds == null) return "No deadline set"
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return formatter.format(Date(milliseconds))
    }

    fun getBackgroundColorForDeadline(deadline: Long?, currentTime: Long): Color {
        if (deadline == null) return Color.White

        val timeDifference = deadline - currentTime
        return when {
            timeDifference <= 0 -> Color.Red
            timeDifference <= 24 * 60 * 60 * 1000 -> Color.Magenta
            else -> DarkOrange
        }
    }

    fun uriToByteArray(context: Context, uri: Uri): ByteArray? {
        return context.contentResolver.openInputStream(uri)?.use { inputStream ->
            inputStream.readBytes()

        }
    }


}