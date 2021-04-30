package com.example.storyreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stuff()
    }

    private fun stuff() {
        try {
            val inputStream: String? = loadFileFromAsset("TheRailwayChildrenBook.txt")
            val peanut = inputStream?.split(" ")
            if (peanut != null) {
                for (word in peanut) {
                    println(word)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            println("finished")
        }
    }

    private fun loadFileFromAsset(filename: String): String? {
        val inputStream: InputStream?
        try {
            inputStream = baseContext.assets.open(filename)
        } catch (e: IOException) {
            throw e
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