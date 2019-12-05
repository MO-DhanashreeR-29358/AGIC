package com.mosl.mobile.data.panel

import com.mosl.data.api.AGICApi
import com.mosl.mobile.data.newsletter.NewsLetterMapper
import com.mosl.mobile.domain.panel.PanelDetailsRepository
import com.mosl.mobile.domain.panel.PanelResponse
import io.reactivex.Observable
import javax.inject.Inject

class PanelDetailsRepositoryImpl @Inject constructor(
    private val api:AGICApi
):PanelDetailsRepository {

    private val mapper = PanelDetailsMapper
    override fun panelDetailsData(): Observable<PanelResponse> {
      return  api.getPanelDetails().map {
              mapper.transformPanelDetails(it)

      }

    }
}