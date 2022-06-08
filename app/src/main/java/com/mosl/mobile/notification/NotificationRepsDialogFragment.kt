package com.mosl.mobile.notification

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.app.coreui.dialog.BaseDialogFragment
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.databinding.DialogNotificationRepsBinding
import com.mosl.mobile.menu.ScreenNavigator
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NotificationRepsDialogFragment : BaseDialogFragment() {

    lateinit var repoList: List<String>

    internal lateinit var binding: DialogNotificationRepsBinding
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private lateinit var section: DataManagerSection


    override fun getLayout(): Int {
        return R.layout.dialog_notification_reps
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

        binding = DialogNotificationRepsBinding.bind(view)
        binding.ivClose.setOnClickListener {
            dismiss()
        }

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.rvScheduleRepo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
        for (model in repoList) {
            section.add(ItemNotificationReps(model))
        }
    }

    fun setData(list: List<String>) {
        this.repoList = list
    }
}
