package com.techak.screenlocker;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.accessibility.AccessibilityManager;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private ComponentName componentName;
    private DevicePolicyManager devicePolicyManager;
    private AccessibilityManager accessibilityManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        componentName = new ComponentName(this, AdminReceiver.class);
        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);

        if (devicePolicyManager.isAdminActive(componentName)) {
            devicePolicyManager.lockNow();
            finish();
        } else {
            registerAsAdmin();
    }

    }

    private void registerAsAdmin() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, getString(R.string.explaination_device_admin_permission));
        startActivity(intent);
        finish();
    }


}
