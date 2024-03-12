package com.example.contactsapp.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.databinding.OneRowBinding
import com.example.contactsapp.model.data.Contact

class ContactAdapter(
    private val context: Context,
    private val contacts: List<Contact>,
    private val listener: onContactClickListener?
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(private val binding: OneRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rowId.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION)
                {
                    val contact = contacts[position]
                    listener?.onContactClicked(contact)
                }
            }
        }
        fun bind(contact: Contact) {
            binding.apply {
                contactNameIdRow.text = contact.name
                contact.image?.let { contactImageIdOneRow.setImageResource(it) }
            }
        }
    }

    interface onContactClickListener {
        fun onContactClicked(contact: Contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = OneRowBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }
}