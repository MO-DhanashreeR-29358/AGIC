package com.mosl.app.core.error

import androidx.annotation.StringRes
import com.mosl.app.core.R

/**
 * Created by ankitnagda on 8/2/2016.
 */
enum class ErrorState(@JvmField @param:StringRes val messageStr: Int) {
  NETWORK_ERROR(R.string.network_failure),
  SERVICE_ERROR(R.string.service_failure),
  VALIDATION_ERROR(R.string.error_message_validation_failed),
  NO_DATA_ERROR(R.string.error_message_no_data_available),
  UNKNOWN_ERROR(R.string.unknown_failure);
}