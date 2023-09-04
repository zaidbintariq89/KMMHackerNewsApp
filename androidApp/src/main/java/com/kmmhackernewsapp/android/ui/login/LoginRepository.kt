package com.kmmhackernewsapp.android.ui.login

import com.kmmhackernewsapp.android.ui.login.model.LoginErrorModel
import com.kmmhackernewsapp.android.ui.login.model.LoginResponseModel
import com.kmmhackernewsapp.android.ui.login.model.LoginResult
import com.kmmhackernewsapp.shared.FireBaseManager
import kotlinx.coroutines.tasks.await

class LoginRepository {
    // create firebase manager
    private val firebaseManager = FireBaseManager()

    suspend fun signIn(email: String, password: String): LoginResult {
        val task = firebaseManager.getFirebaseAuth().signInWithEmailAndPassword(email, password)
        val authResult = task.await()
        return if (task.isSuccessful) {
            // get UserInfo
            getUserInfo(authResult.user?.email ?: "")
        } else {
            LoginErrorModel(task.exception?.localizedMessage ?: "Something went wrong")
        }
    }

    private suspend fun getUserInfo(email: String): LoginResult {
        val fireStore = firebaseManager.getFirebaseFireStore()
        val query = fireStore.collection("users").whereEqualTo("email", email).get()
        val result = query.await()
        return if (query.isSuccessful && result.isEmpty.not()) {
            val document = result.first()
            val userName = document.data["name"] as String
            LoginResponseModel(userName , email)
        } else {
            LoginErrorModel(query.exception?.localizedMessage ?: "Something went wrong")
        }
    }
}