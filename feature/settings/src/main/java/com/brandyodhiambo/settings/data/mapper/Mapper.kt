package com.brandyodhiambo.settings.data.mapper

import com.brandyodhiambo.common.domain.model.ReminderMode
import com.brandyodhiambo.common.domain.model.TimeFormate
import com.brandyodhiambo.entity.ReminderModeEntity
import com.brandyodhiambo.entity.TimeFormateEntity

internal fun TimeFormateEntity.toTimeFormate(): TimeFormate {
    return TimeFormate(
        formate = formate
    )
}

internal fun TimeFormate.toTimeFormateEntity(): TimeFormateEntity {
    return TimeFormateEntity(
        formate = formate
    )
}

internal fun ReminderModeEntity.toReminderMode(): ReminderMode {
    return ReminderMode(
        days = days,
        mode = mode,
        isVibrated = isVibrated,
        isDeleted = isDeleted,
        hour = hour,
        minutes = minutes,
        ampm = ampm,
        isOn = isOn
    )
}

internal fun ReminderMode.toReminderModeEntity(): ReminderModeEntity {
    return ReminderModeEntity(
        days = days,
        mode = mode,
        isVibrated = isVibrated,
        isDeleted = isDeleted,
        hour = hour,
        minutes = minutes,
        ampm = ampm,
        isOn = isOn
    )
}