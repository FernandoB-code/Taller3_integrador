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
import com.example.login.R
import com.example.login.viewModels.DepositMoneyViewModel
import com.google.android.material.snackbar.Snackbar

class DepositMoneyFragment : Fragment() {

    companion object {
        fun newInstance() = DepositMoneyFragment()
    }

    private lateinit var viewModel: DepositMoneyViewModel

    private lateinit var v: View
    private lateinit var textName : TextView
    private lateinit var textCbu : TextView
    private lateinit var textAlias : TextView
    private lateinit var btnTemp : Button
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_deposit_money, container, false)
        textName = v.findViewById(R.id.depositUserName)
        textCbu = v.findViewById(R.id.depositAccountCbu)
        textAlias = v.findViewById(R.id.depositAccountAlias)
        btnTemp = v.findViewById(R.id.btnDepMoney)
        rootLayout = v.findViewById(R.id.frameLayout5)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DepositMoneyViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        viewModel.setAccountDetail(this)
        viewModel.setUserName(this)

        btnTemp.setOnClickListener {
            val action = DepositMoneyFragmentDirections.actionDepositMoneyFragmentToTransaction()
            v.findNavController().navigate(action)
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

    fun setCbuAlias(cbu : String, alias : String) {
        this.textCbu.text = cbu
        this.textAlias.text = alias
    }

    fun setUserName (name : String) {
        this.textName.text = name
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DepositMoneyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}