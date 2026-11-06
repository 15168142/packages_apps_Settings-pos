/*
 * SPDX-FileCopyrightText: 2026 itsaschoolbus
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.deviceinfo.firmwareversion

import android.content.Context
import androidx.preference.Preference
import com.android.settings.R
import com.android.settingslib.metadata.PreferenceMetadata
import com.android.settingslib.metadata.UI_ONLY_PREFERENCE
import com.android.settingslib.preference.PreferenceBinding
import com.android.settingslib.widget.LayoutPreference

class TsukuyomiLogoPreference : PreferenceMetadata, PreferenceBinding {

    override val key: String
        get() = "pixelos_logo"

    override val purpose: Int
        get() = 0

    override val indexable
        get() = false

    override fun tags(context: Context) = arrayOf(UI_ONLY_PREFERENCE)

    override fun createWidget(context: Context): Preference =
        LayoutPreference(context, R.layout.pixelos168_logo).apply {
            isSelectable = false
        }
}
