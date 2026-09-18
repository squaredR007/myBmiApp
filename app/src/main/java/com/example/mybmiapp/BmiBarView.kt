package com.example.mybmiapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class BmiBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var bmi: Float = 22f

    // BMI range this bar visually covers
    private val minBmi = 15f
    private val maxBmi = 35f

    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val markerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2C2C2A")
        strokeWidth = 6f
        strokeCap = Paint.Cap.ROUND
    }

    fun setBmi(value: Float) {
        bmi = value
        invalidate() // tells Android "redraw this view, its data changed"
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val trackHeight = 28f
        val top = (height - trackHeight) / 2
        val bottom = top + trackHeight

        // Gradient across the four BMI zones
        trackPaint.shader = LinearGradient(
            0f, 0f, width.toFloat(), 0f,
            intArrayOf(
                Color.parseColor("#85B7EB"), // underweight
                Color.parseColor("#97C459"), // normal
                Color.parseColor("#FAC775"), // overweight
                Color.parseColor("#F09595")  // obese
            ),
            floatArrayOf(0f, 0.35f, 0.65f, 1f),
            Shader.TileMode.CLAMP
        )

        canvas.drawRoundRect(0f, top, width.toFloat(), bottom, trackHeight / 2, trackHeight / 2, trackPaint)

        // Marker position based on where bmi falls in [minBmi, maxBmi]
        val clampedBmi = bmi.coerceIn(minBmi, maxBmi)
        val fraction = (clampedBmi - minBmi) / (maxBmi - minBmi)
        val markerX = fraction * width

        canvas.drawLine(markerX, top - 10f, markerX, bottom + 10f, markerPaint)
    }
}