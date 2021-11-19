package com.app.cryptowallet

import android.graphics.drawable.Drawable

data class WalletItem(
    var drawable: Drawable,
    var rate: String,
    var amount: String,
    var isPrimaryAccount: Boolean,
)