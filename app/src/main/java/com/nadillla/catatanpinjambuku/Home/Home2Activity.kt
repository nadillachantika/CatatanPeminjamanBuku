package com.nadillla.catatanpinjambuku.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.nadillla.catatanpinjambuku.R
import com.nadillla.catatanpinjambuku.View.MainActivity
import kotlinx.android.synthetic.main.activity_home2.*

class Home2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        val navController = Navigation.findNavController(this,R.id.home_nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.logout_option,menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.getItemId()

        if(id==R.id.logout){
            AlertDialog.Builder(this).apply {
                setTitle("Keluar")
                setMessage("Yakin ingin keluar ?")
                setCancelable(false)
                setPositiveButton("Yakin"){
                    dialogInterface, i ->
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
                setNegativeButton("Batal"){
                    dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            }.show()
        }
        return super.onOptionsItemSelected(item)
    }
}