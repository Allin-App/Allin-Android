package fr.iut.alldev.allin.keystore

import androidx.security.crypto.MasterKeys

abstract class AllInKeystoreManager {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    abstract fun createKeystore()
    abstract fun putToken(token: String)
    abstract fun getToken(): String?
    abstract fun deleteToken()
}