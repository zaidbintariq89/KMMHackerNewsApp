package com.example.kmmtestapp.kmm.shared

import com.google.firebase.auth.FirebaseAuth

class FireBaseManager : IFireBaseManager<FirebaseAuth> {
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}