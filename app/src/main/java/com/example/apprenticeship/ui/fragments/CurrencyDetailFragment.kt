package com.example.apprenticeship.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.apprenticeship.databinding.FragmentCurrencyDetailBinding
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.Navegation
import com.example.apprenticeship.ui.adapters.AskBidAdapter
import com.example.apprenticeship.ui.adapters.CurrencyImageAdapter
import com.example.apprenticeship.ui.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailFragment : Fragment() {

    private var _binding: FragmentCurrencyDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<CurrencyViewModel>()
    private val askAdapter by lazy { AskBidAdapter() }
    private val bidAdapter by lazy { AskBidAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

        viewModel.currencyEvents.observe(viewLifecycleOwner, { event ->
            when (event) {
                is Navegation.ShowResult<*> -> {
                    val cur = event.result as Currency
                    binding.tvLastUpdate.text = cur.ticker?.created_at?.substringBefore("T")
                    binding.tvCurrencyHighValue.text = String.format("${cur.ticker?.high} mxn")
                    binding.tvCurrencyLastValue.text = String.format("${cur.ticker?.last} mxn")
                    binding.tvCurrencyLowValue.text = String.format("${cur.ticker?.low} mxn")

                    askAdapter.submitList(cur.orderBook?.ask)
                    bidAdapter.submitList(cur.orderBook?.bid)
                }
                is Navegation.ShowNotFound -> {
                    Log.i("Diego tag", "this is actually not working")

                }
                is Navegation.ShowLoading -> {
                    Log.i("Diego tag", "this is actually loading")
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setData() {
        viewModel.currency.observe(viewLifecycleOwner, { currency ->
            binding.tvCurrencyName.text = currency.comercialName
            Glide.with(binding.root.context).load(CurrencyImageAdapter.getImage(currency.book))
                .centerCrop()
                .into(binding.ivCurrency)
        })
        binding.rvAskCurrency.adapter = askAdapter
        binding.rvBidCurrency.adapter = bidAdapter
    }


}