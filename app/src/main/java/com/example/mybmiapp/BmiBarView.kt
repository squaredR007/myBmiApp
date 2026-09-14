package com.example.mybmiapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.resourceinspection.annotation.Attribute

class BmiBarView @JvmOverloads constructor(
    context : Context , attrs : AttributeSet? = null
) : View(context , attrs) {
    private var bmi : Float = 22f

    private val minBmi = 15f
    private val maxBmi = 35f

    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val markerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color  = Color.parseColor("#2C2C2A")
        strokeWidth = 6f
        strokeCap = Paint.Cap.ROUND
    }

    fun setBmi (value : Float) {
        bmi = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val trackHeight = 28f
        val top = (height - trackHeight) / 2
        val bottom = top + trackHeight

        trackPaint.shader = LinearGradient (0f , 0f , width.toFloat() , 0f , intArrayOf(
            Color.parseColor("#85B7EB"),
            Color.parseColor("#97C459"),
            Color.parseColor("#FAC775"),
            Color.parseColor("#F09595")
        ) , floatArrayOf(0f , 0.35f , 0.65f , 1f) , Shader.TileMode.CLAMP
        )
        canvas.drawRoundRect(0f , top , width.toFloat() , bottom , trackHeight / 2 , trackHeight / 2 , trackPaint)

        val clampedBmi = bmi.coerceIn(minBmi , maxBmi)
        val fraction = (clampedBmi - minBmi) / (maxBmi - minBmi)
        val markerX = fraction * width

        canvas.drawLine(markerX , top - 10f , markerX , bottom + 10f , markerPaint)
    }
}