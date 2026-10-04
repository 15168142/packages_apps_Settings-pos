/*
 * SPDX-FileCopyrightText: 2026 itsaschoolbus
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class TsukuyomiVersionPreferenceController extends BasePreferenceController {

    private static final String VERSION_PROPERTY = "ro.custom.version";
    private static final String DEVICE_PROPERTY = "ro.custom.device";
    private static final String BUILD_DATE_PROPERTY = "ro.custom.build.date";
    private static final String VERSION_PREFIX = "PixelOS_";

    public TsukuyomiVersionPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return getVersion() != null ? AVAILABLE_UNSEARCHABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public CharSequence getSummary() {
        final String version = getVersion();
        return version != null ? version : mContext.getString(R.string.device_info_default);
    }

    private String getVersion() {
        final String fullVersion = SystemProperties.get(VERSION_PROPERTY, "");
        final String device = SystemProperties.get(DEVICE_PROPERTY, "");
        final String buildDate = SystemProperties.get(BUILD_DATE_PROPERTY, "");
        if (TextUtils.isEmpty(device) || TextUtils.isEmpty(buildDate)) {
            return null;
        }

        final String prefix = VERSION_PREFIX + device + "-";
        final String suffix = "-" + buildDate;
        if (!fullVersion.startsWith(prefix) || !fullVersion.endsWith(suffix)) {
            return null;
        }

        final String platformVersion = fullVersion.substring(prefix.length(), fullVersion.length() - suffix.length());
        if (platformVersion.isEmpty()) {
            return null;
        }

        return platformVersion + " | " + device + " | ほなみ | " + buildDate;
    }
}
