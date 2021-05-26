package com.example.shiftlab2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        btn_welcom.setOnClickListener(View.OnClickListener {
            createWelcomeDialog()
        })
    }
    private fun createWelcomeDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Приветствие")

        builder.setMessage("Привет, ${intent.getStringExtra("name")} ${intent.getStringExtra("firstname")}!!" +
                "Поздравляем с успешной регистрацией в приложении VipTigo))")
        builder.setNegativeButton("Спасибо!"){dialog, i->
        }
        builder.show()

    }
}
