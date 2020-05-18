package com.android.findcardgame.adapter

import android.animation.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.findcardgame.R
import kotlinx.android.synthetic.main.card_front.view.*
import kotlinx.android.synthetic.main.card_item.view.*
import java.lang.ref.WeakReference


class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

    private var cardNumber: String? = null
    private var callbackWeakRef: WeakReference<RecyclerViewAdapter.Listener>? = null
    private var mIsBackVisible: Boolean = false
    private var mSetRightOut: AnimatorSet? = null
    private var mSetLeftIn: AnimatorSet? = null

    fun updateCardItem(
        textNumber: String,
        callback: RecyclerViewAdapter.Listener
    ) {
        callbackWeakRef = WeakReference(callback)
        itemView.txt_front.text = textNumber
        this.cardNumber = textNumber
        itemView.setOnClickListener { onClick(it) }
    }

    override fun onClick(view: View) {
        val callback = callbackWeakRef!!.get()
        callback?.onClickItem(adapterPosition)
//        fader(view)
        flipCard(view)
    }

    fun flipCard(view: View?) {
        changeCameraDistance(itemView)
        loadAnimations(itemView)
        if (!mIsBackVisible) {
            mSetRightOut?.setTarget(view?.card_front)
            mSetLeftIn?.setTarget(view?.card_back)
            mSetRightOut?.start()
            mSetLeftIn?.start()
            mIsBackVisible = true
        } else {
            mSetRightOut?.setTarget(view?.card_back)
            mSetLeftIn?.setTarget(view?.card_front)
            mSetRightOut?.start()
            mSetLeftIn?.start()
            mIsBackVisible = false
        }
    }

    private fun changeCameraDistance(itemView: View?) {
//        itemView!!.root.visibility = View.INVISIBLE
        val distance = 5000
        val scale: Float = itemView!!.root.context.resources.displayMetrics.density * distance
        itemView.root.cameraDistance = scale
        itemView.root.cameraDistance = scale
    }

    private fun loadAnimations(itemView: View?) {
        mSetRightOut = AnimatorInflater.loadAnimator(itemView!!.root.context, R.animator.out_animation) as AnimatorSet
        mSetLeftIn = AnimatorInflater.loadAnimator(itemView.root.context, R.animator.in_animation) as AnimatorSet
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
