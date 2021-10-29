package com.veldan.libgdxwebview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.veldan.libgdxwebview.databinding.ActivityMainBinding
import com.veldan.libgdxwebview.fragments.webViewFragment

lateinit var activityMain: Activity private set
lateinit var navController: NavController private set
lateinit var activityMainBinding: ActivityMainBinding private set

class AndroidLauncher : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMain = this
        navController = findNavController(R.id.nav_host_fragment)
        setStartDestination(navController, R.id.webViewFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webViewFragment.onActivityResult(requestCode, resultCode, data)
    }

    override fun exit() {}

    private fun setStartDestination(
        navController: NavController,
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        navController.apply {
            val graph = navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = destinationId
            }
            setGraph(graph, args)
        }
    }

}


fun log(message: String) {
    Log.i("VLAD", message)
}