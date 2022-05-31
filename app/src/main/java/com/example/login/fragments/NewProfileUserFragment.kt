package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.viewModels.NewProfileUserViewModel

class NewProfileUserFragment : Fragment() {

    companion object {
        fun newInstance() = NewProfileUserFragment()
    }

    private lateinit var viewModel: NewProfileUserViewModel

    private lateinit var v: View
    private lateinit var btnSignUp : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_new_profile_user, container, false)

        btnSignUp = v.findViewById(R.id.btnSignUp)
        return v

    }

    override fun onStart() {
        super.onStart()

        btnSignUp.setOnClickListener {
            val action = NewProfileUserFragmentDirections.actionNewProfileUserFragmentToFragment1()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewProfileUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}