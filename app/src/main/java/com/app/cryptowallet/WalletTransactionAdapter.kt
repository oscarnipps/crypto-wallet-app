package com.app.cryptowallet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wallet_item.view.*

class WalletTransactionAdapter  : RecyclerView.Adapter<WalletTransactionAdapter.WalletTransactionViewHolder>() {

    private var items: List<WalletTransactionDetails> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletTransactionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.transaction_detail_item, parent, false)

        return WalletTransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletTransactionViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<WalletTransactionDetails>) {
        this.items = items
        notifyDataSetChanged()
    }


    class WalletTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val walletImage = itemView.walletImage
        private val rate = itemView.rate
        private val balance = itemView.balance

        fun bindItem(currentItem: WalletTransactionDetails) {
           /* walletImage.setImageDrawable(currentItem.drawable)

            rate.text = currentItem.rate

            balance.text = currentItem.amount*/
        }


    }
}