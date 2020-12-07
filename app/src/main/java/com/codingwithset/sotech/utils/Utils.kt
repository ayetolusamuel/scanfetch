package com.codingwithset.sotech.utils

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.codingwithset.sotech.Model
import com.google.gson.Gson
import java.io.IOException

@Throws(IOException::class)
fun Context.readJsonAsset(fileName: String): String {
    val inputStream = assets.open(fileName)
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}




fun Context.saveFrag(entity: Model) {
    val sharedPreferences = getDefaultSharedPreferences(this)
    val editor = sharedPreferences.edit()
    val gSon = Gson()
    val valueString = gSon.toJson(entity)
    editor.putString("data", valueString).apply()
}


fun Context.getFrag(): Model {
    val sharedPreferences = getDefaultSharedPreferences(this)
    val data = sharedPreferences.getString("data", "")

    return Gson().fromJson(data, Model::class.java)

}
