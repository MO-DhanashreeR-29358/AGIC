package com.mosl.mobile.data.util

import okhttp3.CertificatePinner

interface CertPinProvider {
  fun getPinner(): CertificatePinner?
}