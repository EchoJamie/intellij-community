// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.polySymbols

import com.intellij.openapi.util.NlsSafe
import com.intellij.polySymbols.impl.PolySymbolOriginImpl
import com.intellij.polySymbols.utils.PolySymbolTypeSupport
import javax.swing.Icon

/*
 * INAPPLICABLE_JVM_NAME -> https://youtrack.jetbrains.com/issue/KT-31420
 **/
interface PolySymbolOrigin {
  val framework: @NlsSafe FrameworkId?
    get() = null

  val library: @NlsSafe String?
    get() = null

  val version: @NlsSafe String?
    get() = null

  val defaultIcon: Icon?
    get() = null

  val typeSupport: PolySymbolTypeSupport?
    get() = null

  fun loadIcon(path: String): Icon? = null

  companion object {
    @JvmStatic
    fun create(
      framework: FrameworkId? = null,
      library: String? = null,
      version: String? = null,
      defaultIcon: Icon? = null,
      typeSupport: PolySymbolTypeSupport? = null,
    ): PolySymbolOrigin =
      PolySymbolOriginImpl(framework, library, version, defaultIcon, typeSupport)

    @JvmStatic
    fun empty(): PolySymbolOrigin =
      PolySymbolOriginImpl.empty
  }

}