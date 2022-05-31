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
import com.example.login.viewModels.ConvertViewModel

class ConvertFragment : Fragment() {

    companion object {
        fun newInstance() = ConvertFragment()
    }

    private lateinit var viewModel: ConvertViewModel

    private lateinit var v: View
    private lateinit var btnTemp : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_convert, container, false)
        btnTemp = v.findViewById(R.id.btnConvAmount)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnTemp.setOnClickListener {
            val action = ConvertFragmentDirections.actionConvertFragmentToTransaction()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConvertViewModel::class.java)
        // TODO: Use the ViewModel
    }

}