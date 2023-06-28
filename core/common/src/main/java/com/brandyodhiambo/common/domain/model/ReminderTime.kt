package com.brandyodhiambo.common.domain.model

data class ReminderTime(
    val hour: Int,
    val minute: Int,
    val ampm: String,
    val isRepeated: Boolean,
    val isAllDay: Boolean,
    val days: List<String>,
)
