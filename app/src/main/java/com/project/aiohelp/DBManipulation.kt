package com.project.aiohelp

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.aiohelp.data.OrderModel
import com.project.aiohelp.data.WorkerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*


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

    fun addWorker(
        name: String,
        age: String,
        phNo: String,
        email: String,
        pass: String,
        job: String
    ) {
        val worker = hashMapOf(
            "name" to name,
            "age" to age,
            "phNo" to phNo,
            "email" to email,
            "pass" to pass,
            "job" to job,
            "busy" to false
        )
        db.collection("Workers").document(email).set(worker)
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


    fun placeOrder(
        userEmail: String,
        workerName: String,
        workerEmail: String,
        address: String,
        paymentMethod: String,
        jobType: String
    ) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val monthName = calendar.getDisplayName(month, Calendar.SHORT, Locale.US)
        val date = "$day $monthName"
        val order = OrderModel(userEmail, workerName, workerEmail, false, address, paymentMethod, jobType, date)

        db.collection("Workers").document(workerEmail).update("busy", true)
        db.collection("Orders").add(order)
    }

    fun getOrder(userEmail: String) : SnapshotStateList<OrderModel?> {
        val orderList = mutableStateListOf<OrderModel?>()
        Log.i("Order", userEmail)
        db.collection("Orders").whereEqualTo("userEmail", userEmail).get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: OrderModel? = d.toObject(OrderModel::class.java)
                        orderList.add(c)
                    }
                }
            }
        return orderList
    }
}

class StoreUserEmail(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("UserEmail")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    val getEmail: Flow<String?> =
        context.datastore.data.map { preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    suspend fun saveEmail(name: String) {
        context.datastore.edit { preference ->
            preference[USER_EMAIL_KEY] = name
        }
    }
}


class StoreJobType(private val context: Context) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("JobType")
        val JOB_TYPE = stringPreferencesKey("job_type")
    }

    val getJobType: Flow<String?> =
        context.datastore.data.map { preferences ->
            preferences[JOB_TYPE] ?: ""
        }

    suspend fun saveJobType(jobType: String) {
        context.datastore.edit { preference ->
            preference[JOB_TYPE] = jobType
        }
    }
}
