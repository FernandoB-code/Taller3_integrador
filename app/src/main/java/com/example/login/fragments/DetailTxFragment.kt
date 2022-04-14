package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.login.R
import com.example.login.fragments.viewModels.DetailTxViewModel
import com.google.android.material.snackbar.Snackbar

class DetailTxFragment : Fragment() {

    companion object {
        fun newInstance() = DetailTxFragment()
    }

    private lateinit var viewModel: DetailTxViewModel

    private lateinit var v: View

    private lateinit var textView2: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.detail_tx_fragment, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()

        var txType = DetailTxFragmentArgs.fromBundle(requireArguments()).detalle
         Snackbar.make(v,txType,Snackbar.LENGTH_SHORT).show()
        textView2 = v.findViewById(R.id.textView2)
        textView2.text = txType
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTxViewModel::class.java)
        // TODO: Use the ViewModel
    }

}