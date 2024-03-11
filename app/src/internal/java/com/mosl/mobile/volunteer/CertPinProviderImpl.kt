package com.mosl.mobile.volunteer

import com.mosl.mobile.volunteer.coredata.util.CertPinProvider
import okhttp3.CertificatePinner

object CertPinProviderImpl : CertPinProvider {
  override fun getPinner(): CertificatePinner? {
    return null
  }
}