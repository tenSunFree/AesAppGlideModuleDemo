package com.home.aesappglidemoduledemo.presenter

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.home.aesappglidemoduledemo.common.CipherManager
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import javax.crypto.Cipher

class MainPresenter(private val context: Context) {

    var base64String = ""

    fun generateBase64(): String {
        val bitmap = getBitmapFromAsset(context)
        base64String = getBase64String(bitmap!!)
        return base64String
    }

    private fun getBitmapFromAsset(context: Context): Bitmap? {
        val assetManager: AssetManager = context.assets
        val stream: InputStream
        var bitmap: Bitmap? = null
        try {
            stream = assetManager.open("movie.jpg")
            bitmap = BitmapFactory.decodeStream(stream)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }

    private fun getBase64String(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
        val byteArray = stream.toByteArray()
        val cipher = CipherManager.getCipher(Cipher.ENCRYPT_MODE)
        val cipherByteArray = cipher.doFinal(byteArray)
        return Base64.encodeToString(cipherByteArray, Base64.NO_WRAP)
    }
}