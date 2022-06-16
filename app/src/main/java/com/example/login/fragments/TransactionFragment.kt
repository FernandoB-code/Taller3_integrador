package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.adapter.TransactionAdapter
import com.example.login.entity.Account
import com.example.login.fragments.viewModels.TransactionViewModel
import com.example.login.repository.TransactionRepository
import com.example.login.viewModels.TransferMoneyViewModel
import com.google.android.material.snackbar.Snackbar


class TransactionFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionFragment()
    }

    private lateinit var viewModel: TransactionViewModel

    lateinit var v : View
    lateinit var adapter : TransactionAdapter
    var transactionRepository : TransactionRepository = TransactionRepository()
    private lateinit var btnTransf : Button
    private lateinit var btnDep : Button
    private lateinit var btnConv : Button
    private lateinit var txtAmountAccount : TextView
    lateinit var recyclerTransaction : RecyclerView
    private lateinit var rootLayout: ConstraintLayout

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
        rootLayout = v.findViewById(R.id.txframeLayout)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        var amount = viewModel.showData(this)

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

    fun showMessage(message : String){

        val snack: Snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG)
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()

    }

    fun setAmount(amount : String) {
        this.txtAmountAccount.text = amount
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}