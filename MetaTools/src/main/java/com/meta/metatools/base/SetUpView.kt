package com.meta.metatools.base

import androidx.annotation.LayoutRes

interface SetUpView<VM, STATE> {
    @LayoutRes
    fun getLayoutResId(): Int
    fun getModelClass(): Class<VM>
    fun initialize()
    fun registerEvents()
    fun render(state: STATE)
}