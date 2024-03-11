package com.mosl.mobile.volunteer.bytes

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.ErrorState
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.prefs.StringPreference
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.LastBytesData
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.databinding.FragmentBytesBinding
import com.mosl.mobile.volunteer.domain.bytes.BytesData
import com.mosl.mobile.volunteer.domain.bytes.BytesItemModel
import com.mosl.mobile.volunteer.domain.bytes.BytesResponse
import com.mosl.mobile.volunteer.util.toggleProgressBar
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.getFormatedDate
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.Observable
import javax.inject.Inject

class BytesFragment : MOSLCommonFragment(), BytesContract {
    @Inject
    lateinit var presenter: BytesPresenter
    lateinit var binding: FragmentBytesBinding
    lateinit var section: DataManagerSection

    @Inject
    @field:LastBytesData
    lateinit var bytesData: StringPreference

    @Inject
    lateinit var gson: Gson

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
            }
        }
        handler.postDelayed(runnable, API_INTERVAL_TIME)
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
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
            if (bytesData.get().isEmpty()) {
                section.showError(it)
            } else {
                val type = object : TypeToken<List<BytesResponse>>() {}.type
                val bytesResponse: List<BytesResponse> =
                    gson.fromJson(bytesData.get(), type)
                bytesResponse.responseData()
            }
        }

        uiModel.onSuccess {

            if (it.isEmpty()) {
                if (bytesData.get().isNotEmpty()) {
                    val type = object : TypeToken<MutableList<BytesResponse>>() {}.type
                    val bytesResponse: MutableList<BytesResponse> =
                        gson.fromJson(bytesData.get(), type)
                    bytesResponse.responseData()
                } else {
                    it.responseData()
                    val json = gson.toJson(it)
                    bytesData.set(json)
                }
            } else {
                if (bytesData.get().isNotEmpty()) {
                    val type = object : TypeToken<MutableList<BytesResponse>>() {}.type
                    val bytesResponse: MutableList<BytesResponse> =
                        gson.fromJson(bytesData.get(), type)
                    bytesResponse.addAll(it)
                    bytesResponse.responseData()
                    val json = gson.toJson(bytesResponse)
                    bytesData.set(json)

                } else {
                    it.responseData()
                    val json = gson.toJson(it)
                    bytesData.set(json)
                }
            }

        }
    }

    private fun List<BytesResponse>.responseData() {
        if (isNotEmpty()) {
            section.clearContent()
            val bytesList = getBytesData(this)
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
            val sortedList = childItemList.sortedWith(compareByDescending({ it.entryDate }))
            bytesListData.add(indexDate, BytesData(filteredDates, sortedList))

        }

        return bytesListData
    }

    companion object {
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
        const val API_INTERVAL_TIME = (60 * 2000).toLong()
    }
}