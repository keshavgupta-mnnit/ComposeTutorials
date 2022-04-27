package com.keshav.composetutorials.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.keshav.composetutorials.model.BookItem
import java.lang.reflect.Type


fun Context.getGsonFromAssets(fileName: String): List<BookItem> {
    val jsonFileString = this.assets.open(fileName).bufferedReader().use { it.readText() }
    val gson = Gson()
    val listBookItemType: Type = object : TypeToken<List<BookItem?>?>() {}.type
    return gson.fromJson(jsonFileString, listBookItemType)
}