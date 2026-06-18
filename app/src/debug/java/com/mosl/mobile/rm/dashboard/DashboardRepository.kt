package com.mosl.mobile.rm.dashboard

import com.mosl.mobile.rm.constants.NavigationConstants.Companion.NAVIGATE_TO_PORTFOLIO
import com.mosl.mobile.rm.nav.DebugConstants
import io.reactivex.Observable

interface DashboardRepository {
  fun getAll(): Observable<List<DashboardHeader>>
  fun search(query: String): Observable<List<DashboardHeader>>
}

object DashboardRepositoryImpl : DashboardRepository {
  private val headerObservable: Observable<List<DashboardHeader>> by lazy {
    getAllHeaders()
  }

  override fun getAll(): Observable<List<DashboardHeader>> {
    return headerObservable
  }

  override fun search(query: String): Observable<List<DashboardHeader>> {
    return getAll().map { dataList ->
      dataList.map { header ->
        header.copy(screens = header.screens.filter { it.title.contains(query, ignoreCase = true) })
      }
        .filter { it.screens.isNotEmpty() }
    }
  }

  fun getAllHeaders(): Observable<List<DashboardHeader>> {
    return Observable.fromCallable {
      val debugScreens = listOf(
        DashboardData(
          "Playground 1",
          DebugConstants.NAVIGATE_TO_PLAYGROUND_01
        ),
        DashboardData(
          "Playground 2",
          DebugConstants.NAVIGATE_TO_PLAYGROUND_02
        ),
        DashboardData(
          "Drawer Menu",
          DebugConstants.NAVIGATE_TO_DRAWER_MENU
        )
      )
      val debugSection = DashboardHeader("Debug Screen", debugScreens)

      val portfolioScreens= listOf(DashboardData("Demo",
        NAVIGATE_TO_PORTFOLIO))
      val portfolioSection=DashboardHeader("Demo",portfolioScreens)

      listOf(debugSection,portfolioSection)
    }
  }

}