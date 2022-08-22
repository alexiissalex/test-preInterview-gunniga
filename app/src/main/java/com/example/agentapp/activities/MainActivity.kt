package com.example.agentapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mDatabase: FirebaseDatabase? = null
    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        sharedPreferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX",false)

//      open app after checkbox in sign in page
        if(isRemembered){
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_signinhome.setOnClickListener {
        var email = et_email.text.toString().trim()
        var password = et_password.text.toString().trim()
        var checked: Boolean = cb_remember.isChecked

        if(!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task: Task<AuthResult> ->
            if (task.isSuccessful) {
                var editor: SharedPreferences.Editor = sharedPreferences.edit()
/*              editor.putString("name",null)
                editor.putString("email",email)
                editor.putBoolean("CHECKBOX",checked)
                editor.apply()
*/
                editor.apply {
                    putString("name",null)
                    putString("email",email)
                    putBoolean("CHECKBOX",checked)
                    apply()
                }

                Toast.makeText(this, "Sign in Successful", Toast.LENGTH_LONG).show()

                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Sign in Unsuccessful", Toast.LENGTH_LONG).show()
            }
        }
        }else {
            Toast.makeText(this, "Please check your email password", Toast.LENGTH_LONG).show()
        }
        }

        tv_forgotpasswordhome.setOnClickListener {
            var intent = Intent (this, ForgotPassowordActivity::class.java)
            startActivity(intent)
        }

        tv_signup.setOnClickListener {
            var intent = Intent (this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}