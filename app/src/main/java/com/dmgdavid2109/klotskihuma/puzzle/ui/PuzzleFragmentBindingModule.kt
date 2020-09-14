package com.dmgdavid2109.klotskihuma.puzzle.ui

import androidx.fragment.app.Fragment
import com.dmgdavid2109.klotskihuma.di.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class PuzzleFragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(PuzzleFragment::class)
    abstract fun bindListFragment(mainFragment: PuzzleFragment): Fragment
}
