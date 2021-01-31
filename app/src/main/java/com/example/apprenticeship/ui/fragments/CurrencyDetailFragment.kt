package com.example.apprenticeship.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.apprenticeship.R
import com.example.apprenticeship.databinding.FragmentCurrencyDetailBinding
import com.example.apprenticeship.domain.Currency
import com.example.apprenticeship.domain.Ohlc
import com.example.apprenticeship.ui.Navegation
import com.example.apprenticeship.ui.adapters.AskBidAdapter
import com.example.apprenticeship.ui.adapters.CurrencyImageAdapter
import com.example.apprenticeship.ui.chart.lineChartSetUp
import com.example.apprenticeship.ui.viewmodel.CurrencyViewModel
import com.example.apprenticeship.utils.Network
import com.example.apprenticeship.utils.toDateString
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailFragment : Fragment() {

    private var _binding: FragmentCurrencyDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<CurrencyViewModel>()
    private val askAdapter by lazy { AskBidAdapter() }
    private val bidAdapter by lazy { AskBidAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineChartSetUp(binding.chartCurrency)
        binding.layoutNotFound.clNotFound.visibility = View.VISIBLE
        currencyObserver()
        chartObserver()


        binding.layoutNotFound.btnNewSearch.setOnClickListener {
            viewModel.fetchTickerAndOrderBookInfo()
        }
    }

    private fun chartObserver() {
        viewModel.currencyChartPointsEvents.observe(viewLifecycleOwner,{
                event ->
            when (event) {
                is Navegation.ShowResult<*> -> {
                    binding.chartCurrency.visibility = View.VISIBLE
                    val entries = ArrayList<Entry>()
                    val chartPoints = event.result
                    if(chartPoints is ArrayList<*>){
                        for(i in 0 until chartPoints.size){
                            if(chartPoints[i] is Ohlc){
                                entries.add(Entry(i.toFloat(),(chartPoints[i] as Ohlc).currencyAverageValue.toFloat()))
                            }
                        }
                        val set = LineDataSet(entries,"")

                        set.fillAlpha = 110
                        val dataSet = arrayListOf<ILineDataSet>()
                        dataSet.add(set)
                        set.disableDashedLine()
                        set.setDrawCircles(false)
                        val data = LineData(dataSet)
                        binding.chartCurrency.data = data
                        binding.chartCurrency.invalidate()
                        binding.chartCurrency.refreshDrawableState()

                    }

                }
                is Navegation.ShowNotFound -> {
                    binding.chartCurrency.visibility = View.GONE
                }
                else -> {}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun currencyObserver(){
        viewModel.currencyDetailsEvents.observe(viewLifecycleOwner, { event ->
            when (event) {
                is Navegation.ShowResult<*> -> {
                    binding.clCurrencyDetail.visibility = View.VISIBLE
                    binding.layoutNotFound.clNotFound.visibility = View.GONE
                    binding.pbLoading.visibility = View.GONE
                    setCurrencyInfo(event.result as Currency)
                }
                is Navegation.ShowNotFound -> {
                    binding.clCurrencyDetail.visibility = View.GONE
                    binding.layoutNotFound.clNotFound.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                }
                is Navegation.ShowLoading -> {
                    binding.clCurrencyDetail.visibility = View.GONE
                    binding.layoutNotFound.clNotFound.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setCurrencyInfo(currency: Currency) {
        binding.tvCurrencyName.text = currency.comercialName
        Glide.with(binding.root.context).load(CurrencyImageAdapter.getImage(currency.book))
            .centerCrop()
            .into(binding.ivCurrency)
        binding.rvAskCurrency.adapter = askAdapter
        binding.rvBidCurrency.adapter = bidAdapter

        var offlineText = ""

        if (!Network.isNetworkConnected) {
            offlineText = "Offline: "
            binding.tvLastUpdate.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.indianRed
                )
            )
        }

        binding.tvLastUpdate.text = String.format("$offlineText ${String().toDateString(currency.ticker?.created_at ?: "")}")
        binding.tvCurrencyHighValue.text = String.format("${currency.ticker?.high} mxn")
        binding.tvCurrencyLastValue.text = String.format("${currency.ticker?.last} mxn")
        binding.tvCurrencyLowValue.text = String.format("${currency.ticker?.low} mxn")

        askAdapter.submitList(currency.orderBook?.asks)
        bidAdapter.submitList(currency.orderBook?.bids)
    }

}
