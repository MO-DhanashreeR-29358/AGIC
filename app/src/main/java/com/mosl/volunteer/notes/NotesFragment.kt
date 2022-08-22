package com.mosl.volunteer.notes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.mosl.volunteer.R
import com.mosl.volunteer.core.MOSLCommonFragment
import com.mosl.volunteer.databinding.FragmentNotesBinding
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
