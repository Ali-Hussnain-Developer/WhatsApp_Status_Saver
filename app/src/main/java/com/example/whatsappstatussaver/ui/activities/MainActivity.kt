package com.example.whatsappstatussaver.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.whatsappstatussaver.R
import com.example.whatsappstatussaver.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


open class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accessPermission()
        val window = this.window
        window.statusBarColor = Color.parseColor(getString(R.color.blue))
    }

    private fun accessPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (!report.areAllPermissionsGranted()) {
                        accessPermission()
                    } else {
                        val viewPager: ViewPager = findViewById(R.id.viewPager)
                        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
                        val viewPagerAdapter = ViewPagerAdapter(getSupportFragmentManager())
                        viewPager.setAdapter(viewPagerAdapter)
                        tabLayout.setupWithViewPager(viewPager)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?,
                ) {
                    token?.continuePermissionRequest()

                }
            }).check()

    }

}