package com.example.apprenticeship.ui.adapters

import com.example.apprenticeship.R

class CurrencyImageAdapter {
    companion object{
        fun getImage(book: String): Int {
            return when{
                book.contains("btc") ->  R.drawable.ic_bitcoin
                book.contains("eth") -> R.drawable.ic_ether
                book.contains("xrp") -> R.drawable.ic_xrp
                book.contains("ltc") -> R.drawable.ic_litecoin
                book.contains("bch") -> R.drawable.ic_bitcoincash
                book.contains("tusd")-> R.drawable.ic_trueusd
                book.contains("mana")-> R.drawable.ic_mana
                book.contains("gnt") -> R.drawable.ic_golem
                book.contains("bat") -> R.drawable.ic_bat
                book.contains("dai") -> R.drawable.ic_dai
                else -> -1
            }
        }
    }
}