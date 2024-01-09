package fr.iut.alldev.allin.keystore.impl

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import javax.inject.Inject

private const val AUTH_TOKEN_KEY = "auth_token"
private const val PREFS_FILE_NAME = "secured_shared_prefs"

class AllInKeystoreManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AllInKeystoreManager() {

    private var sharedPreferences: SharedPreferences? = null
    override fun createKeystore() {
        if (sharedPreferences == null) {
            sharedPreferences = EncryptedSharedPreferences.create(
                PREFS_FILE_NAME,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }

    override fun putToken(token: String) {
        sharedPreferences?.edit()?.putString(AUTH_TOKEN_KEY, token)?.apply()
    }


    override fun getToken(): String? {
        return sharedPreferences?.getString(AUTH_TOKEN_KEY, null)
    }

    override fun deleteToken() {
        sharedPreferences?.edit()?.putString(AUTH_TOKEN_KEY, null)?.apply()
    }
}