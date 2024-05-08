package com.example.diploma.viewmodels

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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

    fun checkAndRequestExactAlarmPermission(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                } else {
                    // Для Android ниже 12 (API 31), это действие не требуется
                    null
                }
                intent?.let { context.startActivity(it) }
            } else {
                // Разрешение уже предоставлено, можно планировать будильники
            }
        }
    }

    fun scheduleNotification(context: Context, target: TargetData) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("target_description", target.targetName)
            putExtra("notification_id", target.targetId)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, target.targetId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        // Устанавливаем время срабатывания на один день до дедлайна
        val triggerAtMillis = target.targetDeadline - 24 * 60 * 60 * 1000  // 24 часа в миллисекундах

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
        }
    }
}