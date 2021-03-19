package com.nadillla.catatanpinjambuku.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nadillla.catatanpinjambuku.Local.CatatanDatabase
import com.nadillla.catatanpinjambuku.R
import com.nadillla.catatanpinjambuku.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment(), View.OnClickListener{

    private var catatanDatabase: CatatanDatabase? = null
    lateinit var navController : NavController

    private lateinit var  userViewModel: UserViewModel

    var get_name: String? = null
    var get_email: String? = null
    var get_password: String? = null






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        catatanDatabase = context?.let{ CatatanDatabase.getInstance(it)}

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnSignup.setOnClickListener(this)
        btnBack.setOnClickListener(this)

        attachObserver()



    }

    private fun attachObserver() {

        //cek email

        userViewModel._responseActionUser.observe(viewLifecycleOwner, Observer { gotEmail() })
        userViewModel._isErrorUser.observe(viewLifecycleOwner,Observer{ emptyEmail()})
    }

    private fun emptyEmail() {
         userViewModel.registerUser(
             null,
             edemail.text.toString(),
             edusername.text.toString(),
             edpassword.text.toString(),
             edconfirmpasswd.text.toString()


         )
        navController.navigate(R.id.action_registerFragment_to_resultFragment)
    }

    private fun gotEmail() {
        Toast.makeText(context,"Register gagal, Emali sudah terdaftar", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View?) {

        when(view?.id){
            R.id.btnSignup-> {
               userViewModel.gotEmail(edemail.text.toString())


            }

            R.id.btnBack->activity?.onBackPressed()
        }
    }


}