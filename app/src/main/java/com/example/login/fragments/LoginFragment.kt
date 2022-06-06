package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.example.login.repository.UserRepositoryFirebase
import com.example.login.service.impl.userServiceImpl
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private lateinit var viewModel: Fragment1ViewModel

    private lateinit var v: View

    private lateinit var btnNavigate: Button
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnNewProfile : Button
    private lateinit var btnForgotPw : Button



    private var userService = userServiceImpl()

    private  var transactionFragment = TransactionFragment()

    var transactionRepository : TransactionRepository = TransactionRepository()

    //var userRepository : UserRepository = UserRepository()

    var userRepositoryFireBase: UserRepositoryFirebase = UserRepositoryFirebase()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)

        btnNavigate = v.findViewById(R.id.btnNavigate)
        inputEmail = v.findViewById(R.id.inputEmail)
        inputPassword = v.findViewById(R.id.inputPassword)
        btnNewProfile = v.findViewById(R.id.btnSign)
        btnForgotPw = v.findViewById(R.id.btnForgotPw)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnNewProfile.setOnClickListener {
            val action1 = LoginFragmentDirections.actionFragment1ToNewProfileUserFragment()
            v.findNavController().navigate(action1)
        }

        btnForgotPw.setOnClickListener {
            val action2 = LoginFragmentDirections.actionFragment1ToRecoveryPasswordFragment()
            v.findNavController().navigate(action2)
        }

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


            //val foundUser : User? = userService.findByEmaiAndPassword(userRepository.userList ,inputEmail.editableText.toString(),inputPassword.editableText.toString())
            val foundUser : User? = null

            if (foundUser != null ) {
                val action = LoginFragmentDirections.actionFragment1ToTransaction()
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

