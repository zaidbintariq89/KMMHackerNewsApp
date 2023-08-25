package com.example.kmmtestapp.android.data

import android.util.Log
import com.example.kmmtestapp.android.data.model.LoggedInUser
import com.example.kmmtestapp.kmm.shared.FireBaseManager
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String, loginCompleteCallback: LoginRepository.LoginCompleteCallback) {
        val fireBaseManager = FireBaseManager()
        val firebaseAuth = fireBaseManager.getFirebaseAuth()
        val fireStore = fireBaseManager.getFirebaseFireStore()

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(
                    "MainActivity",
                    "signInWithEmail:success .... :: email = ${firebaseAuth.currentUser?.email}"
                )

                // get user information
                getUserDetails(fireStore, firebaseAuth.currentUser?.email ?: "", loginCompleteCallback)

            } else {
                // If sign in fails, display a message to the user.
                Log.w("MainActivity", "signInWithEmail:failure", task.exception)
                loginCompleteCallback.onError(task.exception.toString())
            }
        }
    }

    private fun getUserDetails(fireStore: FirebaseFirestore,email: String, loginCompleteCallback: LoginRepository.LoginCompleteCallback) {
        fireStore.collection("users").whereEqualTo("email", email).get()
            .addOnSuccessListener { result ->
                if (result.isEmpty.not()) {
                    val document = result.first()
                    Log.d("MainActivity", "${document.id} => ${document.data}")
                    val userName = document.data["name"] as String
                    loginCompleteCallback.onLoginComplete(LoggedInUser(email, userName))
                }
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}