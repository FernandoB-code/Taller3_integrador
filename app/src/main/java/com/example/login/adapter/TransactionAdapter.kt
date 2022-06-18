package com.example.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.entity.Transaction
import com.example.login.entity.TransactionDetail
import com.example.login.fragments.TransactionFragmentDirections


class TransactionAdapter ( var transactionHistory : MutableList<TransactionDetail>) : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    class TransactionHolder (v : View) : RecyclerView.ViewHolder(v){

            private var view : View

            init {
                this.view = v
            }

        fun setAmount(amount: Double){
            var txtAmount : TextView = view.findViewById(R.id.txtMovAmount)
            txtAmount.text = amount.toString();
        }

        fun getTxItem() : View{
            return view.findViewById(R.id.txtItem)
        }

        fun setTxtType(txType: String) {
            var txtType : TextView = view.findViewById(R.id.transactionDetail)
            txtType.text = txType
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent,false)
            return(TransactionHolder(view))
    }



    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.setAmount(transactionHistory[position].amount)
        holder.setTxtType(transactionHistory[position].transactionType.type)

        holder.getTxItem().setOnClickListener{
            val action = TransactionFragmentDirections.actionTransactionToDetailTxFragment(transactionHistory[position].accountTO, transactionHistory[position].amount.toString(), transactionHistory[position].date)
            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return transactionHistory.size
    }

}