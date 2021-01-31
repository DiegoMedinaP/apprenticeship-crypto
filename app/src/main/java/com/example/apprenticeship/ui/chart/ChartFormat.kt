package com.example.apprenticeship.ui.chart

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

fun lineChartSetUp(chart: LineChart){

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