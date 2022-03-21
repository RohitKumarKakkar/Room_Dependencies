package com.kotlin.roomdependencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlin.roomdependencies.Databases.ContactDatabase
import com.kotlin.roomdependencies.Entities.Contact
import com.kotlin.roomdependencies.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase
    lateinit var binding: ActivityMainBinding
    var id: Int = 0
    lateinit var name: String
    lateinit var phone: String
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //ViewModel
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)

        //this is not a good practice - we should use singleton for building DB as we Do in Shared Preferences
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        /* database = Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "Contact DB").build() */

        //Singleton is Created in ContactDatabase.Kt File.
        database = ContactDatabase.getDatabase(this)
    }

    //Reading Data From EditText
    fun readEdtText() {

        if (!binding.edtID.text.isEmpty() || !binding.edtName.text.isEmpty() || !binding.edtPhone.text.isEmpty()) {
            id = Integer.parseInt(binding.edtID.text.toString())
            name = binding.edtName.text.toString()
            phone = binding.edtPhone.text.toString()
        } else {
            id = 0
            name = "Null"
            phone = "Null"
            binding.edtID.error = "Cannot be Empty."
            binding.edtID.requestFocus()
            return
        }

    }

    //Button Clicks
    fun btnClick(view: View) {

        when (view.id) {
            R.id.insertData -> {
                readEdtText()
                id = 0
                mainViewModel.insertContact(Contact(id, name, phone, Date(), 1))
            }

            R.id.updateData -> {
                readEdtText()
                mainViewModel.updateContact(Contact(id, name, phone, Date(), 1))
            }

            R.id.readData -> {
                mainViewModel.allContact
                    .observe(this, Observer {
                        val userList: List<Contact>
                        userList = it
                        Toast.makeText(this, userList.toString(), Toast.LENGTH_SHORT).show()
                    })
            }

            R.id.deleteData -> {
                readEdtText()
                mainViewModel.deleteContact(Contact(id, name, phone, Date(), 1))
            }

        }

    }

}