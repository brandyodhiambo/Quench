package com.brandyodhiambo.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.brandyodhiambo.common.domain.model.Days
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class Converter(private val gson: Gson) {
    @TypeConverter
    fun toDaysString(parts: List<Days>): String {
        return gson.toJson(
            parts,
            object : TypeToken<ArrayList<Days>>() {}.type,
        ) ?: "[]"
    }

    @TypeConverter
    fun toDays(parts: String): List<Days> {
        return gson.fromJson<ArrayList<Days>>(
            parts,
            object : TypeToken<ArrayList<Days>>() {}.type,
        ) ?: emptyList()
    }

    @TypeConverter
    fun toDayString(parts: Days): String {
        return gson.toJson(
            parts,
            object : TypeToken<Days>() {}.type,
        ) ?: ""
    }

    @TypeConverter
    fun toDay(parts: String): Days? {
        return gson.fromJson<Days>(
            parts,
            object : TypeToken<Days>() {}.type,
        ) ?: null
    }

    @TypeConverter
    fun fromList(parts: List<String>): String {
        return gson.toJson(
            parts,
            object : TypeToken<ArrayList<String>>() {}.type,
        ) ?: "[]"
    }

    @TypeConverter
    fun toList(parts: String): List<String> {
        return gson.fromJson<ArrayList<String>>(
            parts,
            object : TypeToken<ArrayList<String>>() {}.type,
        ) ?: emptyList()
    }
}