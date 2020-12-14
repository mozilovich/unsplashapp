package com.app.unsplashapp.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.app.unsplashapp.presentation.extensions.dpToPx

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var radius: Float = context.dpToPx(50)
        set(value) {
            field = value
            requestLayout()
        }

    var innerRadius: Float = context.dpToPx(20)
        set(value) {
            field = value
            requestLayout()
        }

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val innerCirclePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private val dividerPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.WHITE
        strokeWidth = context.dpToPx(2)
    }

    private val pieChartRect = RectF(0f, 0f, radius * 2, radius * 2)

    private val colors = listOf(
        Color.BLACK,
        Color.BLUE,
        Color.CYAN,
        Color.GRAY,
        Color.GREEN,
        Color.MAGENTA,
        Color.RED
    )

    var values = listOf(30f, 30f, 30f)

    private val charts by lazy {
        var angle = 0f
        val charts = ArrayList<ChartModel>()
        val localColors = ArrayList<Int>().apply {
            addAll(colors)
        }
        values.forEach {
            val value = it / values.sum() * CIRCLE_RADIUS
            charts.add(
                ChartModel(
                    value,
                    angle,
                    localColors.random().also { color ->
                        localColors.remove(color)
                    }
                )
            )
            angle += value
        }
        charts
    }

    private var width = 0f
    private var height = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = measuredWidth.toFloat()
        height = measuredHeight.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawPieChart(canvas)
    }

    private fun drawPieChart(canvas: Canvas?) {
        charts.forEach {
            paint.color = it.color
            canvas?.drawArc(pieChartRect, it.angle, it.value, true, paint)
        }
        canvas?.drawCircle(width / 2, height / 2, innerRadius, innerCirclePaint)
    }

    companion object {
        private const val CIRCLE_RADIUS = 360f
    }
}

data class ChartModel(
    val value: Float,
    val angle: Float,
    val color: Int
)