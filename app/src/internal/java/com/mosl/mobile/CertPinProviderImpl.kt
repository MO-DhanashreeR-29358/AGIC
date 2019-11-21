package com.mosl.mobile

import com.mosl.mobile.data.util.CertPinProvider
import okhttp3.CertificatePinner

object CertPinProviderImpl : CertPinProvider {
  override fun getPinner(): CertificatePinner? {
    return null
  }
}