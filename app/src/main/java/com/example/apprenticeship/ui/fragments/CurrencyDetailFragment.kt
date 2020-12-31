package com.example.apprenticeship.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.apprenticeship.databinding.FragmentCurrencyDetailBinding
import com.example.apprenticeship.ui.Navegation
import com.example.apprenticeship.ui.adapters.CurrencyImageAdapter
import com.example.apprenticeship.ui.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyDetailBinding
    private val viewModel by activityViewModels<CurrencyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        viewModel.fetchTickerAndOrderBookInfo()

        viewModel.currencyEvents.observe(viewLifecycleOwner, { event ->
            when (event) {
                is Navegation.ShowResult<*> -> {
                    Log.i("Diego tag","this is actually working")
                }
                is Navegation.ShowNotFound -> {
                    Log.i("Diego tag","this is actually not working")

                }
                is Navegation.ShowLoading -> {
                    Log.i("Diego tag","this is actually loading")
                }
            }

        })
    }

    private fun setData() {
        viewModel.currency.observe(viewLifecycleOwner,{ currency ->
            binding.tvCurrencyName.text = currency.comercialName
            Glide.with(binding.root.context).load(CurrencyImageAdapter.getImage(currency.book)).centerCrop()
                    .into(binding.ivCurrency)
        })
    }


}