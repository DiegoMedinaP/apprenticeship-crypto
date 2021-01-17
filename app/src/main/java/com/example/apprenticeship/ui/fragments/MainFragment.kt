package com.example.apprenticeship.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.apprenticeship.R
import com.example.apprenticeship.databinding.FragmentMainBinding
import com.example.apprenticeship.databinding.ItemCurrencyBinding
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.ui.Navegation
import com.example.apprenticeship.ui.adapters.CurrencyAdapter
import com.example.apprenticeship.ui.viewmodel.CurrencyViewModel
import com.example.apprenticeship.utils.Network
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<CurrencyViewModel>()
    private val adapter by lazy {
        CurrencyAdapter { currency ->
            onCurrencyClick(currency)
        }
    }

    private lateinit var snack :Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutNotFound.clNotFound.visibility = View.GONE


        if (!Network.isNetworkConnected) {
            snack = Snackbar.make(binding.root, "Sin conexión", Snackbar.LENGTH_INDEFINITE)
            snack.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.indianRed))
            snack.setAction("X") {
                snack.dismiss()
            }
            snack.show()
        }

        binding.rvCurrency.adapter = adapter
        viewModel.currencyListEvents.observe(viewLifecycleOwner, { event ->
            when (event) {
                is Navegation.ShowResult<*> -> {
                    binding.pbLoading.visibility = View.GONE
                    adapter.submitList(event.result as List<Currency>)
                    if (event.result.size==0)
                        binding.layoutNotFound.clNotFound.visibility = View.VISIBLE
                    else
                        binding.layoutNotFound.clNotFound.visibility = View.GONE

                }
                is Navegation.ShowNotFound -> {
                    binding.layoutNotFound.clNotFound.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                }
                is Navegation.ShowLoading -> {
                    binding.layoutNotFound.clNotFound.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }

        })

        binding.layoutNotFound.btnNewSearch.setOnClickListener {
            viewModel.fetchCurrencies()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if(::snack.isInitialized)
            snack.dismiss()
    }

    private fun onCurrencyClick(currency: Currency) {
        viewModel.setSelectedCurrency(currency)
        findNavController().navigate(R.id.action_mainFragment_to_currencyDetailFragment)
    }


}