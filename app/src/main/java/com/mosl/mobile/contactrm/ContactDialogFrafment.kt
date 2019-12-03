package com.mosl.mobile.contactrm

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mosl.mobile.R
import com.mosl.mobile.databinding.FragmentContactDialogBinding
import com.mosl.mobile.menu.ScreenNavigator
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ContactDialogFrafment : DialogFragment() {

    internal lateinit var binding: FragmentContactDialogBinding
    @Inject
    lateinit var screenNavigator: ScreenNavigator


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_dialog, container, false)
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
        binding = FragmentContactDialogBinding.bind(view)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
}