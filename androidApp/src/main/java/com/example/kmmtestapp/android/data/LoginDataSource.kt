package com.example.kmmtestapp.android.data

import android.util.Log
import com.example.kmmtestapp.android.data.model.LoggedInUser
import com.example.kmmtestapp.kmm.shared.FireBaseManager

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String, loginCompleteCallback: LoginRepository.LoginCompleteCallback) {
        val firebaseAuth = FireBaseManager().getFirebaseAuth()
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(
                    "MainActivity",
                    "signInWithEmail:success .... :: email = ${firebaseAuth.currentUser?.email}"
                )
                loginCompleteCallback.onLoginComplete(LoggedInUser(firebaseAuth.currentUser?.email ?: "", "Current User"))
            } else {
                // If sign in fails, display a message to the user.
                Log.w("MainActivity", "signInWithEmail:failure", task.exception)
                loginCompleteCallback.onError(task.exception.toString())
            }
        }
//            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
//            Result.Success(fakeUser)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}