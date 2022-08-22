package com.example.agentapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.agentapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_forgot_passoword.*

class ForgotPassowordActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mDatabase: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_passoword)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        iv_backforgotpassword.setOnClickListener {
            onBackPressed()
        }
        iv_logoforgotpassword.setOnClickListener {
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
        btn_Sent.setOnClickListener {
            var email = et_emailforgotpassword.text.toString().trim()

            if(TextUtils.isEmpty(email)){
                et_emailforgotpassword.error = "Please enter your email"
            }else{
                mAuth!!.sendPasswordResetEmail(email).addOnCompleteListener {
                    task -> if(task.isSuccessful){
                        Toast.makeText(this,"Please Check your email",Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this,"Send password reset failed",Toast.LENGTH_LONG).show()
                }
                }
            }
        }

    }
}