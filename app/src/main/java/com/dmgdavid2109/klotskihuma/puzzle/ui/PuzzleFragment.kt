package com.dmgdavid2109.klotskihuma.puzzle.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dmgdavid2109.klotskihuma.R
import com.dmgdavid2109.klotskihuma.databinding.PuzzleFragmentBinding
import com.dmgdavid2109.klotskihuma.di.ViewModelFactory
import com.dmgdavid2109.klotskihuma.puzzle.common.ui.viewBinding
import java.lang.Exception

import javax.inject.Inject

class PuzzleFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory<PuzzleViewModel>
) : Fragment((R.layout.puzzle_fragment)) {

    private val binding by viewBinding(PuzzleFragmentBinding::bind)
    private val viewModel: PuzzleViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()

        binding.solveBoard.setOnClickListener {
            viewModel.solveBoard()
        }
    }

    private fun bindView() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            try {
                with(viewState) {
                    if (showProgressBar) {
                        binding.progressBar.visibility = VISIBLE
                    } else {
                        binding.progressBar.visibility = GONE
                    }

                    binding.position00.text = array[0][0].toString()
                    binding.position01.text = array[0][1].toString()
                    binding.position02.text = array[0][2].toString()
                    binding.position03.text = array[0][3].toString()
                    binding.position04.text = array[0][4].toString()
                    binding.position05.text = array[0][5].toString()

                    binding.position10.text = array[1][0].toString()
                    binding.position11.text = array[1][1].toString()
                    binding.position12.text = array[1][2].toString()
                    binding.position13.text = array[1][3].toString()
                    binding.position14.text = array[1][4].toString()
                    binding.position15.text = array[1][5].toString()

                    binding.position20.text = array[2][0].toString()
                    binding.position21.text = array[2][1].toString()
                    binding.position22.text = array[2][2].toString()
                    binding.position23.text = array[2][3].toString()
                    binding.position24.text = array[2][4].toString()
                    binding.position25.text = array[2][5].toString()

                    binding.position30.text = array[3][0].toString()
                    binding.position31.text = array[3][1].toString()
                    binding.position32.text = array[3][2].toString()
                    binding.position33.text = array[3][3].toString()
                    binding.position34.text = array[3][4].toString()
                    binding.position35.text = array[3][5].toString()

                    binding.position40.text = array[4][0].toString()
                    binding.position41.text = array[4][1].toString()
                    binding.position42.text = array[4][2].toString()
                    binding.position43.text = array[4][3].toString()
                    binding.position44.text = array[4][4].toString()
                    binding.position45.text = array[4][5].toString()

                    binding.position50.text = array[5][0].toString()
                    binding.position51.text = array[5][1].toString()
                    binding.position52.text = array[5][2].toString()
                    binding.position53.text = array[5][3].toString()
                    binding.position54.text = array[5][4].toString()
                    binding.position55.text = array[5][5].toString()

                    binding.position60.text = array[6][0].toString()
                    binding.position61.text = array[6][1].toString()
                    binding.position62.text = array[6][2].toString()
                    binding.position63.text = array[6][3].toString()
                    binding.position64.text = array[6][4].toString()
                    binding.position65.text = array[6][5].toString()

                    binding.solveBoard.isEnabled = buttonEnabled
                }

            } catch (exception: Exception) {
            }
        })
    }

}
