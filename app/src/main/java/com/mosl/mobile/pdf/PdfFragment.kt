package com.mosl.mobile.pdf


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mosl.app.core.network.isNetworkConnected
import com.mosl.app.coreui.dialog.BaseDialogFragment
import com.mosl.mobile.R
import com.mosl.mobile.databinding.FragmentPdfBinding
import com.mosl.mobile.appuser.login.UserManagerImpl
import com.mosl.mobile.util.downloadDocument
import com.mosl.mobile.util.openUrlInBrowser
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PdfFragment : BaseDialogFragment(), PdfContract {

    lateinit var binding: FragmentPdfBinding

    @Inject
    lateinit var userManager: UserManagerImpl

    override fun getLayout(): Int {
        return R.layout.fragment_pdf
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPdfBinding.bind(view)

        binding.apply {

            tvPdfCancel.setOnClickListener {
                dialog?.dismiss()
            }

            tvPdfOk.setOnClickListener {
                if (requireContext().isNetworkConnected()) {

                    if(userManager.getPdf().isNotEmpty())
                    requireContext().openUrlInBrowser(userManager.getPdf())

                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.msg_pdf_needs_network_to_download,
                        Toast.LENGTH_LONG
                    ).show()
                }
                dialog?.dismiss()

            }
        }


    }


    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }


    companion object {

        const val PDF_LINK = "pdf_link"

        @JvmStatic
        fun newInstance(): PdfFragment {
            val fragment = PdfFragment()
            return fragment
        }
    }
}
