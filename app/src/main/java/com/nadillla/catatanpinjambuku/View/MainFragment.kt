package com.nadillla.catatanpinjambuku.View

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nadillla.catatanpinjambuku.Helper.SessionManager
import com.nadillla.catatanpinjambuku.Local.User
import com.nadillla.catatanpinjambuku.R
import com.nadillla.catatanpinjambuku.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), View.OnClickListener{

    lateinit var navController : NavController
    private  lateinit var userViewModel: UserViewModel
    
    private lateinit var  sessionManager: SessionManager
    
    var get_email: String? = null
    var get_password: String? = null
    var get_name: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        tvSignup.setOnClickListener(this)
        btnLogin.setOnClickListener(this)

        attachObserve()

    }

    private fun attachObserve() {

        userViewModel._responseActionUser.observe(viewLifecycleOwner,Observer{loginSuccess(it)})
        userViewModel._isErrorUser.observe(viewLifecycleOwner,Observer{errorLogin(it)})
    }

    private fun errorLogin(it: Throwable?) {
        Toast.makeText(context,"Email Belum terdaftar", Toast.LENGTH_SHORT).show()
        Log.d("TAG", "errorLogin: ${it?.message}")

    }

    private fun loginSuccess(it: User) {
        Log.d("TAG", "loginSuccess: OK")
        navController.navigate(R.id.action_mainFragment_to_home2Activity)
        

    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        
        get_email = arguments?.getString("email")
        get_password = arguments?.getString("password")
        get_name = arguments?.getString("name")
        
        
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvSignup-> navController.navigate(R.id.action_mainFragment_to_registerFragment)
            R.id.btnLogin->
                if(edusername.text.toString().isEmpty()){
                    edusername.error ="Email tidak boleh kosong"

                }else if(edpassword.text.toString().isEmpty()){
                    edpassword.error="Password idak boleh kosong"
                }else{
                    userViewModel.loginUser(edusername.text.toString(),edpassword.text.toString())
                }
        }
    }


}