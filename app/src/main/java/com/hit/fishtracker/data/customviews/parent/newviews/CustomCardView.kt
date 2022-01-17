package com.hit.fishtracker.data.customviews.parent.newviews
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.hit.fishtracker.data.customviews.parent.CustomView
import com.hit.fishtracker.databinding.FishItemBinding

class CustomCardView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
)
: CustomView<FishItemBinding>(context!!, attrs, defStyle) {
    override fun inflate(): FishItemBinding = FishItemBinding.inflate(LayoutInflater.from(context),this,true)


}