package com.mosl.mobile.data.corporateschedulepager

import com.mosl.data.api.AGICApi
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseData
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseDayAttendanceItem
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseDayItem
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseRepresentativeItem
import com.mosl.mobile.domain.corporateschedulepager.*
import io.reactivex.Observable
import javax.inject.Inject

class CorporateSchedulePagerRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : CorporateScheduleRepository {

    var overViewDayWiseListData = mutableListOf<List<CorporateScheduleResponseDayItemInfo>>()
    private val mapper = CorporateSchedulePagerMapper


    override fun getCorporateScheduleRepository(overviewPagerInfo: CorporateScheduleRequestInfo): Observable<CorporateScheduleResponseDataInfo> {
//        return agicApi.getCorporateScheduleAPI(mapper.getCorporateSchedulePagerRequestMap(overviewPagerInfo))
        return sampleData()
            .map {
                mapper.getScheduleMap(it)
            }
    }

    fun sampleData(): Observable<CorporateScheduleResponseData> {

        val obj0GOOD = CorporateScheduleResponseRepresentativeItemInfo(
            name = "Sajid",
            designation = "Android Developer"
        )

        val obj0 = CorporateScheduleResponseRepresentativeItemInfo(
            name = "Android",
            designation = "Android Developer"
        )

        val obj1 = CorporateScheduleResponseRepresentativeItemInfo(
            name = "Java",
            designation = "Java Developer"
        )

        val obj2 = CorporateScheduleResponseRepresentativeItemInfo(
            name = "PHP",
            designation = "PHP Developer"
        )

        var representineItem: List<CorporateScheduleResponseRepresentativeItemInfo> = emptyList()
        representineItem = representineItem + obj0GOOD

        var representineItem2: List<CorporateScheduleResponseRepresentativeItemInfo> = emptyList()
        representineItem2 += obj0
        representineItem2 += obj1
        representineItem2 += obj2


        val attendItem = CorporateScheduleResponseDayAttendanceItemInfo(
            FundName = "ICL FIND",
            representative = representineItem
        )

        val obj102aa = CorporateScheduleResponseDayAttendanceItemInfo(
            FundName = "Good",
            representative = representineItem2
        )

        var sa2: List<CorporateScheduleResponseDayAttendanceItemInfo> = emptyList()
        sa2 += obj102aa
        sa2 += obj102aa

        var sa3: List<CorporateScheduleResponseDayAttendanceItemInfo> = emptyList()
        sa3 += attendItem

        val objCorporateScheduleRequestInfo1 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T09:00:00",
            roomNumber = "122",
            Attendees = sa2,
            natureOfMeeting = "1-1",
            day = "day1"
        )

        val objCorporateScheduleRequestInfo6 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T19:00:00",
            roomNumber = "122",
            Attendees = sa2,
            natureOfMeeting = "2-1",
            day = "day3"
        )


        val objCorporateScheduleRequestInfo2 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T10:00:00",
            roomNumber = "533",
            Attendees = sa2,
            natureOfMeeting = "3-1",
            day = "day1"
        )

        val objCorporateScheduleRequestInfo3 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T12:00:00",
            roomNumber = "120",
            Attendees = sa2,
            natureOfMeeting = "4-1",
            day = "day1"
        )

        val objCorporateScheduleRequestInfo5 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T13:00:00",
            roomNumber = "463",
            Attendees = sa3,
            natureOfMeeting = "Small Group",
            day = "day2"
        )

        var sa13: List<CorporateScheduleResponseDayItemInfo> = emptyList()
        var sa23: List<CorporateScheduleResponseDayItemInfo> = emptyList()
        var sa33: List<CorporateScheduleResponseDayItemInfo> = emptyList()

        sa13 += objCorporateScheduleRequestInfo1
        sa13 += objCorporateScheduleRequestInfo2
        sa13 += objCorporateScheduleRequestInfo3

        sa23 += objCorporateScheduleRequestInfo5

        sa33 += objCorporateScheduleRequestInfo6

        overViewDayWiseListData.add(0, sa13)
        overViewDayWiseListData.add(1, sa23)
        overViewDayWiseListData.add(2, sa33)

        var dataItemInfo: CorporateScheduleResponseDataInfo

        dataItemInfo = CorporateScheduleResponseDataInfo(sa13, sa23, sa33)


        val dataItem: CorporateScheduleResponseData = getScheduleMap(dataItemInfo)

        return Observable.just(
            dataItem
        )
    }


    fun getScheduleMap(responseList: CorporateScheduleResponseDataInfo): CorporateScheduleResponseData {

        return CorporateScheduleResponseData(

            day1 = Day(responseList.day1),
            day2 = Day(responseList.day2),
            day3 = Day(responseList.day3)
        )
    }

    fun Day(it: List<CorporateScheduleResponseDayItemInfo>): List<CorporateScheduleResponseDayItem> {
        return it.map {
            CorporateScheduleResponseDayItem(

                day = it.day,
                shiftTiming = it.shiftTiming,
                roomNumber = it.roomNumber,
                natureOfMeeting = it.natureOfMeeting,
                Attendees = DayAttendance(it.Attendees)

            )
        }
    }

    fun DayAttendance(it: List<CorporateScheduleResponseDayAttendanceItemInfo>): List<CorporateScheduleResponseDayAttendanceItem> {
        return it.map {
            CorporateScheduleResponseDayAttendanceItem(
                FundName = it.FundName,
                representative = Representative(it.representative)
            )
        }
    }


    fun Representative(it: List<CorporateScheduleResponseRepresentativeItemInfo>): List<CorporateScheduleResponseRepresentativeItem> {
        return it.map {
            CorporateScheduleResponseRepresentativeItem(
                name = it.name,
                designation = it.designation
            )
        }
    }
}