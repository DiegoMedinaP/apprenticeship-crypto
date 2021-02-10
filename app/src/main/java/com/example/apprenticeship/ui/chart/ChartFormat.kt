package com.example.apprenticeship.ui.chart

import com.example.apprenticeship.domain.Ohlc
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

fun lineChartSetUp(chart: LineChart) {
    
    chart.axisLeft.apply {
        setDrawGridLines(false)
        setDrawLabels(false)
        setDrawAxisLine(false)
    }
    chart.axisRight.apply {
        setDrawGridLines(false)
        setDrawLabels(false)
        setDrawAxisLine(false)
    }

    chart.xAxis.apply {
        setDrawGridLines(false)
        setDrawLabels(false)
        setDrawAxisLine(false)
    }
    chart.setDrawGridBackground(false)
    chart.setDrawBorders(false)
    chart.setDrawMarkers(false)

    chart.description.isEnabled = false
    chart.legend.isEnabled = false
}

fun lineChartSetValue(chart: LineChart, chartPoints: ArrayList<*>) {
    val entries = ArrayList<Entry>()

    for (i in 0 until chartPoints.size) {
        if (chartPoints[i] is Ohlc) {
            entries.add(Entry(i.toFloat(), (chartPoints[i] as Ohlc).currencyAverageValue.toFloat()))
        }
    }
    val set = LineDataSet(entries, "")

    set.fillAlpha = 110
    val dataSet = arrayListOf<ILineDataSet>()
    dataSet.add(set)
    set.disableDashedLine()
    set.setDrawCircles(false)
    val data = LineData(dataSet)
    chart.data = data
    chart.invalidate()
    chart.refreshDrawableState()
}