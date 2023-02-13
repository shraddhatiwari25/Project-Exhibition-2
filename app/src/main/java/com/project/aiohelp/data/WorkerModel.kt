package com.project.aiohelp.data

data class WorkerModel(
    var name: String? = null,
    var age: String? = null,
    var phNo: String? = null,
    var email: String? = null,
    var pass: String? = null,
    var job: String? = null,
    var busy: Boolean = false,
    var ratings: Float = 0f,
    var noOfRatings: Int = 0
)
