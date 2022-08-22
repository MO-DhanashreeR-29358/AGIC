package com.mosl.volunteer.data.util.cache

import android.os.SystemClock
import io.reactivex.Maybe
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit.HOURS
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject
import javax.inject.Singleton

data class CacheOptions @JvmOverloads constructor(
  val validity: Long,
  val forUser: Boolean = false
)

data class CacheEntry(
  val entryTime: Long,
  val data: Any,
  val options: CacheOptions
) {
  fun isExpired(): Boolean {
    val currentTime = SystemClock.elapsedRealtime()
    val lastUpdateTime = entryTime
    return lastUpdateTime == 0L || currentTime - lastUpdateTime > options.validity
  }

  fun forUser() = options.forUser
}

@JvmField
val CacheForHour = CacheOptions(HOURS.toMillis(1))

@JvmField
val UserCacheForHour: CacheOptions = CacheForHour.copy(forUser = true)

@JvmField
val NoCache = CacheOptions(0)

@Singleton
class InMemoryCache @Inject constructor() {
  private val memoryCache: ConcurrentHashMap<Any, CacheEntry> = ConcurrentHashMap()

  init {
    Observable.interval(15, SECONDS)
        .subscribe { _ ->
          val expiredKeys = memoryCache.filter { it.value.isExpired() }
              .keys
          for (key in expiredKeys) {
            evict(key)
          }
          if (memoryCache.size > 0)
            Timber.i("Cache Entry Count : %d", memoryCache.size)
        }
  }

  fun clearUserEntries() {
    val usersEntries = memoryCache.filter { it.value.forUser() }
        .keys
    for (key in usersEntries) {
      evict(key)
    }
  }

  fun evict(key: Any) {
    memoryCache.remove(key)
  }

  fun evictAll() {
    memoryCache.clear()
  }

  @JvmOverloads
  fun set(
    key: Any,
    value: Any,
    options: CacheOptions = UserCacheForHour
  ) {
    memoryCache[key] = CacheEntry(SystemClock.elapsedRealtime(), value, options)
  }

  @Suppress("UNCHECKED_CAST")
  fun <V : Any> getValue(key: Any): V? {
    return memoryCache[key]?.let {
      it.data as V
    }
  }

  fun contains(key: Any): Boolean {
    return memoryCache.contains(key)
  }

  @Suppress("UNCHECKED_CAST")
  fun <V : Any> get(key: Any): Maybe<V> {
    return memoryCache[key]?.let {
      Maybe.just(it.data as V)
    } ?: Maybe.empty<V>()
  }
}
