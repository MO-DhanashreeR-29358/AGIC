package com.mosl.app.coreui.views

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.ErrorState
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.app.coreui.R
import io.reactivex.subjects.PublishSubject

/**
 * Created by lalkishore on 04/02/18.
 */

class ErrorLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
  internal lateinit var imgErrorIcon: ImageView
  internal lateinit var txtErrorMessage: TextView
  internal lateinit var btnRetry: Button
  internal var sectionId: Int? = null

  var retryListener: OnClickListener? = null
  var retrySubject: PublishSubject<DataAction>? = null

  private val onClickListener = OnClickListener { v ->
    retrySubject?.onNext(retryAction) ?: retryListener?.onClick(v)
  }

  internal val retryAction: DataAction
    get() {
      return sectionId.let {
        if (it == null || it == -1) {
          DataAction.Retry
        } else {
          DataAction.RetrySection(it)
        }
      }
    }

  fun isRetryVisible(): Boolean {
    return visibility == View.VISIBLE && btnRetry.visibility == View.VISIBLE
  }

  init {
    orientation = LinearLayout.VERTICAL
    gravity = Gravity.CENTER
    val padding = context.resources.getDimensionPixelSize(R.dimen.padding_medium)
    setPadding(padding, padding, padding, padding)
    LayoutInflater.from(context).inflate(R.layout.view_error_layout, this, true)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    val rootView = rootView
    imgErrorIcon = rootView.findViewById(R.id.img_error_icon)
    txtErrorMessage = rootView.findViewById(R.id.txt_error_message)
    btnRetry = rootView.findViewById(R.id.btn_retry)
    btnRetry.setOnClickListener(onClickListener)
  }

  fun hide() {
    visibility = View.INVISIBLE
  }

  @JvmOverloads
  fun showError(
      throwable: Throwable,
      sectionId: Int? = null
  ) {
    showError(throwable.getErrorOnFailure(), sectionId)
  }

  @JvmOverloads
  fun showError(
      error: ErrorModel,
      sectionId: Int? = null
  ) {
    this.sectionId = sectionId
    showMessage(error.getResolvedMessage(context), error.reason)
  }

  fun showMessage(
      message: String,
      errorState: ErrorState
  ) {
    txtErrorMessage.text = Html.fromHtml(message)
    showIcon(errorState)
    visibility = View.VISIBLE
  }

  private fun showIcon(typeOfError: ErrorState) {
    val (iconDrawable, retryVisibility) = when (typeOfError) {
      ErrorState.NO_DATA_ERROR -> {
        R.drawable.ic_description_black_80dp to View.GONE
      }
      ErrorState.NETWORK_ERROR -> {
        R.drawable.ic_wifi to View.VISIBLE
      }
      else -> {
        R.drawable.ic_warning_black_80dp to View.VISIBLE
      }
    }
    imgErrorIcon.visibility = View.VISIBLE
    btnRetry.visibility = retryVisibility
    imgErrorIcon.setImageResource(iconDrawable)
  }
}