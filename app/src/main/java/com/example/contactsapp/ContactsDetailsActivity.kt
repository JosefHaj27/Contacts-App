package com.example.contactsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.databinding.ActivityContactsDetailsBinding

class ContactsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}