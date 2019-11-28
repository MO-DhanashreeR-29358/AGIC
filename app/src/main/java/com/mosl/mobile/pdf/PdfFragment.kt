package com.mosl.mobile.pdf


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.mosl.app.core.events.DataAction
import com.mosl.mobile.R

import com.mosl.mobile.databinding.FragmentPdfBinding
import io.reactivex.Observable


/**
 * A simple [Fragment] subclass.
 */
class PdfFragment : DialogFragment(), PdfContract {

    lateinit var binding: FragmentPdfBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_pdf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPdfBinding.bind(view)
        dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.drawable.border_dialog))

        binding.apply {

            pdfTvCancel.setOnClickListener {
                dialog?.dismiss()
            }

            pdfTvOk.setOnClickListener {
                pdfTvCancel.performClick()
            }
        }


    }


    override fun isDataEmpty(): Boolean {
        return false
    }

    override fun onDataAction(): Observable<DataAction> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }


    companion object {
        @JvmStatic
        fun newInstance(): PdfFragment {
            return PdfFragment()
        }
    }
}
