package com.lee.mymvvmsample.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.BaseActivity
import com.lee.mymvvmsample.common.addFragment
import com.lee.mymvvmsample.databinding.ActivityMainBinding
import com.lee.mymvvmsample.ui.main.gallery.GalleryFragment
import com.lee.mymvvmsample.ui.main.home.HomeFragment
import com.lee.mymvvmsample.ui.main.share.ShareFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    private var mCloseApp: Boolean = false

    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setContentView(mBinding?.root)

        mBinding?.navView?.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            addFragment(HomeFragment(), "HomeFragment")
        }

        setEvent()
    }

    private fun setEvent() {
        viewModel.loginResultEvent.observe(this, Observer {
            when (it) {
                is MainViewModel.LoginResult.Success -> {
                    Timber.e("LoginResult.Success")
                }
                is MainViewModel.LoginResult.Update -> {
                    Timber.e("LoginResult.Update ${it.newVersion}")
                }
                is MainViewModel.LoginResult.NetworkError -> {
                    Timber.e("LoginResult.NetworkError")
                }
                is MainViewModel.LoginResult.ServerError -> {
                    Timber.e("LoginResult.ServerError ${it.errorMsg}")
                }
                else -> {
                    Timber.e("LoginResult.Success")
                }
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_gallery -> {
                addFragment(GalleryFragment(), "GalleryFragment")
            }
            R.id.nav_share -> {
                addFragment(ShareFragment(), "ShareFragment")
            }
        }

        mBinding?.drawerLayout?.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (!mCloseApp) {
            mCloseApp = true
            Toast.makeText(this, "종료하시려면 한번 더 누르세요.", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                mCloseApp = false
            }, 1000)
            
        } else {
            finishActivity()
        }
    }
}
