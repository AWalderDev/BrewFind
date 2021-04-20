package com.brewfind.ui.main

import android.view.View
import com.brewfind.dto.Brewery

interface OnClickCallback {
    fun onClick(brewery: Brewery)
}