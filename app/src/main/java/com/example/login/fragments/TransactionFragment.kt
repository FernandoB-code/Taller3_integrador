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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.adapter.TransactionAdapter
import com.example.login.fragments.viewModels.TransactionViewModel
import com.example.login.repository.TransactionRepository


class TransactionFragment : Fragment() {

    lateinit var v : View
    lateinit var adapter : TransactionAdapter
    var transactionRepository : TransactionRepository = TransactionRepository()

    private lateinit var btnTransf : Button
    private lateinit var btnDep : Button
    private lateinit var btnConv : Button
    private lateinit var txtAmountAccount : TextView


    companion object {
        fun newInstance() = TransactionFragment()
    }

    private lateinit var viewModel: TransactionViewModel

    lateinit var recyclerTransaction : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.transaction_fragment, container, false)
        recyclerTransaction = v.findViewById(R.id.recTransaction)
        btnTransf = v.findViewById(R.id.btnTransfer)
        btnConv = v.findViewById(R.id.btnConvert)
        btnDep = v.findViewById(R.id.btnDeposit)
        txtAmountAccount = v.findViewById(R.id.amountAccount)

        return v
    }

    override fun onStart() {
        super.onStart()

        recyclerTransaction.setHasFixedSize(true)
        recyclerTransaction.layoutManager = LinearLayoutManager(context)
        adapter = TransactionAdapter(transactionRepository.transactionList)
        recyclerTransaction.adapter = adapter

        btnTransf.setOnClickListener {
            val action = TransactionFragmentDirections.actionTransactionToTransferMoneyFragment()
            v.findNavController().navigate(action)
        }

        btnConv.setOnClickListener {
            val action1 = TransactionFragmentDirections.actionTransactionToConvertFragment()
            v.findNavController().navigate(action1)
        }

        btnDep.setOnClickListener {
            val action1 = TransactionFragmentDirections.actionTransactionToDepositMoneyFragment()
            v.findNavController().navigate(action1)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}