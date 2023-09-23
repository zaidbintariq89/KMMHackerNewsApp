package com.kmmhackernewsapp.shared

interface IFireBaseManager<T> {
    fun getFirebaseAuth() : T
}