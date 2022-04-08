package com.example.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.entity.Transaction


class TransactionAdapter ( var transactionList : MutableList<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    class TransactionHolder (v : View) : RecyclerView.ViewHolder(v){
            private var view : View
            init {
                this.view = v
            }

        fun setAmount(amount: Double){
            var txtAmount : TextView = view.findViewById(R.id.txItemTransaction)
            txtAmount.text = amount.toString();
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent,false)
            return(TransactionHolder(view))
    }



    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.setAmount(transactionList[position].amount)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }


}