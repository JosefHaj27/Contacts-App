package com.example.contactsapp.view.ui

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.databinding.ActivityContactsDetailsBinding
import com.example.contactsapp.model.data.Contact

class ContactsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsDetailsBinding
    private val TAG = "Contacts_Details_Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        val contact = getContactFromIntent()

        binding.apply {
            backBtn.setOnClickListener {
                backButton()
            }
            contact?.let {
                contactNameId.text = it.name
                (contactMobileNumberId as TextView).text = it.mobileNumber?.toString()
                (contactMailId as TextView).text = it.email
                (contactWorkNumberId as TextView).text = it.workNumber?.toString()
                it.image?.let { imgInt -> contactImageId.setImageResource(imgInt) }
            }
        }
        
    }

    private fun backButton() {
        finish()
    }

    private fun getContactFromIntent(): Contact? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", Contact::class.java)
        } else {
            intent.getParcelableExtra("data")
        }
    }
}