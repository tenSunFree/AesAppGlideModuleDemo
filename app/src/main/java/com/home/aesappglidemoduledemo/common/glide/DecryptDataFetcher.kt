package com.home.aesappglidemoduledemo.common.glide

import android.util.Base64
import android.util.Log
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.home.aesappglidemoduledemo.common.CipherManager
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import javax.crypto.Cipher
import javax.crypto.CipherInputStream

class DecryptDataFetcher(
    private val base64String: String
) : DataFetcher<InputStream> {

    @Volatile
    private var isCancel = false
    private var inputStream: InputStream? = null

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        if (isCancel) callback.onDataReady(null)
        val byteAny = Base64.decode(base64String, Base64.NO_WRAP)
        val stream: InputStream = ByteArrayInputStream(byteAny)
        val cipher = CipherManager.getCipher(Cipher.DECRYPT_MODE)
        inputStream = CipherInputStream(stream, cipher)
        callback.onDataReady(inputStream)
    }

    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.LOCAL
    }

    override fun cancel() {
        isCancel = true
    }

    override fun cleanup() {
        if (inputStream != null) {
            try {
                inputStream!!.close()
            } catch (e: IOException) {
                Log.e("Glide", "Glide", e)
            } finally {
                inputStream = null
            }
        }
    }
}