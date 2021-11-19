package com.app.cryptowallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.app.cryptowallet.databinding.Fragment2Binding

class Fragment2 : Fragment() {

    private lateinit var walletTransactionAdapter: WalletTransactionAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: Fragment2Binding
    private lateinit var onBoardingIndicator: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment2, container, false)

        viewPager = binding.walletViewPager

        with(viewPager) {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
        }

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
        viewPager.setPageTransformer { page, position ->
            val viewPager = page.parent.parent as ViewPager2
            val offset = position * -(2 * offsetPx + pageMarginPx)
            if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -offset
                } else {
                    page.translationX = offset
                }
            } else {
                page.translationY = offset
            }
        }

        onBoardingIndicator = binding.transactionIndicator

        setUpOnBoardingItems()

        viewPager.adapter = walletTransactionAdapter

        setUpOnBoardingIndicators()

        setCurrentOnBoardingItemIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnBoardingItemIndicator(position)
            }

        })


        return binding.root
    }

    private fun setUpOnBoardingItems() {
        val walletTransactionItems = mutableListOf<WalletTransactionDetails>()

        val transactionAmounts = resources.getStringArray(R.array.transaction_amounts)

        val accountBalances = resources.getStringArray(R.array.wallet_balance)

        val accountNames = resources.getStringArray(R.array.account_names)

        val transactionDates = resources.getStringArray(R.array.transaction_dates)

        val transactionReceivers = resources.getStringArray(R.array.transaction_receivers)

        val walletTransactionIcons = resources.obtainTypedArray(R.array.transaction_icons)

        for (index in transactionAmounts.indices) {

            walletTransactionItems.add(
                WalletTransactionDetails(
                    walletTransactionIcons.getDrawable(index)!!,
                    transactionDates[index],
                    transactionReceivers[index],
                    transactionAmounts[index],
                    accountNames[index],
                    accountBalances[index]
                )
            )
        }


        walletTransactionAdapter = WalletTransactionAdapter()

        walletTransactionAdapter.setItems(walletTransactionItems)
    }

    private fun setUpOnBoardingIndicators() {
        val indicators: Array<ImageView> = Array(walletTransactionAdapter.itemCount) {
            ImageView(requireContext())
        }

        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParams.setMargins(8, 0, 8, 0)

        for (index in indicators.indices) {
            indicators[index].setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.onboarding_indicator_inactive
                )
            )

            indicators[index].layoutParams = layoutParams

            onBoardingIndicator.addView(indicators[index])

        }

    }

    private fun setCurrentOnBoardingItemIndicator(index: Int) {
        val childCount: Int = onBoardingIndicator.childCount

        for (i in 0 until childCount) {
            val imageView = onBoardingIndicator.getChildAt(i) as ImageView

            if (i == index) {

                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.onboarding_indicator_active
                    )
                )

            } else {

                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.onboarding_indicator_inactive
                    )
                )

            }
        }
    }
}