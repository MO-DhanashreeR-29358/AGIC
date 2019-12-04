package com.mosl.mobile.schedule

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.mobile.R
import com.mosl.mobile.databinding.FragmentScheduleRepDialogBinding
import com.mosl.mobile.menu.ScreenNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo_Rep
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

class ScheduleRepDialogFragment : DialogFragment() {

    lateinit var repoList: List<ScheduleResponseItemInfo_Rep>

    internal lateinit var binding: FragmentScheduleRepDialogBinding
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private lateinit var section: Section

    fun setData(list: List<ScheduleResponseItemInfo_Rep>) {
        this.repoList = list
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule_rep_dialog, container, false)
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = Section()
        groupAdapter.add(section)
        binding.rvScheduleRepo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)


    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentScheduleRepDialogBinding.bind(view)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.ivClose.setOnClickListener {
            dismiss()
        }

        setupRecyclerView()

        for (model in repoList) {
            section.add(ItemScheduleRepo(model, context!!))
        }
    }
}