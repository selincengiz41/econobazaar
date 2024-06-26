package com.selincengiz.core.common

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig

object RemoteConfigManager {
    private const val BACKGROUND_KEY = "background"

    private val firebaseRemoteConfig: FirebaseRemoteConfig by lazy {
        Firebase.remoteConfig.apply {
            setDefaultsAsync(R.xml.remote_config)
            fetch(0)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        activate()
                    } else {
                        Log.e("user", "Fetch failed")
                    }
                }
        }
    }

    fun getBackgroundColor(): String {
        return firebaseRemoteConfig.getString(BACKGROUND_KEY)
    }
}