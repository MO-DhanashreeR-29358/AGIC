package com.mosl.navigator

import androidx.fragment.app.Fragment

interface FragmentResolver {
  fun resolve(pageId: Int): Fragment?
}