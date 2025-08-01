// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.java.dev.psiViewer.debug

import com.intellij.openapi.components.*
import com.intellij.util.xmlb.XmlSerializerUtil

@Service
@State(name = "PsiViewerDebugSettings", storages = [Storage(StoragePathMacros.NON_ROAMABLE_FILE)])
internal class PsiViewerDebugSettings : PersistentStateComponent<PsiViewerDebugSettings> {
  var showDialogFromDebugAction: Boolean = false

  var watchMode: Boolean = false

  override fun getState(): PsiViewerDebugSettings = this

  override fun loadState(state: PsiViewerDebugSettings) {
    XmlSerializerUtil.copyBean(state, this)
  }

  companion object {
    @JvmStatic
    fun getInstance(): PsiViewerDebugSettings = service()
  }
}