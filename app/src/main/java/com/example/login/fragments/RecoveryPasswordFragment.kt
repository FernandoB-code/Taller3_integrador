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
import com.example.login.viewModels.RecoveryPasswordViewModel

class RecoveryPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = RecoveryPasswordFragment()
    }

    private lateinit var viewModel: RecoveryPasswordViewModel

    private lateinit var v: View
    private lateinit var btnRecoverPw : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_recovery_password, container, false)
        btnRecoverPw = v.findViewById(R.id.btnRecPassword)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnRecoverPw.setOnClickListener {
            val action = RecoveryPasswordFragmentDirections.actionRecoveryPasswordFragmentToFragment1()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecoveryPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}