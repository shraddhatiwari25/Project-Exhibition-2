package com.project.aiohelp.data

import com.google.firebase.Timestamp

data class OrderModel(
    var userEmail: String? = null,
    var workerName: String? = null,
    var workerEmail: String? = null,
    var completed: Boolean = false,
    var address: String? = null,
    var paymentMethod: String? = null,
    var jobType: String? = null,
    var date: String? = null
)
