package com.mosl.commons.store

import com.mosl.commons.cache.CacheOptions
import com.mosl.commons.cache.CacheOptions.Companion.NoCache
import com.mosl.commons.cache.CacheOptions.Companion.UserCacheForHour
import com.mosl.commons.cache.InMemoryCache
import io.reactivex.Single

class StoreFactory(
    private val cache: InMemoryCache
) {
  fun <Key : Any, Value : Any> create(
      fetcher: (Key) -> Single<Value>
  ): MiniStore<Key, Value> {
    return createCached(NoCache, fetcher)
  }

  fun <Key : Any, Value : Any> createCached(
      cacheOptions: CacheOptions = UserCacheForHour,
      fetcher: (Key) -> Single<Value>
  ): MiniStore<Key, Value> {
    return RealMiniStore(fetcher, cacheOptions, cache)
  }
}