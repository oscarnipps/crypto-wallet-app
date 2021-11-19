package com.app.cryptowallet

import android.graphics.drawable.Drawable

data class WalletTransactionDetails(
    var drawable: Drawable,
    var date: String,
    var receiverName: String,
    var amountPaid: String,
    var account: String,
    var amountBalance: String
)