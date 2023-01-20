package com.project.aiohelp

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.aiohelp.data.WorkerModel


class DBManipulation {
    private val db = Firebase.firestore

    fun addCustomer(name: String, email: String, pass: String) {
        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "pass" to pass,
        )
        db.collection("Users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("Stuff", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Stuff", "Error adding document", e)
            }
    }

    fun addWorker(name: String, age: String, email: String, pass: String, job: String) {
        val worker = hashMapOf(
            "name" to name,
            "age" to age,
            "email" to email,
            "pass" to pass,
            "job" to job,
            "busy" to false
        )
        db.collection("Workers")
            .add(worker)
            .addOnSuccessListener { documentReference ->
                Log.d("Stuff", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Stuff", "Error adding document", e)
            }
    }

    fun getWorker(job: String): SnapshotStateList<WorkerModel?> {
        val workerList = mutableStateListOf<WorkerModel?>()

        db.collection("Workers").whereEqualTo("job", job).whereEqualTo("busy", false).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: WorkerModel? = d.toObject(WorkerModel::class.java)
                        workerList.add(c)
                    }
                }
            }
            .addOnFailureListener {
                // TODO
            }
        return workerList
    }
}
