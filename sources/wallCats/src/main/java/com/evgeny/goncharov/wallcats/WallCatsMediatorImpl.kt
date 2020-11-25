package com.evgeny.goncharov.wallcats

import androidx.fragment.app.FragmentManager
import com.evgeny.goncharov.coreapi.base.BaseFragment
import com.evgeny.goncharov.coreapi.mediators.WallCatsMediator
import com.evgeny.goncharov.wallcats.ui.CatDescriptionFragment
import com.evgeny.goncharov.wallcats.ui.WallCatsFragment
import javax.inject.Inject

/**
 * Реализация логики перехода на экран стены котов и на экран описание кота
 */
class WallCatsMediatorImpl @Inject constructor() : WallCatsMediator {

    override fun goToTheWallCatsFragment(supportFragmentManager: FragmentManager) {
        val fragment = WallCatsFragment.getInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frmRootField, fragment, WallCatsFragment::class.java.name)
            .commitNowAllowingStateLoss()
    }

    override fun goToTheScreenCatDescription(id: String, supportFragmentManager: FragmentManager) {
        val fragment = CatDescriptionFragment.getInstance(id)
        supportFragmentManager.beginTransaction()
            .hide(
                supportFragmentManager.fragments.findLast {
                    it is BaseFragment
                }!!
            )
            .add(R.id.frmRootField, fragment, CatDescriptionFragment::class.java.name)
            .addToBackStack(CatDescriptionFragment::class.java.name)
            .commit()
    }
}