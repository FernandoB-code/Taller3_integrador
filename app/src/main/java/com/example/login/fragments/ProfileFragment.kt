package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.login.R
import com.example.login.viewModels.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var nameUser: TextView
    private lateinit var dniUser: TextView
    private lateinit var emailUser: TextView
    lateinit var v: View
    lateinit var auth: FirebaseAuth
    private val fbUser = Firebase.auth.currentUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)

        nameUser = v.findViewById(R.id.nameUser)
        dniUser = v.findViewById(R.id.userDni)
        emailUser = v.findViewById(R.id.emailUser)
        auth = Firebase.auth

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            nameUser.text = it.name
            dniUser.text = it.dni
            emailUser.text = it.email
        }

    }

    override fun onStart() {
        super.onStart()

        viewModel.getUser(fbUser?.email.toString())

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}