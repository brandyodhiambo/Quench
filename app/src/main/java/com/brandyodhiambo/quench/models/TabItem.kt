package com.brandyodhiambo.quench.models

import com.brandyodhiambo.quench.R
import com.brandyodhiambo.quench.views.screens.destinations.Destination
import com.brandyodhiambo.quench.views.screens.destinations.HomeScreenDestination
import com.brandyodhiambo.quench.views.screens.destinations.SettingScreenDestination
import com.brandyodhiambo.quench.views.screens.destinations.StatisticsScreenDestination

sealed class TabItem(var icon:Int,var title:String,var destination: Destination){
    object Home :TabItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )
    object Statistics : TabItem(
        title = "Statistics",
        icon = R.drawable.ic_statistics,
        destination = StatisticsScreenDestination
    )
    object Settings : TabItem(
        title = "Settings",
        icon = R.drawable.ic_settings,
        destination = SettingScreenDestination
    )
}
