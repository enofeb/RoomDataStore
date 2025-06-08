package com.enofeb.roomdatastore.domain.usecase

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.enofeb.roomdatastore.worker.ReminderNotificationWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SetReminderNotificationUseCase @Inject constructor(
    private val workManager: WorkManager
) {
    operator fun invoke() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .build()

        val reminderWork = PeriodicWorkRequestBuilder<ReminderNotificationWorker>(
            15, TimeUnit.MINUTES
        ).setInitialDelay(30, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            REMINDER_WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            reminderWork
        )
    }

    companion object {
        private const val REMINDER_WORK_NAME = "reminder_notification_work"
    }
} 