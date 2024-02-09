package fr.iut.alldev.allin.keystore

import androidx.security.crypto.MasterKey

abstract class AllInKeystoreManager {
    protected abstract val masterKey: MasterKey
    abstract fun createKeystore()
    abstract fun putToken(token: String)
    abstract fun getToken(): String?
    abstract fun deleteToken()
    fun getTokenOrEmpty() = getToken() ?: ""
}