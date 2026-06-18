package com.mosl.mobile.rm.feedback.item

import android.content.Context
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import com.mosl.mobile.rm.R
import com.mosl.mobile.rm.databinding.ItemFeedbackCheckboxBinding
import com.mosl.mobile.domain.feedback.*
import com.mosl.groupiex.helper.databind.BindableItem
import timber.log.Timber

class CheckBoxQuestionItem(
    val data: FeedbackResponse,
    val listener: OnCheckBoxSelectedListener
) : BindableItem<ItemFeedbackCheckboxBinding>() {

    internal var context: Context? = null
    internal val questionAnswerMap = mutableMapOf<String, CheckBoxOptionTag>()
    internal val selectedOption: MutableList<OptionsResponse> = mutableListOf()
    override fun getLayout(): Int = R.layout.item_feedback_checkbox

    override fun bind(viewBinding: ItemFeedbackCheckboxBinding, position: Int) {

        viewBinding.apply {
            context = root.context
            tvQuestion.text = data.questionTitle
            renderCheckBox(data.options)
        }
    }

    private fun ItemFeedbackCheckboxBinding.renderCheckBox(options: List<OptionsResponse>) {

        llVertical.removeAllViews()
        var answeredList: MutableList<CheckBoxAnswered> = mutableListOf()
        val isAnsweredList = questionAnswerMap[data.questionId]?.answeredList?.isNotEmpty() ?: false
        if (isAnsweredList) {
            answeredList = questionAnswerMap[data.questionId]!!.answeredList
        }

        for (option in options) {
            val checkBox = CheckBox(context).apply {
                tag = FeedbackOptionTag(option.optionId, "options")
                text = option.optionName
                setPadding(8, 8, 8, 8)
                setOnClickListener { view ->
                    val checkBox = view as CheckBox
                    checkBox.callListener(checkBox.isChecked)
                    answeredList.add(CheckBoxAnswered(option.optionId, checkBox.isChecked))
                }

            }

            if (data.questionId.equals(questionAnswerMap[data.questionId]?.questionId) && isAnsweredList) {
                for (checkBoxAnswered in questionAnswerMap[data.questionId]!!.answeredList) {
                    if (option.optionId == checkBoxAnswered.optionId) {
                        checkBox.isChecked = checkBoxAnswered.selected
                    }
                }
            }
            checkBox.isChecked = option.selected

            llVertical.addView(checkBox)

        }

        questionAnswerMap[data.questionId] = CheckBoxOptionTag(data.questionId, answeredList)


    }

    private fun CheckBox.callListener(isChecked: Boolean) {
        val checkBoxOption = tag as FeedbackOptionTag
        Timber.d(tag.toString())
        if (isChecked) {
            selectedOption.add(OptionsResponse(checkBoxOption.questionId, selected = isChecked))
        } else {
            selectedOption.remove(OptionsResponse(checkBoxOption.questionId, selected = isChecked))
        }
        checkListData(selectedOption)
    }

    fun checkListData(selectedOption: List<OptionsResponse>) {
        listener.onCheckBoxSelected(
            answer = CheckBoxSelected(
                questionId = data.questionId,
                value = "",
                options = selectedOption
            )
        )

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CheckBoxQuestionItem

        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

    interface OnCheckBoxSelectedListener {
        fun onCheckBoxSelected(answer: FeedbackAnswer)
    }
}