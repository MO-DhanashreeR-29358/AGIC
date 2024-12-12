package com.mosl.debugview.core

interface MockResponseSupplier {
  operator fun <T : Enum<T>> get(cls: Class<T>): T

  fun set(value: Enum<*>)
}
