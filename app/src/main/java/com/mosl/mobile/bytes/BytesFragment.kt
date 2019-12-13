package com.mosl.mobile.bytes

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.ErrorState
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.prefs.StringPreference
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.annotations.LastBytesDate
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentBytesBinding
import com.mosl.mobile.domain.bytes.BytesData
import com.mosl.mobile.domain.bytes.BytesItemModel
import com.mosl.mobile.domain.bytes.BytesResponse
import com.mosl.mobile.util.toggleProgressBar
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.getFormatedDate
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class BytesFragment : MOSLCommonFragment(), BytesContract {
    @Inject
    lateinit var presenter: BytesPresenter
    lateinit var binding: FragmentBytesBinding
    lateinit var section: DataManagerSection

    @Inject
    @field:LastBytesDate
    lateinit var bytesDate: StringPreference

    override fun getLayout(): Int {
        return R.layout.fragment_bytes
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBytesBinding.bind(view)
        setupRecyclerView()
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                dataActionSubject.onNext(DataAction.Fetch)
                handler.postDelayed(this, API_INTERVAL_TIME)
            }
        }
        handler.postDelayed(runnable, API_INTERVAL_TIME)
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(false)
            adapter = groupAdapter
        }

    }


    override fun swipeRefresh(): Observable<Unit> {
        return binding.swipeRefresh.refreshes()
    }

    override fun renderData(uiModel: UiModel<List<BytesResponse>>) {
        binding.swipeRefresh.toggleProgressBar(section, uiModel.inProgress)
        uiModel.onFailure { it ->
            section.showError(it)
        }

        uiModel.onSuccess {
            bytesDate.set(AppDateFormats.DF_JSON_DATE_4.format(Date()))
            if (it.isNotEmpty()) {
                section.clearContent()
//            val sortedList = it.sortedWith(compareBy({ it.entryDate }))
                val bytesList = getBytesData(it)
                val items = mutableListOf<BindableItem<*>>()
                for (header in bytesList) {
                    items.add(BytesHeaderItem(header.headerTime))
                    for (model in header.itemList) {
                        items.add(ItemBytes(model))
                    }

                }
                section.update(items)
            } else {
                section.showEmptyView(
                    ErrorModel(
                        NO_DATA_AVAILABLE_FOR_THE_DAY,
                        ErrorState.NO_DATA_ERROR
                    )
                )
            }
        }
    }


    fun getBytesData(dataList: List<BytesResponse>): List<BytesData> {
        val bytesListData = mutableListOf<BytesData>()


        val distinctDatesList = dataList
            .map {
                getFormatedDate(
                    it.entryDate,
                    AppDateFormats.DF_JSON_DATE_4,
                    AppDateFormats.DF_JSON_DATE_10
                )
            }
            .distinctBy { it }


        distinctDatesList.forEachIndexed { indexDate, filteredDates ->
            val childItemList = mutableListOf<BytesItemModel>()
            dataList.forEachIndexed { _, bytesResponse ->
                if (bytesResponse.entryDate.contains(filteredDates)) {
                    childItemList.add(BytesItemModel(bytesResponse.byte, bytesResponse.entryDate))
                }
            }
            bytesListData.add(indexDate, BytesData(filteredDates, childItemList))

        }

        return bytesListData
    }

    companion object {
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
        const val API_INTERVAL_TIME = (60*2000).toLong()
    }
}