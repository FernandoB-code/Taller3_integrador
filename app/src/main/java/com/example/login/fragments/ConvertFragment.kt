package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.entity.APIService
import com.example.login.viewModels.ConvertViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConvertFragment : Fragment() {

    companion object {
        fun newInstance() = ConvertFragment()
    }

    private lateinit var viewModel: ConvertViewModel

    private lateinit var v: View
    private lateinit var btnTemp: Button
    private lateinit var inpAmount: EditText // Clara
    private lateinit var showAmount: TextView // Clara
    private lateinit var rootLayout: ConstraintLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_convert, container, false)
        btnTemp = v.findViewById(R.id.btnConvAmount)
        inpAmount = v.findViewById(R.id.inputAmountConv)
        showAmount = v.findViewById(R.id.outputAmount)
        rootLayout = v.findViewById(R.id.frameLayout6)
        return v
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConvertViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        btnTemp.setOnClickListener {
            val action = ConvertFragmentDirections.actionConvertFragmentToTransaction()
            v.findNavController().navigate(action)
            viewModel.searchByAmount(inpAmount.inputType)
            showAmount.text = viewModel.sendMoney().toString()

        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConvertViewModel::class.java)
        // TODO: Use the ViewModel
    }



    fun showMessage(message : String){



        val snack: Snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG)
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()

    }


}
