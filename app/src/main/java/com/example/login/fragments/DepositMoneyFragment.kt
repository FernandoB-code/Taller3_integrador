package com.example.login.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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

        viewModel.setAccountDetail(textCbu, textAlias)
        viewModel.setUserName(textName)

        btnTemp.setOnClickListener {
            //val action = DepositMoneyFragmentDirections.actionDepositMoneyFragmentToTransaction()
            //v.findNavController().navigate(action)
            val depositData = "Nombre: " +textName.text + "\nCBU: " + textCbu.text + "\nAlias: " + textAlias.text
            val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("datos", depositData)
            clipboard.setPrimaryClip(clip)
            Snackbar.make(rootLayout,"Datos copiados", Snackbar.LENGTH_LONG).show()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DepositMoneyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}