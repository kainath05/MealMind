package com.example.mealmind.util

import android.content.Context
import com.example.mealmind.R
import org.json.JSONObject
import java.io.InputStream

fun getApiKey(context: Context): String {
    val inputStream: InputStream = context.resources.openRawResource(R.raw.keys)
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    val jsonObject = JSONObject(jsonString)
    return jsonObject.getString("openai_api_key")
}