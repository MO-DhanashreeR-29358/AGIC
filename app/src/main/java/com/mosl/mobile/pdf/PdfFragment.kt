package com.mosl.mobile.pdf


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.mosl.app.core.events.DataAction
import com.mosl.app.coreui.dialog.BaseDialogFragment
import com.mosl.mobile.R

import com.mosl.mobile.databinding.FragmentPdfBinding
import io.reactivex.Observable


/**
 * A simple [Fragment] subclass.
 */
class PdfFragment : BaseDialogFragment(), PdfContract {

    lateinit var binding: FragmentPdfBinding

    override fun getLayout(): Int {
        return R.layout.fragment_pdf
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPdfBinding.bind(view)
//        dialog?.window?.setBackgroundDrawable(requireContext().getDrawable(R.drawable.border_dialog))

        binding.apply {

            tvPdfCancel.setOnClickListener {
                dialog?.dismiss()
            }

            tvPdfOk.setOnClickListener {
                tvPdfCancel.performClick()
            }
        }


    }


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }


    companion object {
        @JvmStatic
        fun newInstance(): PdfFragment {
            return PdfFragment()
        }
    }
}
