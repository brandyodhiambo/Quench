package com.brandyodhiambo.quench.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brandyodhiambo.quench.data.local.entity.ReportEntity

@Dao
interface ReportDao {

    @Insert
    suspend fun insertReport(report: ReportEntity)

    @Query("SELECT * FROM report_table")
    fun getReports():ReportEntity

    @Query("DELETE  FROM report_table")
    suspend fun deleteReports()
}