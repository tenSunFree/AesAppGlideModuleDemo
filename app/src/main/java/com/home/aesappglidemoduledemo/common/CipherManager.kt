package com.home.aesappglidemoduledemo.common

import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CipherManager {

    private const val password = "0123456789abcdef"
    private const val iv = "0123456789abcdef"

    fun getCipher(mode: Int): Cipher {
        val password =
            SecretKeySpec(password.toByteArray(charset("UTF-8")), "AES")
        val iv =
            IvParameterSpec(iv.toByteArray(charset("UTF-8")))
        val cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
        cipher.init(mode, password, iv)
        return cipher
    }
}
