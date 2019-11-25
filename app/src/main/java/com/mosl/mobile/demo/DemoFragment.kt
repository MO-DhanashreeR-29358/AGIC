package com.mosl.mobile.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import timber.log.Timber
import javax.inject.Inject
import androidx.lifecycle.observe
import com.mosl.mobile.databinding.FragmentDemoBinding

class DemoFragment() : MOSLCommonFragment(),DemoView {
  @Inject
  lateinit var presenter: DemoPresenter
  lateinit var binding: FragmentDemoBinding
  @Inject
  lateinit var factory: ViewModelProvider.NewInstanceFactory

  override fun getLayout(): Int {
    return R.layout.fragment_demo
  }

  val viewModel: AuthViewModel by viewModels { factory }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentDemoBinding.bind(view)

    binding.viewmodel = viewModel


    presenter.start()
  }

  override fun onDestroyView() {
    presenter.stop()
    super.onDestroyView()
  }

  override fun onStarted() {
    binding.progressBar.show()
  }

  @SuppressLint("TimberArgCount")
  override fun onSuccess(user: LiveData<AuthResponseM>) {
    user.observe(viewLifecycleOwner) { result ->
      Timber.d("Success %s ", result.message)
    }
    binding.progressBar.hide()
  }

  override fun onFailure(message: String) {
    binding.progressBar.hide()
    binding.rootLayout.snackbar(message)
  }

}