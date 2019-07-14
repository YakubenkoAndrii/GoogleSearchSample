package by.search.app.googlesearch.common.utils

import android.support.v7.app.AppCompatActivity
import by.search.app.googlesearch.R
import by.search.app.googlesearch.common.baseclasses.BaseFragment

enum class FragmentAppearance {
    NONE,
    FROM_LEFT,
    FROM_RIGHT
}

class FragmentHelper(private val activity: AppCompatActivity, val container: Int) {

    fun replaceWithBackStack(fragment: BaseFragment, direction: FragmentAppearance) {
        changeFragment(fragment, direction, true, true)
    }

    fun replaceWithoutBackStack(fragment: BaseFragment,
                                direction: FragmentAppearance) {
        changeFragment(fragment, direction, true, false)
    }

    fun addWithBackStack(fragment: BaseFragment, direction: FragmentAppearance) {
        changeFragment(fragment, direction, false, true)
    }

    fun addWithoutBackStack(fragment: BaseFragment, direction: FragmentAppearance) {
        changeFragment(fragment, direction, false, false)
    }

    private fun changeFragment(fragment: BaseFragment,
                               direction: FragmentAppearance,
                               needToReplace: Boolean,
                               needToAddToBackStack: Boolean) {
        activity.supportFragmentManager
            .beginTransaction()
            .apply {
                if (direction == FragmentAppearance.FROM_RIGHT) {
                    setCustomAnimations(
                        R.anim.slide_right_enter, R.anim.slide_left_exit,
                        R.anim.slide_left_enter, R.anim.slide_right_exit)
                } else if (direction == FragmentAppearance.FROM_LEFT) {
                    setCustomAnimations(R.anim.slide_left_enter, R.anim.slide_right_exit,
                        R.anim.slide_right_enter, R.anim.slide_left_exit)
                }
            }
            .apply {
                if (needToReplace)
                    replace(container, fragment, fragment::class.java.simpleName)
                else
                    add(container, fragment, fragment::class.java.simpleName)
            }
            .apply {
                if (needToAddToBackStack)
                    addToBackStack(fragment::class.java.simpleName)
            }
            .commitAllowingStateLoss()
    }

}