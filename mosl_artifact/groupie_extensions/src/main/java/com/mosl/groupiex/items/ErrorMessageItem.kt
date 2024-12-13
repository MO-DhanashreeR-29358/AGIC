package com.mosl.groupiex.items

import android.view.View
import android.view.ViewGroup.LayoutParams
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.groupiex.R
import com.mosl.groupiex.databinding.ItemErrorMessageBinding
import com.mosl.groupiex.helper.databind.BindableItem
import io.reactivex.subjects.PublishSubject

class ErrorMessageItem @JvmOverloads constructor(
    private val model: ErrorModel,
    private val onRetryClick: View.OnClickListener,
    private val retrySubject: PublishSubject<DataAction>? = null,
    private val wrapContent: Boolean = false,
    private val id: Int? = null
) : BindableItem<ItemErrorMessageBinding>() {

  @JvmOverloads
  constructor(
      error: Throwable,
      onRetryClick: View.OnClickListener,
      retrySubject: PublishSubject<DataAction>?,
      wrapContent: Boolean = false,
      id: Int? = null
  ) : this(error.getErrorOnFailure(), onRetryClick, retrySubject, wrapContent, id)

  override fun getLayout(): Int {
    return R.layout.item_error_message
  }

  override fun bind(viewBinding: ItemErrorMessageBinding, position: Int) {
    val params = viewBinding.errorLayout.layoutParams
    params.height = if (wrapContent) LayoutParams.WRAP_CONTENT else LayoutParams.MATCH_PARENT
    viewBinding.errorLayout.retryListener = onRetryClick
    viewBinding.errorLayout.retrySubject = retrySubject
    viewBinding.errorLayout.showError(model, id)
  }

  override fun isClickable(): Boolean {
    return false
  }
}