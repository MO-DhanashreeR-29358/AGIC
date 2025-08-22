package com.mosl.mobile.notes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.mosl.agicapp.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.agicapp.databinding.FragmentNotesBinding
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NotesFragment : MOSLCommonFragment(), NotesContract {

    @Inject
    lateinit var presenter: NotesPresenter
    lateinit var binding: FragmentNotesBinding

    override fun getLayout(): Int {
        return R.layout.fragment_notes

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)
    }


}
