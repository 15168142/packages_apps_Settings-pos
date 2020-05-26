/*
 * SPDX-FileCopyrightText: 2015-2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.custom.brightness

import android.content.Context
import android.provider.Settings
import com.android.settings.R
import com.android.settingslib.metadata.preferencesapi.types.EnumApiWithRes

private const val DEFAULT_SHOW = 1
private const val DEFAULT_POSITION = 0

enum class BrightnessSliderShowOption(
    override val asApiValue: Int,
    override val purpose: Int,
) : EnumApiWithRes<Int> {
    NEVER(0, R.string.status_bar_brightness_slider_show_never),
    EXPANDED(1, R.string.status_bar_brightness_slider_show_expanded),
    ALWAYS(2, R.string.status_bar_brightness_slider_show_always),
}

enum class BrightnessSliderPositionOption(
    override val asApiValue: Int,
    override val purpose: Int,
) : EnumApiWithRes<Int> {
    TOP(0, R.string.status_bar_brightness_slider_position_top),
    BOTTOM(1, R.string.status_bar_brightness_slider_position_bottom),
}

internal fun Context.getBrightnessSliderShow(): BrightnessSliderShowOption {
    val value = Settings.Secure.getInt(contentResolver, "qs_show_brightness_slider", DEFAULT_SHOW)
    return BrightnessSliderShowOption.entries.firstOrNull { it.asApiValue == value }
        ?: BrightnessSliderShowOption.EXPANDED
}

internal fun Context.setBrightnessSliderShow(value: BrightnessSliderShowOption) {
    Settings.Secure.putInt(contentResolver, "qs_show_brightness_slider", value.asApiValue)
}

internal fun Context.getBrightnessSliderPosition(): BrightnessSliderPositionOption {
    val value =
        Settings.Secure.getInt(contentResolver, "qs_brightness_slider_position", DEFAULT_POSITION)
    return BrightnessSliderPositionOption.entries.firstOrNull { it.asApiValue == value }
        ?: BrightnessSliderPositionOption.TOP
}

internal fun Context.setBrightnessSliderPosition(value: BrightnessSliderPositionOption) {
    Settings.Secure.putInt(contentResolver, "qs_brightness_slider_position", value.asApiValue)
}