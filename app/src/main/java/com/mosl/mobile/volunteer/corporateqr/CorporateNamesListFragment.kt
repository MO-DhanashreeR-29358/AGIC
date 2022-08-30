package com.mosl.mobile.volunteer.corporateqr

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.databinding.FragmentAttendanceCorporateListBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class CorporateNamesListFragment: MOSLCommonFragment(), CorporateNamesListContract {


    private lateinit var binding: FragmentAttendanceCorporateListBinding
    private lateinit var section: DataManagerSection

    override fun getLayout(): Int {
        return R.layout.fragment_attendance_corporate_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAttendanceCorporateListBinding.bind(view)
        setUpRecyclerView()
        val list = listOf(ItemCorporateName("CorporateName"), ItemCorporateName("CorporateName2"))
        section.addAll(list)
    }

    private fun setUpRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(false)
            adapter = groupAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        section.clearContent()
    }
}