package com.mosl.mobile.volunteer.coredata.util

import okhttp3.CertificatePinner

interface CertPinProvider {
    fun getPinner(): CertificatePinner?
}