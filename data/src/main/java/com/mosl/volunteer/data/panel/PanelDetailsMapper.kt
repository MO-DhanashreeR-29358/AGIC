package com.mosl.volunteer.data.panel

import com.mosl.data.api.panel.PanelDataResponse
import com.mosl.data.api.panel.PanelDayModelResponse
import com.mosl.volunteer.domain.panel.PanelDayResponse
import com.mosl.volunteer.domain.panel.PanelResponse

object PanelDetailsMapper {

    fun transformPanelDetails(panelDataResponse: PanelDataResponse): PanelResponse {


        return PanelResponse(
            day1 = panelDataResponse.dayModel1.transformPanelDayResponse(),
            day2 = panelDataResponse.dayModel2.transformPanelDayResponse(),
            day3 = panelDataResponse.dayModel3.transformPanelDayResponse()

        )
    }

    fun List<PanelDayModelResponse>.transformPanelDayResponse(): List<PanelDayResponse> {
        return map{
            PanelDayResponse(
            day=it.day,
                cRepresentiveName=it.cRepresentiveName,
            designation=it.designation,
            representativeStarttime=it.representativeStarttime,
            representativeEndtime=it.representativeEndtime,
            panelImagePath=it.panelImagePath,
            speakerIntroduction=it.speakerIntroduction
        )}
    }
}