package com.brandyodhiambo.common.domain.model

import java.time.LocalDateTime

data class AlarmData(
    val time: LocalDateTime,
    val message: String,
)
