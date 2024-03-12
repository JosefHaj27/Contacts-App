package com.example.contactsapp.view

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.databinding.ActivityMainBinding
import com.example.contactsapp.model.adapter.ContactAdapter
import com.example.contactsapp.model.data.Contact
import com.example.contactsapp.view.ui.ContactsDetailsActivity

class MainActivity : AppCompatActivity(), ContactAdapter.onContactClickListener {
    private lateinit var binding: ActivityMainBinding
    private val TAG = "Main_Activity"
    private var contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializing()
    }

    private fun initializing() {
        // check permisson of contacts if granted or not, if not then request permission.
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            loadContacts()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_READ_CONTACTS_PERMISSION
            )
        }

        binding.contactRecyclerViewId.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContactAdapter(context, contactList, this@MainActivity)
        }
    }

    private fun loadContacts() {
        val nameAndIdCursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        nameAndIdCursor?.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndex(ContactsContract.Contacts._ID))
                val name = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                addToListOfContact(id, name, null, null, null, null)
            }
            it.close()
        }
    }

    private fun addToListOfContact(
        id: Int,
        name: String,
        mobileNumber: Long?,
        workNumber: Long?,
        email: String?,
        image: Int?
    ) {
        val contact = Contact(id, name, mobileNumber, workNumber, email, image)
        contactList.add(contact)
    }

    override fun onContactClicked(contact: Contact) {
        val newIntent = Intent(this, ContactsDetailsActivity::class.java)
        newIntent.putExtra("data", contact)
        startActivity(newIntent)
    }

    /*
        called when the user responds to the permission request dialog.
        It checks if the request code matches the REQUEST_READ_CONTACTS_PERMISSION
        and if the permission is granted.
        If the permission is granted, it calls the loadContacts method to fetch contacts.
        If the permission is denied, it toast a message to the user.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
//        out is a declaration-site variance
//        it means that you are dealing with a read-only array of strings representing permissions that are being requested.
//        the array won't be modified, it is used to ensure type safety.
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_READ_CONTACTS_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts()
            } else {
                Toast.makeText(this, "The permission has been denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val REQUEST_READ_CONTACTS_PERMISSION = 100
    }
}
