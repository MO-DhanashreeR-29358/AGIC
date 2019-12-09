package com.mosl.mobile.bytes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.mosl.app.core.events.DataAction
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentBytesBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import javax.inject.Inject

class BytesFragment : MOSLCommonFragment(), BytesContract {
    @Inject
    lateinit var presenter: BytesPresenter
    lateinit var binding: FragmentBytesBinding
    lateinit var section: DataManagerSection

    override fun getLayout(): Int {
        return R.layout.fragment_bytes
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBytesBinding.bind(view)
        setupRecyclerView()
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
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

    override fun renderData(uiModel: UiModel<List<String>>) {
        uiModel.onFailure { it ->
            section.showError(it)
        }

        uiModel.onSuccess {
            section.clearContent()
//            val sortedList = it.Data.sortedWith(compareBy({ it.startTime }))
//            val items = mutableListOf<BindableItem<*>>()
//            for (header in sortedList) {
//                items.add(BytesHeaderItem(header.title))
//                for (model in header.bytesList) {
//                    items.add(ItemBytes(model))
//                }
//            }
//            section.update(items)
        }
    }

}