package com.home.aesappglidemoduledemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.home.aesappglidemoduledemo.presenter.MainPresenter
import com.home.aesappglidemoduledemo.R
import com.home.aesappglidemoduledemo.common.glide.GlideApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        button_generate_base_64.setOnClickListener {
            val base64String = presenter.generateBase64()
            text_view.text = base64String
        }
        button_load_base_64.setOnClickListener {
            val base64String = presenter.base64String
            GlideApp.with(this)
                .load(base64String)
                .into(image_view)
        }
    }
}