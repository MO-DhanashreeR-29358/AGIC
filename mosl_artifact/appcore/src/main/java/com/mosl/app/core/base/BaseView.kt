package com.mosl.app.core.base

import com.mosl.app.core.events.DataAction
import io.reactivex.Observable

/**
 * Created by apple on 17/03/18.
 */
interface BaseView {
  fun onDataAction(): Observable<DataAction>
  fun isDataEmpty(): Boolean
}