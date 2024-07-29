package com.mosl.mobile.volunteer.attendance.corporateqr

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.mosl.app.core.prefs.StringPreference
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.RemoteConfigData
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.data.RemoteConfigModel
import com.mosl.mobile.volunteer.databinding.FragmentAttendanceCorporateListBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class CorporateNamesListFragment: MOSLCommonFragment(), CorporateNamesListContract {
    private lateinit var binding: FragmentAttendanceCorporateListBinding
    private lateinit var section: DataManagerSection
    private var remoteConfigModel: RemoteConfigModel? = null

    @Inject
    lateinit var userManager: UserManager

    @Inject
    @field:RemoteConfigData
    lateinit var remoteConfigData: StringPreference

    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.fragment_attendance_corporate_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAttendanceCorporateListBinding.bind(view)
        remoteConfigModel =
            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
        setUpViews()
        initData()
    }

    private fun initData() {
//        val banner: RemoteConfigModel? =
//            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
        Glide.with(this)
            .load(remoteConfigModel?.headerImage)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.header.icMosl)
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

        val corpList = userManager.getUser()?.corporateList?.map {
            ItemCorporateName(it, screenNavigator)
        }.orEmpty()

        section.addAll(corpList)
    }

    private fun setUpViews() {
        (requireActivity() as? MOSLCommonActivity)?.toggleAppBarVisibility(true)
        setUpRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        section.clearContent()
    }
}