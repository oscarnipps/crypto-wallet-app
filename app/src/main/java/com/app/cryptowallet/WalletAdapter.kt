package com.app.cryptowallet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wallet_item.view.*

class WalletAdapter : RecyclerView.Adapter<WalletAdapter.WalletViewHolder>() {

    private var items: List<WalletItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.wallet_item, parent, false)

        return WalletViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<WalletItem>) {
        this.items = items
        notifyDataSetChanged()
    }


    class WalletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val walletImage = itemView.walletImage
        private val rate = itemView.rate
        private val balance = itemView.balance

        fun bindItem(currentItem: WalletItem) {
            walletImage.setImageDrawable(currentItem.drawable)

            rate.text = currentItem.rate

            balance.text = currentItem.amount
        }


    }
}