package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return v
    }

    override fun onStart() {
        super.onStart()
        recyclerTransaction.setHasFixedSize(true)

        recyclerTransaction.layoutManager = LinearLayoutManager(context)

        adapter = TransactionAdapter(transactionRepository.transactionList)

        recyclerTransaction.adapter = adapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}