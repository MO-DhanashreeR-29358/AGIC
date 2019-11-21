package com.mosl.mobile.coredata.util

import okhttp3.CertificatePinner

interface CertPinProvider {
    fun getPinner(): CertificatePinner?
}