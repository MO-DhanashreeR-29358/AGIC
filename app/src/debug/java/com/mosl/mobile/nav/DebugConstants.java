package com.mosl.mobile.nav;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        DebugConstants.NAVIGATE_TO_DASHBOARD, DebugConstants.NAVIGATE_TO_DRAWER_MENU,
        DebugConstants.NAVIGATE_TO_PLAYGROUND_01, DebugConstants.NAVIGATE_TO_PLAYGROUND_02
})
public @interface DebugConstants {
    int NAVIGATE_TO_DASHBOARD = -100;
    int NAVIGATE_TO_DRAWER_MENU = -101;
    int NAVIGATE_TO_PLAYGROUND_01 = -102;
    int NAVIGATE_TO_PLAYGROUND_02 = -103;
    int NAVIGATE_TO_LOGIN = -104;
    int NAVIGATE_TO_OVERVIEW = -105;
    int NAVIGATE_TO_SCHEDULE = -106;
    int NAVIGATE_TO_PANEL = -107;
}
