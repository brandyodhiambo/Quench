/*
 * Copyright (C)2023 Brandy Odhiambo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            object : TypeToken<ArrayList<Days>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toDays(parts: String): List<Days> {
        return gson.fromJson<ArrayList<Days>>(
            parts,
            object : TypeToken<ArrayList<Days>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toDayString(parts: Days): String {
        return gson.toJson(
            parts,
            object : TypeToken<Days>() {}.type
        ) ?: ""
    }

    @TypeConverter
    fun toDay(parts: String): Days? {
        return gson.fromJson<Days>(
            parts,
            object : TypeToken<Days>() {}.type
        ) ?: null
    }

    @TypeConverter
    fun fromList(parts: List<String>): String {
        return gson.toJson(
            parts,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toList(parts: String): List<String> {
        return gson.fromJson<ArrayList<String>>(
            parts,
            object : TypeToken<ArrayList<String>>() {}.type
        ) ?: emptyList()
    }
}
