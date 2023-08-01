package com.brandyodhiambo.quench.util

import com.brandyodhiambo.common.domain.model.AlarmData

interface AlarmSchedular {

    fun schedule(item: AlarmData)
    fun cancel(item: AlarmData)
}