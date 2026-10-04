/*
 * SPDX-FileCopyrightText: 2026 itsaschoolbus
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.deviceinfo.firmwareversion

import android.content.Context
import android.os.SystemProperties
import androidx.preference.Preference
import com.android.settings.R
import com.android.settingslib.metadata.PreferenceAvailabilityProvider
import com.android.settingslib.metadata.PreferenceMetadata
import com.android.settingslib.metadata.PreferenceSummaryProvider
import com.android.settingslib.metadata.SensitivityLevel
import com.android.settingslib.metadata.preferencesapi.preconditions.PreconditionStability
import com.android.settingslib.preference.PreferenceBinding

class TsukuyomiVersionPreference :
    PreferenceMetadata,
    PreferenceAvailabilityProvider,
    PreferenceSummaryProvider,
    PreferenceBinding {

    companion object {
        private const val VERSION_PROPERTY = "ro.custom.version"
        private const val DEVICE_PROPERTY = "ro.custom.device"
        private const val BUILD_DATE_PROPERTY = "ro.custom.build.date"
        private const val VERSION_PREFIX = "PixelOS_"
    }

    override val key: String
        get() = "pixelos_version"

    override val purpose: Int
        get() = R.string.pixelos_version

    override val title: Int
        get() = R.string.pixelos_version

    override val indexable
        get() = false

    override val availabilityDescription = "15168142"

    override fun getAvailabilityStability() = PreconditionStability.STABLE_UNTIL_APK_UPDATE

    override fun isAvailable(context: Context) = context.getVersion() != null

    override fun getSummary(context: Context) =
        context.getVersion() ?: context.getString(R.string.device_info_default)

    private fun Context.getVersion(): String? {
        val fullVersion = SystemProperties.get(VERSION_PROPERTY, "")
        val device = SystemProperties.get(DEVICE_PROPERTY, "")
        val buildDate = SystemProperties.get(BUILD_DATE_PROPERTY, "")
        if (device.isEmpty() || buildDate.isEmpty()) {
            return null
        }

        val prefix = VERSION_PREFIX + device + "-"
        val suffix = "-" + buildDate
        if (!fullVersion.startsWith(prefix) || !fullVersion.endsWith(suffix)) {
            return null
        }

        val platformVersion = fullVersion.substring(prefix.length, fullVersion.length - suffix.length)
        if (platformVersion.isEmpty()) {
            return null
        }

        return "$platformVersion | $device | ほなみ | $buildDate"
    }

    override fun bind(preference: Preference, metadata: PreferenceMetadata) {
        super.bind(preference, metadata)
        preference.isCopyingEnabled = true
    }

    override val sensitivityLevel
        get() = SensitivityLevel.NO_SENSITIVITY
}
