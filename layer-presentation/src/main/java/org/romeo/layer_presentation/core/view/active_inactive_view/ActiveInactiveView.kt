package org.romeo.layer_presentation.core.view.active_inactive_view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import org.romeo.layer_presentation.R
import kotlin.math.roundToInt

class ActiveInactiveView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : MaterialCardView(context, attributeSet, defStyleAttr) {

    var activeState = false
        private set

    var listenerState: ListenerState? = null

    private var containerColor: Int = 0

    private var viewButtonColor: Int = 0
    private var viewButtonXLeft: Float = 0F
    private var viewButtonXRight: Float = 0F

    private var activeTextText = "Активные"
    private var activeTextColor = 0
    private var activeTextSize = 0
    private var activeTextPaddingTop = 0
    private var activeTextPaddingBot = 0
    private var activeTextPaddingEnd = 0

    private var inactiveTextText = "Прошедшие"
    private var inactiveTextColor = 0
    private var inactiveTextSize = 0
    private var inactiveTextPaddingTop = 0
    private var inactiveTextPaddingBot = 0
    private var inactiveTextPaddingEnd = 0

    private var width: Int? = 0
    private var height: Int? = 0

    private var animated = false

    private var animator: ValueAnimator? = null

    private val containerRectangle = RectF()
    private val viewButtonRectangle = RectF()

    private var containerPaint: Paint? = null
    private var viewButtonPaint: Paint? = null
    private var inactiveTextPaint: Paint? = null
    private var activeTextPaint: Paint? = null

    private var listener: OnClickListener? = null

    private var click = false
    private var duration: Long = 0
    private var radius: Int = 0

    init {
        initAttr(context, attributeSet)
        init()
    }

    private fun init() {
        containerPaint = Paint()
        containerPaint?.color = containerColor
        containerPaint?.style = Paint.Style.FILL

        viewButtonPaint = Paint()
        viewButtonPaint?.color = viewButtonColor
        viewButtonPaint?.style = Paint.Style.FILL
    }

    private fun initAttr(context: Context, attributeSet: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.ActiveInactiveView, 0, 0)
        containerColor = ta.getColor(
            R.styleable.ActiveInactiveView_container_color, Color.WHITE
        )
        radius = ta.getDimensionPixelSize(
            R.styleable.ActiveInactiveView_radius, 10
        )

        viewButtonColor = ta.getColor(
            R.styleable.ActiveInactiveView_buttonView_color,
            ContextCompat.getColor(context, R.color.purple_light)
        )

        activeTextText = ta.getString(
            R.styleable.ActiveInactiveView_active_text
        ) ?: "Users"
        activeTextColor = ta.getColor(
            R.styleable.ActiveInactiveView_active_text_color, Color.BLACK
        )
        activeTextSize =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_active_text_size,
                DEFAULT_TEXT_SIZE
            )
        activeTextPaddingBot =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_active_paddingBot,
                DEFAULT_TEXT_PADDING_BOT
            )
        activeTextPaddingEnd =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_active_paddingEnd,
                DEFAULT_TEXT_PADDING_END
            )
        activeTextPaddingTop =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_active_paddingTop,
                DEFAULT_TEXT_PADDING_TOP
            )

        inactiveTextText = ta.getString(R.styleable.ActiveInactiveView_inactive_text) ?: "Ads"
        inactiveTextColor =
            ta.getColor(R.styleable.ActiveInactiveView_inactive_text_color, Color.BLACK)
        inactiveTextSize =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_inactive_text_size,
                DEFAULT_TEXT_SIZE
            )
        inactiveTextPaddingBot =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_inactive_paddingBot,
                DEFAULT_TEXT_PADDING_BOT
            )
        inactiveTextPaddingEnd =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_inactive_paddingEnd,
                DEFAULT_TEXT_PADDING_START
            )
        inactiveTextPaddingTop =
            ta.getDimensionPixelSize(
                R.styleable.ActiveInactiveView_inactive_paddingTop,
                DEFAULT_TEXT_PADDING_TOP
            )

        animated = ta.getBoolean(R.styleable.ActiveInactiveView_animated, true)

        duration =
            ta.getDimensionPixelSize(R.styleable.ActiveInactiveView_duration, DEFAULT_DURATION)
                .toLong()

        ta.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        width = w - paddingStart - paddingEnd
        height = h - paddingTop - paddingBottom

        viewButtonXLeft = 0f
        viewButtonXRight = (width!! / 2).toFloat()

        containerRectangle.set(0f, 0f, width!!.toFloat(), height!!.toFloat())
        viewButtonRectangle.set(
            viewButtonXLeft,
            0f,
            viewButtonXRight,
            height!!.toFloat()
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        initInactiveTextPaint()
        initActiveTextPaint()
        initContainerRectangle(canvas)
        animatedViewButton()
        drawRectViewButton(canvas)
        initInactiveTextText(canvas)
        initActiveTextText(canvas)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        if (action == MotionEvent.ACTION_UP) {
            activeState = !activeState
            listenerState?.onStateChanged(activeState)
//              Когда буду писать вьюмодель раскомментирую
//                ?: throw RuntimeException("For the view to work correctly, you must declare listener")
            click = true
        }

        listener?.onClick(this)
        invalidate()
        return true
    }

    override fun setOnClickListener(l: OnClickListener?) {
        this.listener = l
    }

    private fun animatedViewButton() {
        if (click) {
            if (viewButtonXLeft < width!!) {
                activeTextColor = Color.WHITE
                inactiveTextColor = Color.BLACK
                animator?.cancel()
                animator = ValueAnimator.ofFloat(viewButtonXLeft, width!!.toFloat())
                viewButtonXLeft = width!!.toFloat()
                animator!!.duration = duration
                animator!!.addUpdateListener {
                    viewButtonXLeft = it.animatedValue as Float
                    invalidate()
                }
                animator!!.start()

            } else {
                activeTextColor = Color.BLACK
                inactiveTextColor = Color.WHITE
                animator?.cancel()
                animator = ValueAnimator.ofFloat(viewButtonXLeft, 0f)
                viewButtonXLeft = 0f
                animator!!.duration = duration
                animator!!.addUpdateListener {
                    viewButtonXLeft = it.animatedValue as Float
                    invalidate()
                }
                animator!!.start()
            }

        } else {
            viewButtonRectangle.set(
                viewButtonXLeft,
                0f,
                viewButtonXRight,
                height!!.toFloat()
            )
        }
        click = false
    }

    private fun initInactiveTextPaint() {
        inactiveTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        inactiveTextPaint!!.textSize = inactiveTextSize.toFloat()
        inactiveTextPaint!!.color = inactiveTextColor
        inactiveTextPaint!!.typeface = Typeface.createFromAsset(context.assets, "roboto_medium.ttf")
        inactiveTextPaint!!.textAlign = Paint.Align.RIGHT
    }

    private fun initActiveTextPaint() {
        activeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        activeTextPaint!!.textSize = activeTextSize.toFloat()
        activeTextPaint!!.color = activeTextColor
        activeTextPaint!!.typeface = Typeface.createFromAsset(context.assets, "roboto_medium.ttf")
        activeTextPaint!!.textAlign = Paint.Align.RIGHT
    }

    private fun initContainerRectangle(canvas: Canvas?) {
        canvas?.drawRoundRect(
            containerRectangle,
            radius.toFloat(),
            radius.toFloat(),
            containerPaint!!
        )
    }

    private fun drawRectViewButton(canvas: Canvas?) {
        canvas?.drawRoundRect(
            viewButtonRectangle,
            radius.toFloat(),
            radius.toFloat(),
            viewButtonPaint!!
        )
    }

    private fun initActiveTextText(canvas: Canvas?) {
        canvas?.drawText(
            activeTextText,
            (width!! - activeTextPaddingEnd).toFloat(),
            (height!! - activeTextPaddingBot).toFloat(),
            activeTextPaint!!
        )
    }

    private fun initInactiveTextText(canvas: Canvas?) {
        canvas?.drawText(
            inactiveTextText,
            (width!! / 2 - inactiveTextPaddingEnd).toFloat(),
            (height!! - (inactiveTextPaddingBot)).toFloat(),
            inactiveTextPaint!!
        )
    }

    private fun dpToPxX(dp: Int): Float {
        val dm = context.resources.displayMetrics
        return (dp * (dm.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt().toFloat()
    }

    private fun dpToPxY(dp: Int): Float {
        val dm = context.resources.displayMetrics
        return (dp * (dm.ydpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt().toFloat()
    }

    companion object {
        private const val DEFAULT_TEXT_SIZE = 20
        private const val DEFAULT_TEXT_PADDING_BOT = 11
        private const val DEFAULT_TEXT_PADDING_END = 39
        private const val DEFAULT_TEXT_PADDING_TOP = 10
        private const val DEFAULT_TEXT_PADDING_START = 43
        private const val DEFAULT_DURATION = 150
    }
}