package com.example.login.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.login.R
import com.example.login.viewModels.TransferMoneyViewModel
import com.google.android.material.snackbar.Snackbar


class TransferMoneyFragment : Fragment() {

    companion object {
        fun newInstance() = TransferMoneyFragment()
    }

    private lateinit var viewModel: TransferMoneyViewModel

    private lateinit var rootLayout: ConstraintLayout
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
        rootLayout = v.findViewById(R.id.frameLayout4)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransferMoneyViewModel::class.java)

    }

    override  fun onStart() {
        super.onStart()

        btnTemp.setOnClickListener {

            if (amount.text.isNotEmpty() && cvuAlias.text.isNotEmpty()) {
                viewModel.transfer(cvuAlias.text.toString(), amount.text.toString().toDouble(), this)
            } else {
                Snackbar.make(rootLayout,"Debe completar los campos", Snackbar.LENGTH_LONG).show()
            }

            /*val action = TransferMoneyFragmentDirections.actionTransferMoneyFragmentToTransaction()
            v.findNavController().navigate(action)*/
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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransferMoneyViewModel::class.java)
        // TODO: Use the ViewModel
    }
}