package com.mosl.navigator

sealed class AuthorizationState {
  object OK : AuthorizationState()
  object CANCEL : AuthorizationState()
}