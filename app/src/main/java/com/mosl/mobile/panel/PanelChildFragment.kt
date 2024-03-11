package com.mosl.mobile.panel


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLPagerItemFragment
import com.mosl.mobile.databinding.CalendarAddDialogBinding
import com.mosl.mobile.databinding.FragmentPanelChildBinding
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.panelpager.PanelListDayItemData
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.addToCalendart
import com.mosl.shared.util.getDateJSON_DATE6
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PanelChildFragment : MOSLPagerItemFragment(), PanelChildView {
    @Inject
    lateinit var presenter: PanelChildPresenter
    lateinit var binding: FragmentPanelChildBinding
    lateinit var section: DataManagerSection

    lateinit var publishSubject: Observable<UiModel<List<PanelDayResponse>>>
    private var publishSubjectItem = PublishSubject.create<PanelUiEvent>()

    override fun getLayout(): Int {
        return R.layout.fragment_panel_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPanelChildBinding.bind(view)
        setupRecyclerView()
        publishSubject = (parentFragment as? PanelFragment)?.tabPublishSubject ?: Observable.empty()
        presenter.start()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.rvPanelList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    override fun panelDayList(): ArrayList<PanelListDayItemData>? {
        return arguments?.getParcelableArrayList(PanelFragment.PANEL_DAY_LIST)
    }

    override fun renderPage(uiModel: UiModel<List<PanelDayResponse>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess { panelListDayData ->
            section.clearContent()
            for (model in panelListDayData) {
                section.add(ItemPanelList(model, context!!, publishSubjectItem))
            }
        }

    }

    override fun onTabClicked(): Observable<UiModel<List<PanelDayResponse>>> {
        return publishSubject.ofType()
    }

    lateinit var bindingDialog: CalendarAddDialogBinding

    internal fun calendarDialog(panelDayResponse: PanelDayResponse) {

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.calendar_add_dialog, null)

        bindingDialog = CalendarAddDialogBinding.bind(mDialogView)

        bindingDialog.apply {

            val mBuilder = AlertDialog.Builder(context!!).setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            txtEvent.text = String.format(
                "%s" + "\nDate: " + "%s" +
                        "\nStart Time: " + "%s" +
                        "\nEnd Time: " + "%s",
                panelDayResponse.cRepresentiveName,
                getDateJSON_DATE6(panelDayResponse.representativeStarttime),
                getTimeAMPM(panelDayResponse.representativeStarttime),
                getTimeAMPM(panelDayResponse.representativeEndtime)
            )
            btnYes.setOnClickListener {
                addToCalendart(
                    context!!,
                    panelDayResponse.cRepresentiveName,
                    panelDayResponse.speakerIntroduction,
                    "",
                    panelDayResponse.representativeStarttime,
                    panelDayResponse.representativeEndtime
                )

                mAlertDialog.dismiss()
            }
            //cancel button click of custom layout
            btnNo.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }
    }


    override fun renderItemToCalender(panelDayResponse: PanelDayResponse) {
        calendarDialog(panelDayResponse)
    }

    override fun onItemClicked(): Observable<PanelUiEvent.ItemClick> {
        return publishSubjectItem.ofType()
    }

}
