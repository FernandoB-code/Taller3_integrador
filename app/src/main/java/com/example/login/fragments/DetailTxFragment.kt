package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.fragments.viewModels.DetailTxViewModel
import com.google.android.material.snackbar.Snackbar

class DetailTxFragment : Fragment() {

    companion object {
        fun newInstance() = DetailTxFragment()
    }

    private lateinit var viewModel: DetailTxViewModel

    private lateinit var v: View
    private lateinit var btnTemp : Button
    private lateinit var accountDestiny: TextView
    private lateinit var accountAmount: TextView
    private lateinit var date: TextView
    private lateinit var type : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.detail_tx_fragment, container, false)
        btnTemp = v.findViewById(R.id.btnBack)
        return v
    }

    override fun onStart() {
        super.onStart()

        var accountDestinyTEXT = DetailTxFragmentArgs.fromBundle(requireArguments()).accountDestiny
        var amountTEXT = DetailTxFragmentArgs.fromBundle(requireArguments()).amount
        var dateTx = DetailTxFragmentArgs.fromBundle(requireArguments()).date
        var typeText = DetailTxFragmentArgs.fromBundle(requireArguments()).type

        accountDestiny = v.findViewById(R.id.detDestino)
        accountDestiny.text = accountDestinyTEXT

        accountAmount = v.findViewById(R.id.detMonto)
        accountAmount.text = amountTEXT

        date = v.findViewById(R.id.detFecha)
        date.text = dateTx

        type = v.findViewById(R.id.detTipo)
        type.text = typeText

        btnTemp.setOnClickListener {
            val action = DetailTxFragmentDirections.actionDetailTxFragmentToTransaction()
            v.findNavController().navigate(action)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTxViewModel::class.java)
        // TODO: Use the ViewModel
    }

}