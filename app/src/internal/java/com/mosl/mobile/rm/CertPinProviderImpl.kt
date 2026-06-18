package com.mosl.mobile.rm

import com.mosl.mobile.rm.coredata.util.CertPinProvider
import okhttp3.CertificatePinner

object CertPinProviderImpl : CertPinProvider {
  override fun getPinner(): CertificatePinner? {
    return null
  }
}