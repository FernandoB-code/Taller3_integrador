package com.example.login.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.activities.MainActivity
import com.example.login.viewModels.NewProfileUserViewModel
import com.google.android.material.snackbar.Snackbar

class NewProfileUserFragment : Fragment() {

    companion object {
        fun newInstance() = NewProfileUserFragment()
    }

    private lateinit var viewModel: NewProfileUserViewModel

    private lateinit var v: View

    private lateinit var userName: TextView
    private lateinit var userPassword: TextView
    private lateinit var userMail: TextView
    private lateinit var userId: TextView
    private lateinit var btnSignUp : Button
    private lateinit var rootLayout : ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_new_profile_user, container, false)

        userName = v.findViewById(R.id.userName)
        userPassword = v.findViewById(R.id.password)
        userMail = v.findViewById(R.id.emailAdress)
        userId = v.findViewById(R.id.userId)
        btnSignUp = v.findViewById(R.id.btnSignUp)
        rootLayout = v.findViewById(R.id.registrate)
        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewProfileUserViewModel::class.java)
        viewModel.success.observe(viewLifecycleOwner) {
            if (it.toString() == "success") {
                startActivity(Intent(activity, MainActivity::class.java))
            } else Snackbar.make(v, it, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onStart() {

        super.onStart()

        btnSignUp.setOnClickListener {

            if (userName.text.isNotEmpty() && userPassword.text.isNotEmpty() && userMail.text.isNotEmpty() && userId.text.isNotEmpty() ) {
                viewModel.registerUser(
                    userName.text.toString(),
                    userPassword.text.toString(),
                    userMail.text.toString(),
                    userId.text.toString(),
                    requireContext()
                )
            } else {
                Snackbar.make(rootLayout, "Datos incompletos", Snackbar.LENGTH_LONG).show()
            }

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewProfileUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}