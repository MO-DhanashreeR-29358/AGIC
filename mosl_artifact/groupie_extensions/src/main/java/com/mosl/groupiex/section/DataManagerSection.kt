package com.mosl.groupiex.section

import android.view.View.OnClickListener
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.groupiex.helper.groupie.Group
import com.mosl.groupiex.helper.groupie.Item
import com.mosl.groupiex.helper.groupie.Section
import com.mosl.groupiex.items.ErrorMessageItem
import com.mosl.groupiex.items.InlineProgressItem
import io.reactivex.subjects.PublishSubject

class DataManagerSection @JvmOverloads constructor(
  private val retryListener: OnClickListener,
  private val progressItem: Item<*> = InlineProgressItem()
) : Section() {
  var wrapErrorView: Boolean = false
  var retrySubject: PublishSubject<DataAction>? = null
  var isDataEmpty: Boolean = true
    private set

  fun toggleProgress(inProgress: Boolean) {
    if (inProgress) {
      if (getPosition(progressItem) == -1) {
        add(0, progressItem)
      }
    } else {
      remove(progressItem)
    }
  }

  fun setContents(group: Group) {
    setContent(listOf(group))
  }

  fun setContent(groups: Collection<Group>) {
    isDataEmpty = false
    if (groups.isEmpty()) {
      showEmptyView(ErrorModel.noDataAvailable())
    } else {
      update(groups)
      removePlaceholder()
    }
  }

  fun clearContent() {
    update(emptyList())
    removePlaceholder()
  }

  fun clearErrorState() {
    removePlaceholder()
  }

  @JvmOverloads
  fun showError(
    throwable: Throwable,
    sectionId: Int = -1
  ) {
    showError(throwable.getErrorOnFailure(), sectionId)
  }

  @JvmOverloads
  fun showError(
    errorModel: ErrorModel,
    sectionId: Int = -1
  ) {
    this.isDataEmpty = true
    showEmptyView(errorModel, sectionId)
  }

  @JvmOverloads
  fun showEmptyView(errorModel: ErrorModel, sectionId: Int = -1) {
    showEmptyView(ErrorMessageItem(errorModel, retryListener, retrySubject, wrapErrorView, sectionId))
  }

  fun showEmptyView(placeHolder: Item<*>) {
    update(emptyList())
    setPlaceholder(placeHolder)
  }
}