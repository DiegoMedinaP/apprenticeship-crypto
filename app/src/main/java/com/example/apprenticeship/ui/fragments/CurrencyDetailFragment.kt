package com.example.apprenticeship.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apprenticeship.R
import com.example.apprenticeship.databinding.FragmentCurrencyDetailBinding
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.adapters.CurrencyImageAdapter
import com.example.apprenticeship.ui.viewmodel.CurrencyDetailViewModel
import com.example.apprenticeship.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyDetailBinding
    private val viewModel by viewModels<CurrencyDetailViewModel>()
    private val args : CurrencyDetailFragmentArgs by navArgs()
    private val currency:Currency
        get() {
            return args.currency
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

        viewModel.currencyEvents.observe(viewLifecycleOwner, { event ->
            when (event) {
                is CurrencyDetailViewModel.CurrencyNavegation.ShowCurrencies -> {

                }
                is CurrencyDetailViewModel.CurrencyNavegation.ShowNotFound -> {

                }
                CurrencyDetailViewModel.CurrencyNavegation.ShowLoading -> {

                }
            }

        })
    }

    private fun setData() {
        binding.tvCurrencyName.text = currency.comercialName
        Glide.with(binding.root.context).load(CurrencyImageAdapter.getImage(currency.book)).centerCrop()
            .into(binding.ivCurrency)
    }


}