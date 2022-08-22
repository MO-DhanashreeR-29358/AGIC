package com.mosl.volunteer.feedback.item

import android.content.Context
import android.view.View
import android.widget.RatingBar
import com.mosl.volunteer.R
import com.mosl.volunteer.databinding.ItemFeedbackRatingBarBinding
import com.mosl.volunteer.domain.feedback.FeedbackAnswer
import com.mosl.volunteer.domain.feedback.FeedbackOptionTag
import com.mosl.volunteer.domain.feedback.FeedbackResponse
import com.mosl.volunteer.domain.feedback.RatingBarItem
import com.xwray.groupie.databinding.BindableItem


class RatingBarQuestionItem(
    val data: FeedbackResponse,
    val listener: OnRatingBarSelectedListener
) : BindableItem<ItemFeedbackRatingBarBinding>() {

    internal var context: Context? = null
    internal val viewMap = hashMapOf<FeedbackOptionTag, View>()


    override fun getLayout(): Int = R.layout.item_feedback_rating_bar

    override fun bind(
        viewBinding: ItemFeedbackRatingBarBinding,
        position: Int
    ) {
        viewBinding.apply {
            context = root.context
            renderRatingBar()
        }
    }

    internal val ratingBarChangeListner = object : RatingBar.OnRatingBarChangeListener {

        override fun onRatingChanged(
            ratingBar: RatingBar?,
            rating: Float,
            fromUser: Boolean
        ) {
            listener.onRatingBarSelected(
                answer = RatingBarItem(
                    questionId = data.questionId,
                    value = rating.toInt().toString()

                )
            )
        }


    }


    private fun ItemFeedbackRatingBarBinding.renderRatingBar() {
        if (!viewMap.containsKey(FeedbackOptionTag(data.questionId, "rating"))) {
            tvLabel.text = data.questionTitle
            val ratingBar = rb.apply {
                tag = FeedbackOptionTag(data.questionId, "rating")
                setOnRatingBarChangeListener(ratingBarChangeListner)
            }
            if (data.defaultValue.isNotEmpty()) {
                ratingBar.rating = data.defaultValue.toFloat()
            }
            viewMap[FeedbackOptionTag(data.questionId, "rating")] = ratingBar
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RatingBarQuestionItem

        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

    interface OnRatingBarSelectedListener {
        fun onRatingBarSelected(answer: FeedbackAnswer)
    }

}