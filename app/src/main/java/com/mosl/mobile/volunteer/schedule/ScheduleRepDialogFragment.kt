package com.mosl.mobile.volunteer.schedule

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.app.coreui.dialog.BaseDialogFragment
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.FragmentScheduleRepDialogBinding
import com.mosl.mobile.volunteer.menu.ScreenNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.mosl.mobile.volunteer.domain.schedule.ScheduleRepresentativeItemData
import com.mosl.groupiex.helper.groupie.GroupAdapter
import com.mosl.groupiex.helper.groupie.GroupieViewHolder
import com.mosl.groupiex.helper.groupie.Section
import com.mosl.groupiex.helper.databind.BindableItem

class ScheduleRepDialogFragment : BaseDialogFragment() {

    lateinit var repoList: List<ScheduleRepresentativeItemData>

    internal lateinit var binding: FragmentScheduleRepDialogBinding
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private lateinit var section: DataManagerSection



    override fun getLayout(): Int {
       return R.layout.fragment_schedule_rep_dialog
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentScheduleRepDialogBinding.bind(view)
        binding.ivClose.setOnClickListener {
            dismiss()
        }

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.rvScheduleRepo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
        for (model in repoList) {
            section.add(ItemScheduleRepo(model))
        }
    }

    fun setData(list: List<ScheduleRepresentativeItemData>) {
        this.repoList = list
    }
}
