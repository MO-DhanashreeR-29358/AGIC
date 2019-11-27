package com.mosl.mobile.bytes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentBytesBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import timber.log.Timber
import javax.inject.Inject

class BytesFragment : MOSLCommonFragment(), BytesContract {
    @Inject
    lateinit var presenter: BytesPresenter
    lateinit var binding: FragmentBytesBinding
    lateinit var section: Section

    override fun getLayout(): Int {
        return R.layout.fragment_bytes
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBytesBinding.bind(view)
        setupRecyclerView()
        //presenter.start()
    }

    private fun setupRecyclerView() {
        val repository: BytesRepository = BytesRepositoryImpl
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = Section()
        groupAdapter.add(section)
        binding.rvBytesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(false)
            adapter = groupAdapter
        }

        val data = repository.getBytesList()

        val items = mutableListOf<BindableItem<*>>()

        data.forEach {
            for (header in it) {
                items.add(BytesHeaderItem(header.title))
                for (model in header.bytesList) {
                    items.add(BytesItem(model))
                }
            }
            section.update(items)
            section.notifyChanged()
        }.dispose()

    }


}