package com.muhammadzubair.koranger

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.Canvas

class NativeRangeBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var minValue: Int = 0
    private var maxValue: Int = 100
    private var activeColor: Int = Color.BLUE
    private var inactiveColor: Int = Color.GRAY
    private var thumbRadius: Float = 20f
    private var barThickness: Float = 8f
    private var orientation: Int = HORIZONTAL
    private var progress: Float = 0f

    private var rangeBarChangeListener: OnRangeBarChangeListener? = null


    fun setOnRangeBarChangeListener(listener: OnRangeBarChangeListener) {
        this.rangeBarChangeListener = listener
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RangeBarView,
            0, 0
        ).apply {
            try {
                minValue = getInt(R.styleable.RangeBarView_minValue, 0)
                maxValue = getInt(R.styleable.RangeBarView_maxValue, 100)
                activeColor = getColor(R.styleable.RangeBarView_activeColor, Color.BLUE)
                inactiveColor = getColor(R.styleable.RangeBarView_inactiveColor, Color.GRAY)
                thumbRadius = getDimension(R.styleable.RangeBarView_thumbRadius, 20f)
                barThickness = getDimension(R.styleable.RangeBarView_barThickness, 8f)
                orientation = getInt(R.styleable.RangeBarView_orientation, HORIZONTAL)
            } finally {
                recycle()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                progress = if (orientation == HORIZONTAL) {
                    event.x / width
                } else {
                    1f - (event.y / height)
                }.coerceIn(0f, 1f)

                // Calculate the current value based on progress
                val currentValue = (minValue + progress * (maxValue - minValue)).toInt()

                // Notify the listener about the value change
                rangeBarChangeListener?.onValueChanged(currentValue)

                invalidate()  // Redraw the view
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {

    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }
}
