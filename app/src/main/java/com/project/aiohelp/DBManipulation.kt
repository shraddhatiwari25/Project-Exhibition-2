package com.project.aiohelp

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.project.aiohelp.data.UserModel

class DBManipulation {
    private val database = Firebase.database("https://aio-help-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val dbRef = database.getReference("Users")

    public fun addCustomer(name: String, email: String, pass: String){
        val user = UserModel(name, email, pass)
        dbRef.child(email).setValue(user)
    }
}