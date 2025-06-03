package com.rajdeep.kotlinrealtimedatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.ln

class MainActivity : AppCompatActivity() {

    private lateinit var fname : EditText
    private lateinit var mname : EditText
    private lateinit var lname : EditText
    private lateinit var contact : EditText
    private lateinit var register : Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fname = findViewById<EditText>(R.id.fname)
        mname = findViewById<EditText>(R.id.mname)
        lname = findViewById<EditText>(R.id.lname)
        contact = findViewById<EditText>(R.id.contactNo)
        register = findViewById<Button>(R.id.register)

        register.setOnClickListener {
            val firstname = fname.text.toString()
            val middlename = mname.text.toString()
            val lastname = lname.text.toString()
            val contect = contact.text.toString()
            val dataclass = DataClass(firstname,middlename,lastname,contect)
            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            databaseReference.child(contect).setValue(dataclass)
                .addOnSuccessListener {
                    fname.text.clear()
                    mname.text.clear()
                    lname.text.clear()
                    contact.text.clear()

                    Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {

                    Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                }
        }
    }
}