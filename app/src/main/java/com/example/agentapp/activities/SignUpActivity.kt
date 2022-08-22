package com.example.agentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.agentapp.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    var mAuth:FirebaseAuth? = null
    var mDatabase: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        btn_signup.setOnClickListener {
            var person_name = edt_personname.text.toString().trim()
            var email = et_emailsignup.text.toString().trim()
            var password = et_passwordsignup.text.toString().trim()
            var confirm_password = et_confirmpassword.text.toString().trim()

            if(password.length < 6){
                et_passwordsignup.error = "Enter a password of more than 5 characters"
            }

            if(confirm_password != password){
                et_confirmpassword.error = "Incorrect password"
            }

            if(!TextUtils.isEmpty(person_name)
                && !TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password)){
                mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    task: Task<AuthResult> ->

                    if(task.isSuccessful){
                        var user = mAuth!!.currentUser
                        var userId = user!!.uid

                        var  userRef = mDatabase!!.reference.child("Users").child(userId)

                        var userObject = HashMap<String,String>()
                        userObject.put("person_name",person_name)
                        userObject.put("image","default")
                        userObject.put("thumb_image","default")

                        userRef.setValue(userObject).addOnCompleteListener {
                            task: Task<Void> ->

                            if(task.isSuccessful){
                                Toast.makeText(this,"Sign up Successful",Toast.LENGTH_LONG).show()
                            }else
                                Toast.makeText(this,"Sign up Unsuccessful",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }
        imv_backpage.setOnClickListener {
            onBackPressed()
        }
    }
}