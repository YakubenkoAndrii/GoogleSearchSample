package by.search.app.googlesearch.presentation.search

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import by.search.app.googlesearch.R
import by.search.app.googlesearch.common.utils.FragmentAppearance
import by.search.app.googlesearch.common.utils.FragmentHelper
import by.search.app.googlesearch.presentation.search.searchfragment.SearchFragment
import by.search.app.googlesearch.presentation.search.searchfragment.SearchRouter

class SearchActivity : AppCompatActivity(), SearchRouter {

    private var isTablet = false
    private var isPortraitOrientation = true
    private lateinit var fragmentHelper: FragmentHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentHelper = FragmentHelper(this, R.id.flFragmentContainer)
        isTablet = resources.getBoolean(R.bool.isTablet)

        toSearchFragment(isTablet, isPortraitOrientation)

    }

    private fun toSearchFragment(isTablet: Boolean, isPortraitOrientation: Boolean) {
        fragmentHelper.replaceWithoutBackStack(
            SearchFragment.newInstance(
                isTablet,
                isPortraitOrientation
            ),
            FragmentAppearance.FROM_RIGHT
        )
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        isPortraitOrientation = newConfig?.orientation != Configuration.ORIENTATION_LANDSCAPE
        toSearchFragment(isTablet, isPortraitOrientation)
        super.onConfigurationChanged(newConfig)
    }

    override fun toBrowser(descriptionUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(descriptionUrl)
        startActivity(intent)
    }
}

