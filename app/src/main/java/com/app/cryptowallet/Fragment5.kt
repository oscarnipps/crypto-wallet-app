package com.app.cryptowallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.app.cryptowallet.databinding.Fragment1Binding

class Fragment5 : Fragment() {

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var binding: Fragment1Binding
    private lateinit var viewPager: ViewPager2
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment1, container, false)

        navController = findNavController()

        viewPager = binding.walletViewPager

        setUpOnBoardingItems()

        viewPager.adapter = walletAdapter


        viewPager.setPageTransformer { page, position ->

            if (position >= 0) {
                /* page.scaleX = 0.9f

                 page.scaleY = 0.9f*/

                page.translationX = -page.width * position

                page.translationY = -30 * position
            }
        }

        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        viewPager.offscreenPageLimit = 3

        return binding.root
    }

    private fun setUpOnBoardingItems() {
        val walletItems = mutableListOf<WalletItem>()

        val walletRates = resources.getStringArray(R.array.wallet_rates)

        val walletBalanceAmounts = resources.getStringArray(R.array.wallet_balance_amounts)

        val walletIcons = resources.obtainTypedArray(R.array.on_boarding_images)

        for (index in walletRates.indices) {

            walletItems.add(
                WalletItem(
                    walletIcons.getDrawable(index)!!,
                    walletRates[index],
                    walletBalanceAmounts[index],
                    false
                )
            )

        }

        walletItems[0].isPrimaryAccount = true

        walletAdapter = WalletAdapter()

        walletAdapter.setItems(walletItems)

        walletIcons.recycle()
    }
}