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
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.activities.MainActivity
import com.example.login.viewModels.NewProfileUserViewModel
import com.example.login.viewModels.TransferMoneyViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers

class TransferMoneyFragment : Fragment() {

    companion object {
        fun newInstance() = TransferMoneyFragment()
    }

    private lateinit var viewModel: TransferMoneyViewModel

    private lateinit var v: View
    private lateinit var cvuAlias: TextView
    private lateinit var amount: EditText
    private lateinit var btnTemp : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_transfer, container, false)
        cvuAlias = v.findViewById(R.id.inputCbuAlias)
        amount = v.findViewById(R.id.inputAmount)
        btnTemp = v.findViewById(R.id.btnTransfAmount)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransferMoneyViewModel::class.java)

    }

    override  fun onStart() {
        super.onStart()

        btnTemp.setOnClickListener {

            viewModel.transfer(
                cvuAlias.text.toString(),
                amount.text.toString().toDouble()
            )

            /*val action = TransferMoneyFragmentDirections.actionTransferMoneyFragmentToTransaction()
            v.findNavController().navigate(action)*/
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransferMoneyViewModel::class.java)
        // TODO: Use the ViewModel
    }
}