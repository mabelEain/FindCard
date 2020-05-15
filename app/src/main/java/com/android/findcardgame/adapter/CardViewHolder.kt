package com.android.findcardgame.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import java.lang.ref.WeakReference


class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

    private var cardNumber: Int? =0
    private var callbackWeakRef: WeakReference<RecyclerViewAdapter.Listener>? = null

    fun updateCardItem(
        textNumber: Int,
        callback: RecyclerViewAdapter.Listener
    ) {
        callbackWeakRef = WeakReference(callback)
        itemView.card_number.text = textNumber.toString()
        this.cardNumber = textNumber
        itemView.card_number.setOnClickListener { onClick(it) }
    }

    override fun onClick(view: View) {
        val callback = callbackWeakRef!!.get()
        callback?.onClickItem(adapterPosition)
        fader(view)
    }

    private fun fader(view: View) {

        // Fade the view out to completely transparent and then back to completely opaque

        val animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(view)
        animator.start()
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {

        // This extension method listens for start/end events on an animation and disables
        // the given view for the entirety of that animation.

        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }
}
