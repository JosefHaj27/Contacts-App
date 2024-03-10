package com.example.contactsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.contactsapp.databinding.ActivityContactsDetailsBinding
import com.example.contactsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        initializing()
    }

    private fun initializing() { //TODO:: this is wrong, edit it to be suitable.
        val binding = ActivityContactsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun handlingFragmentsAddOrReplace(fragment: Fragment, fragTag: String) {
        val fragmentManager = supportFragmentManager
        var fragmentExists = fragmentManager.findFragmentByTag(fragTag)

        if (fragmentExists == null) {
            addFragment(fragment, fragTag)
        } else {
            replaceFragment(fragment, fragTag)
        }
    }

    private fun addFragment(fragment: Fragment, fragTag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(binding.frameLayoutId.id, fragment, fragTag)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment, fragTag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.frameLayoutId.id, fragment, fragTag)
        fragmentTransaction.commit()
    }
}