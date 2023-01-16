package com.project.aiohelp

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.project.aiohelp.data.UserModel
import com.project.aiohelp.data.workerModel

class DBManipulation {
    private val database = Firebase.database("https://aio-help-default-rtdb.asia-southeast1.firebasedatabase.app")
    private val dbRef = database.getReference("Users")

    public fun addCustomer(name: String, email: String, pass: String){
        val user = UserModel(name, email, pass)
        dbRef.child(email).setValue(user)
    }

    public fun addWorker(name: String,age: Int,email: String,pass: String,job: String){
        val worker = workerModel(name,age,email,pass,job)
        val dbRef = database.getReference("worker")
        dbRef.child(email).setValue(worker)
    }
}