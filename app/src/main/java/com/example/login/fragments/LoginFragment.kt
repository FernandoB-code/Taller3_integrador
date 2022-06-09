package com.example.login.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.entity.User
import com.example.login.viewModels.LoginViewModel
import com.example.login.repository.TransactionRepository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var v: View
    private lateinit var btnLogin: Button
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnNewProfile : Button
    private lateinit var btnForgotPw : Button
    private lateinit var rootLayout: ConstraintLayout

    companion object {
        fun newInstance() = LoginFragment()
    }

    /*private var userService = userServiceImpl()
    private  var transactionFragment = TransactionFragment()
    var transactionRepository : TransactionRepository = TransactionRepository()
    //var userRepository : UserRepository = UserRepository()
    var userRepositoryFireBase: UserRepositoryFirebase = UserRepositoryFirebase()*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)

        btnLogin = v.findViewById(R.id.btnNavigate)
        inputEmail = v.findViewById(R.id.inputEmail)
        inputPassword = v.findViewById(R.id.inputPassword)
        btnNewProfile = v.findViewById(R.id.btnSign)
        btnForgotPw = v.findViewById(R.id.btnForgotPw)
        rootLayout = v.findViewById(R.id.frameLayout)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        //Va a la pantalla de Registrate
        btnNewProfile.setOnClickListener {
            println("ingresando")
            val action = LoginFragmentDirections.actionFragment1ToNewProfileUserFragment()
            v.findNavController().navigate(action)
        }

        //Va a la pantalla de Recuperar contraseña
        btnForgotPw.setOnClickListener {
            println("recuperar contraseña")
            val action2 = LoginFragmentDirections.actionFragment1ToRecoveryPasswordFragment()
            v.findNavController().navigate(action2)
        }


        //Pantalla de login
        btnLogin.setOnClickListener {

            val mail = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (mail.isNotEmpty() && password.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            //startActivity(Intent(context, MainActivity::class.java))
                            val action = LoginFragmentDirections.actionFragment1ToTransaction()
                            v.findNavController().navigate(action)
                        } else {
                            println("credenciales no válidas")
                            Snackbar.make(
                                rootLayout,
                                "Email o Contraseña incorrecta",
                                Snackbar.LENGTH_LONG
                            )
                                .show()
                        }
                    }
            } else {
                Snackbar.make(rootLayout, "Email o Contraseña incorrecta", Snackbar.LENGTH_LONG)
                    .show()
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

