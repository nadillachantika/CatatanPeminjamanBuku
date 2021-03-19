package com.nadillla.catatanpinjambuku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nadillla.catatanpinjambuku.Helper.SessionManager
import com.nadillla.catatanpinjambuku.Home.Home2Activity
import com.nadillla.catatanpinjambuku.View.MainActivity

class SplashscreenActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 3000 //3 seconds
    private var mDelayHandler: Handler? = null
    private var progressBarStatus = 0
    var dummy: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val session = SessionManager(this)
        if (session.login ?: true) {
            startActivity(Intent(this, Home2Activity::class.java))
            finish()

        } else {
            Handler().postDelayed({

                startActivity(Intent(this, MainActivity::class.java))

                finish()
            }, 2000)
        }
    }

}