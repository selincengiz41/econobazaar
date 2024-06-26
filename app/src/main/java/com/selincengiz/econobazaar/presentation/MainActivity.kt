package com.selincengiz.econobazaar.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.core.common.RemoteConfigManager
import com.selincengiz.econobazaar.R
import com.selincengiz.econobazaar.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        RemoteConfigManager.getBackgroundColor()
        navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT)
        observe()
        navManagement()

        with(binding) {
            home.setOnClickListener(this@MainActivity)
            categories.setOnClickListener(this@MainActivity)
            search.setOnClickListener(this@MainActivity)
            favorite.setOnClickListener(this@MainActivity)
            cart.setOnClickListener(this@MainActivity)
            profile.setOnClickListener(this@MainActivity)
            logout.setOnClickListener(this@MainActivity)
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.startDestination.collectLatest { start ->
                Log.i("start",start.toString())
                navHostFragment.navController.popBackStack(R.id.nav_graph, true)
                navHostFragment.navController.navigate(start)
            }
        }
    }

    private fun navManagement() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.label.toString()) {
                "fragment_onboarding" -> {
                    binding.drawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                        binding.navView
                    )

                }

                "fragment_login" -> {
                    binding.drawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                        binding.navView
                    )

                }

                else -> {
                    binding.drawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_UNLOCKED,
                        binding.navView
                    )
                }
            }
            binding.drawerLayout.invalidate()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.home -> {
                navHostFragment.navController.navigate(com.selincengiz.home.presentation.R.id.home_nav_graph)
            }

            R.id.categories -> {
                navHostFragment.navController.navigate(com.selincengiz.categories.presentation.R.id.categories_nav_graph)
            }

            R.id.search -> {
                navHostFragment.navController.navigate(com.selincengiz.search.presentation.R.id.search_nav_graph)
            }

            R.id.favorite -> {
                navHostFragment.navController.navigate(com.selincengiz.favorite.presentation.R.id.favorite_nav_graph)
            }

            R.id.cart -> {
                val bundle = bundleOf("Id" to auth.currentUser!!.photoUrl.toString().toInt())
                navHostFragment.navController.navigate(
                    com.selincengiz.cart.presentation.R.id.cart_nav_graph,
                    bundle
                )
            }

            R.id.profile -> {
                val bundle = bundleOf("Id" to auth.currentUser!!.photoUrl.toString().toInt())
                navHostFragment.navController.navigate(
                    com.selincengiz.profile.presentation.R.id.profile_nav_graph,
                    bundle
                )
            }

            R.id.logout -> {
                viewModel.signOut()
                navHostFragment.navController.popBackStack(R.id.nav_graph, true)
                navHostFragment.navController.navigate(com.selincengiz.login.presentation.R.id.login_nav)
            }

        }
        binding.drawerLayout.close()
    }
}