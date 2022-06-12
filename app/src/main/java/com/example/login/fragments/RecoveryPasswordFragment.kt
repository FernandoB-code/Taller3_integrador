package com.example.login.fragments

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
import com.example.login.viewModels.RecoveryPasswordViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RecoveryPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = RecoveryPasswordFragment()
    }

    private lateinit var viewModel: RecoveryPasswordViewModel

    private lateinit var v: View
    private lateinit var btnRecoverPw : Button
    private lateinit var txtEmail: EditText
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_recovery_password, container, false)
        btnRecoverPw = v.findViewById(R.id.btnRecPassword)
        txtEmail = v.findViewById(R.id.emailForgotPw)
        auth = FirebaseAuth.getInstance()
        rootLayout = v.findViewById(R.id.emailAdressRec)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnRecoverPw.setOnClickListener {
            sendMail()
        }
    }

    private fun sendMail() {
        val email = txtEmail.text.toString()
        if (email.isEmpty()) {
            Snackbar.make(rootLayout, "Por favor ingresar su Email", Snackbar.LENGTH_SHORT)
                .show()
        } else {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Snackbar.make(
                            rootLayout,
                            "Se envio el mail con exito",
                            Snackbar.LENGTH_LONG
                        ).show()
                        v.findNavController()
                            .navigate(RecoveryPasswordFragmentDirections.actionRecoveryPasswordFragmentToFragment1())

                    } else {
                        Snackbar.make(
                            rootLayout,
                            "Problemas para enviar el mail de recuperación",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecoveryPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}