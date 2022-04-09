package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.entity.User
import com.example.login.fragments.viewModels.Fragment1ViewModel
import com.example.login.repository.TransactionRepository
import com.example.login.repository.UserRepository
import com.example.login.service.impl.userServiceImpl
import com.example.login.service.userService
import com.google.android.material.snackbar.Snackbar



class Fragment1 : Fragment() {

    private lateinit var viewModel: Fragment1ViewModel

    private lateinit var v: View

    private lateinit var btnNavigate: Button
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText



    private var userService = userServiceImpl()

    private  var transactionFragment = TransactionFragment()

    var transactionRepository : TransactionRepository = TransactionRepository()

    var userRepository : UserRepository = UserRepository()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment1_fragment, container, false)

        btnNavigate = v.findViewById(R.id.btnNavigate)
        inputEmail = v.findViewById(R.id.inputEmail)
        inputPassword = v.findViewById(R.id.inputPassword)

        return v
    }

    override fun onStart() {
        super.onStart()

        //navegar a la otra pantalla

        btnNavigate.setOnClickListener {

            if (inputEmail.editableText.isNullOrBlank() || inputPassword.editableText.isNullOrBlank()) {
                val snackFields = Snackbar.make(
                    it,
                    "Todos los campos deben contener valores",
                    Snackbar.LENGTH_LONG
                )
                snackFields.show()

            }


            val foundUser : User? = userService.findByEmaiAndPassword(userRepository.userList ,inputEmail.editableText.toString(),inputPassword.editableText.toString())


            if (foundUser != null ) {
                val action = Fragment1Directions.actionFragment1ToTransaction()
                v.findNavController().navigate(action)

            } else {

                val snackLogin = Snackbar.make(
                    it,
                    "Usuario o contraseña invalido",
                    Snackbar.LENGTH_LONG
                )
                snackLogin.show()

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Fragment1ViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

