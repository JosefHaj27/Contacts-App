package com.example.contactsapp.model.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Contact(
    val id: Int,
    val name: String?,
    val mobileNumber: Long?,
    val workNumber: Long?,
    val email: String?,
    val image: Int?
): Parcelable{
}
