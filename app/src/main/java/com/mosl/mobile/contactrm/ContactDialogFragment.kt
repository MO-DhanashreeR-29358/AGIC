package com.mosl.mobile.contactrm

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.app.coreui.dialog.BaseDialogFragment
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.appuser.model.ContactListItemData
import com.mosl.mobile.databinding.FragmentContactDialogBinding
import com.mosl.mobile.menu.ScreenNavigator
import com.mosl.mobile.util.initiateCall
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ContactDialogFragment : BaseDialogFragment(), ContactView {

    internal lateinit var binding: FragmentContactDialogBinding
    @Inject
    lateinit var screenNavigator: ScreenNavigator

    @Inject
    lateinit var presenter: ContactDialogPresenter
    private lateinit var section: DataManagerSection
    private val callUiEvent = PublishSubject.create<CallUiEvent>()

    override fun getLayout(): Int {
        return R.layout.fragment_contact_dialog
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
        binding = FragmentContactDialogBinding.bind(view)
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        setupRecyclerView()
        presenter.start()

    }


    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        section = DataManagerSection(onRetryClick)
        binding.rvContactList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
        groupAdapter.add(section)


        val itemData = getSendBackListData()

        val list = ArrayList<ContactListitem>()
        itemData?.forEach {
            val item = ContactListitem(it, callUiEvent)
            list.add(item)
            section.add(item)
        }
        section.clearContent()
        section.setContent(list)

    }

    override fun showAlertForCall(mobileNumber: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.main_app_name)
            .setMessage(R.string.call_alert_message)
            .setCancelable(true)
            .setPositiveButton(getString(R.string.text_label_yes)) { _, _ ->
                dialNumber(mobileNumber)
            }
            .setNegativeButton(getString(R.string.text_label_no)) { _, _ ->
            }
            .show()
    }

    private fun dialNumber(phoneNumber: String) {
        requireContext().initiateCall(phoneNumber)
    }

    override fun onCallClicked(): Observable<CallUiEvent.CallClick> {
        return callUiEvent.ofType()
    }


    fun getSendBackListData(): ArrayList<ContactListItemData>? {
        return arguments?.getParcelableArrayList(CONTACT_LIST)
    }


    override fun isDataEmpty(): Boolean {
        return false
    }

    companion object {

        const val CONTACT_LIST = "ContactList"
        @JvmStatic
        fun newInstance(
            list: List<ContactListItemData>
        ): ContactDialogFragment {
            val fragment = ContactDialogFragment()
            fragment.arguments = Bundle().apply {
                putParcelableArrayList(CONTACT_LIST, list as ArrayList<ContactListItemData>)
            }
            return fragment
        }
    }


}