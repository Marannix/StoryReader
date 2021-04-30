package com.example.data.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class FileDataProvider(private val context: Context) {

//    private fun stuff() : MutableList<String> {
//        val listOfWords = mutableListOf<String>()
//        try {
//            val inputStream: String? = loadFileFromAsset("TheRailwayChildrenBook.txt")
//            val peanut = inputStream?.split(" ")
//            if (peanut != null) {
//                for (word in peanut) {
//                    println(word)
//                }
//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return listOfWords
//    }

    private fun loadFileFromAsset(filename: String): String? {
        val inputStream: InputStream?
        try {
            inputStream = context.assets.open(filename)
        } catch (error: IOException) {
            throw error
        }

        return convertInputStreamToString(inputStream)
    }

    private fun convertInputStreamToString(inputStream: InputStream): String? {
        val result: String
        try {
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            result = String(buffer, Charset.forName("UTF-8"))
        } catch (exception: IOException) {
            throw exception
        }

        return result
    }
}