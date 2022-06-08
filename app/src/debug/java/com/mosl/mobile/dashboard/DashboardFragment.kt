package com.mosl.mobile.dashboard

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.widget.textChanges
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentDashboardBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.Observable
import javax.inject.Inject

class DashboardFragment : MOSLCommonFragment(), DashboardContract.View {
  @Inject
  lateinit var presenter: DashboardPresenter
  lateinit var binding: FragmentDashboardBinding
  lateinit var section: Section

  override fun getLayout(): Int {
    return R.layout.fragment_dashboard
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentDashboardBinding.bind(view)
    setupRecyclerView()
    presenter.start()
  }

  fun setupRecyclerView() {
    val context = requireContext()
    val dashboardAdapter = GroupAdapter<ViewHolder>()
    dashboardAdapter.setOnItemClickListener { item, _ ->
      if (item is DashboardItem) {
        navigateDashboardFragment(item.data)
      }
    }
    section = Section()
    dashboardAdapter.add(section)
    binding.rvDashboard.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
      )
      adapter = dashboardAdapter
    }
  }

  private fun navigateDashboardFragment(pageId: Int) {
    val bundle = Bundle()
    navigateTo(pageId, bundle, true)
  }

  override fun onSearchChanged(): Observable<CharSequence> {
    return binding.editSearch.textChanges()
  }

  override fun renderPage(data: List<DashboardHeader>) {
    val items = mutableListOf<BindableItem<*>>()
    for (header in data) {
      items.add(DashboardHeaderItem(header.title))
      for (model in header.screens) {
        items.add(DashboardItem(model))
      }
    }
    section.update(items)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.stop()
    binding.rvDashboard.adapter = null
  }
}
