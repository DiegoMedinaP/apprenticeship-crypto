package com.example.apprenticeship.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apprenticeship.databinding.FragmentMainBinding
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.adapters.CurrencyAdapter
import com.example.apprenticeship.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() , CurrencyAdapter.OnCurrencyClickListener {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy {
        CurrencyAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCurrency.adapter = adapter
        viewModel.currencyEvents.observe(viewLifecycleOwner, { event ->
            when (event) {
                is MainViewModel.CurrencyNavegation.ShowCurrencies -> {
                    binding.pbLoading.visibility = View.GONE
                    adapter.submitList(event.currencies)
                }
                is MainViewModel.CurrencyNavegation.ShowNotFound -> {
                    binding.pbLoading.visibility = View.GONE
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                }
                MainViewModel.CurrencyNavegation.ShowLoading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }

        })
    }

    override fun onCurrencyClick(currency: Currency, position: Int) {
        val action = MainFragmentDirections.actionMainFragmentToCurrencyDetailFragment(currency)
        findNavController().navigate(action)
    }

}