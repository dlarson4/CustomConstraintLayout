package com.example.customconstraintlayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.annotation.ColorRes
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import android.graphics.PorterDuff
import android.view.ViewGroup
import android.widget.TextView


class MyCustomItemLayout : ConstraintLayout {
    companion object {
        private val TAG = MyCustomItemLayout::class.java.simpleName
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrSet: AttributeSet?) : this(context, attrSet, 0)

    constructor(context: Context, attrSet: AttributeSet?, defStyleAttr: Int) : super(context, attrSet, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrSet, R.styleable.MyCustomItemLayout, defStyleAttr, 0)

        val itemText = typedArray.getString(R.styleable.MyCustomItemLayout_itemText)
        val itemLogoDrawable: Drawable? = typedArray.getDrawable(R.styleable.MyCustomItemLayout_itemLogo)
        @ColorRes val colorRes = typedArray.getResourceId(R.styleable.MyCustomItemLayout_imageColor, 0)
        val imageColor = ContextCompat.getColor(context, colorRes)
        typedArray.recycle()

        val view = LayoutInflater.from(context).inflate(R.layout.custom_item, null)

        if (itemLogoDrawable != null) {
            val mutatedLogoDrawable = itemLogoDrawable.mutate()
            mutatedLogoDrawable.setColorFilter(imageColor, PorterDuff.Mode.SRC_IN)
            view.findViewById<ImageView>(R.id.itemIcon).setImageDrawable(itemLogoDrawable)
        }

        view.findViewById<TextView>(R.id.textItem).text = itemText

        addView(view, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
    }

}
