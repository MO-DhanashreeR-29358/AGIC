package com.mosl.mobile.feedback.item

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFeedbackEdittextBinding
import com.mosl.mobile.domain.feedback.FeedbackAnswer
import com.mosl.mobile.domain.feedback.FeedbackOptionTag
import com.mosl.mobile.domain.feedback.FeedbackResponse
import com.mosl.mobile.domain.feedback.TextInputItem
import com.xwray.groupie.databinding.BindableItem
import timber.log.Timber

class TextQuestionItem(
    val data: FeedbackResponse,
    val listener: OnTextEnteredListener
) : BindableItem<ItemFeedbackEdittextBinding>() {

    internal var context: Context? = null
    internal val viewMap = hashMapOf<FeedbackOptionTag, View>()

    internal val watcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {

        }

        override fun afterTextChanged(s: Editable?) {
            val entertedText = s?.toString()

            entertedText?.let { text ->
                Timber.d(text)
                listener.onTextEntered(
                    answer = TextInputItem(
                        questionId = data.questionId,
                        value = text

                    )
                )
            }

        }
    }

    override fun getLayout(): Int = R.layout.item_feedback_edittext

    override fun bind(
        viewBinding: ItemFeedbackEdittextBinding,
        position: Int
    ) {
        viewBinding.apply {
            context = root.context
            renderEditText()
        }
    }

    private fun ItemFeedbackEdittextBinding.renderEditText() {
        if (!viewMap.containsKey(FeedbackOptionTag(data.questionId, "input"))) {
            tvLabel.text = data.questionTitle
            val editText = etNotes.apply {
                tag = FeedbackOptionTag(data.questionId, "input")
                addTextChangedListener(watcher)
            }
            viewMap[FeedbackOptionTag(data.questionId, "input")] = editText
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextQuestionItem

        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

    interface OnTextEnteredListener {
        fun onTextEntered(answer: FeedbackAnswer)
    }

}