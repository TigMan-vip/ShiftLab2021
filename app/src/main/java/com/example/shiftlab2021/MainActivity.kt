package com.example.shiftlab2021

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var formatDate = SimpleDateFormat("dd MMM YYY", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_register.setOnClickListener(View.OnClickListener {
            when {
                name.text.toString().length <= 2 || name.text.toString().length > 20 -> {Toast.makeText(applicationContext, "Имя должно содержать от 3 до 20 букв",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                name.text.toString().contains(".[А-Я]".toRegex()) -> {Toast.makeText(applicationContext, "Только первая буква имени должна быть заглавной",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                name.text.toString().contains(" ".toRegex()) -> { Toast.makeText(applicationContext, "Имя не должно содержать пробелов", Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                !name.text.toString().matches(".[а-я]{1,20}".toRegex()) -> {Toast.makeText(applicationContext, "Имя должно содержать только кириллицу",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                name.text.toString().matches("[^А-Я]+".toRegex())-> {Toast.makeText(applicationContext, "Имя должно начаться с заглавной буквы",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}

                firstname.text.toString().length <= 2 || name.text.toString().length > 30 -> {Toast.makeText(applicationContext, "Фамилия должна содержать от 3 до 30 букв",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                firstname.text.toString().contains(".[А-Я]".toRegex()) -> {Toast.makeText(applicationContext, "Только первая буква фамилии должна быть заглавной",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                firstname.text.toString().contains(" ".toRegex()) -> { Toast.makeText(applicationContext, "Фамилия не должна содержать пробелов", Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                !firstname.text.toString().matches(".[а-я]{1,30}".toRegex()) -> {Toast.makeText(applicationContext, "Фамилия должна содержать только кириллицу",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                firstname.text.toString().matches("[^А-Я]+".toRegex())-> {Toast.makeText(applicationContext, "Фамилия должна начаться с заглавной буквы",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}

                password.text.toString().length < 8 || name.text.toString().length > 20 -> {Toast.makeText(applicationContext, "Пароль должен содержать от 8 до 20 символов",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                !password.text.toString().contains("[0-9]".toRegex()) -> {Toast.makeText(applicationContext, "Пароль должен содержать хотя бы одну цифру",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
                !password.text.toString().contains("[А-ЯA-Z]".toRegex()) -> {Toast.makeText(applicationContext, "Пароль должен содержать заглавную букву",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}

                password.text.toString() != check_password.text.toString() -> {Toast.makeText(applicationContext, "Пароли не совпадают!",Toast.LENGTH_SHORT).show()
                    return@OnClickListener}
            }
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("name", name.text.toString())
            intent.putExtra("firstname", firstname.text.toString())
            startActivity(intent)
        })

        btn_date.setOnClickListener(View.OnClickListener {
            val getDate:Calendar = Calendar.getInstance()
            val datepicker = DatePickerDialog(this, android.R.style
                    .Theme_Holo_Light,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        val selectDate:Calendar = Calendar.getInstance()
                        selectDate.set(Calendar.YEAR,year)
                        selectDate.set(Calendar.MONTH,month)
                        selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        val date:String = formatDate.format(selectDate.time)
                        text_date.setText(date)
                        text_date.setTextColor(-0x1000000)
                    }, getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datepicker.show()
        })


        val loginTextwatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_register.setEnabled(!name.text.toString().isEmpty()
                        && !firstname.text.toString().isEmpty()
                        && !text_date.text.toString().isEmpty()
                        && !password.text.toString().isEmpty()
                        && !check_password.text.toString().isEmpty())
            }
        }
        name.addTextChangedListener(loginTextwatcher)
        firstname.addTextChangedListener(loginTextwatcher)
        text_date.addTextChangedListener(loginTextwatcher)
        password.addTextChangedListener(loginTextwatcher)
        check_password.addTextChangedListener(loginTextwatcher)
    }

}