package com.brandyodhiambo.settings.data.mapper

import com.brandyodhiambo.common.domain.model.TimeFormate
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
