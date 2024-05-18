package com.example.learn_android_9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity: BaseActivity() {

    private val TAG = "mylog_LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.Button_login)
        buttonLogin.setOnClickListener() {
            val account = findViewById<EditText>(R.id.EditText_account)
            val password = findViewById<EditText>(R.id.EditText_password)
            val accountString = account.text.toString()
            val passwordString = password.text.toString()

            if ((accountString == "admin") && (passwordString =="180516")) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                //登录完成之后需要finish掉登录界面
                finish()
            } else {
                Toast.makeText(this, "error account or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

}