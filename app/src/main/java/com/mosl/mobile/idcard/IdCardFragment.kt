package com.mosl.mobile.idcard


import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.View
import android.view.WindowManager
import com.mosl.mobile.R
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentIcardBinding
import com.mosl.mobile.databinding.FragmentNotesBinding
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class IdCardFragment : MOSLCommonFragment(), IDCardContract {

    @Inject
    lateinit var presenter: IDCardPresenter
    lateinit var binding: FragmentIcardBinding

    @Inject
    lateinit var userManager: UserManager

    override fun getLayout(): Int {
        return R.layout.fragment_icard

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIcardBinding.bind(view)
        val imageByteArray = Base64.decode(userManager.user.clientIDCard, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
        binding.ivClientIDCard.setImageBitmap(bitmap)
    }


}
