package com.mosl.app.coreui.base

import com.mosl.app.core.network.NetworkChangeEvent
import com.mosl.navigator.NavigationElement

/**
 * Created by lalkishore on 25-10-2017.
 */

interface NavigationElement : NavigationElement {
  fun networkChanged(info: NetworkChangeEvent)
}
