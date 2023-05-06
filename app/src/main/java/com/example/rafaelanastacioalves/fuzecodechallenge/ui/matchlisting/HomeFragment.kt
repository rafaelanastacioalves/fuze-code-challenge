package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting

import MainScreen
import MainScreenViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val mMainScreenViewModel: MainScreenViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                RafaelanastacioalvesfuzechallengeTheme(darkTheme = true, dynamicColor = false) {
                    MainScreen(mMainScreenViewModel, { detailId -> goToDetailsScreen(detailId) })
                }
            }
        }
    }

    private fun goToDetailsScreen(match: Match) {
        mMainScreenViewModel.setSelectedMatch(match)
        val direction =
            HomeFragmentDirections.actionHomeFragmentToEntityDetailsFragment()
        findNavController().navigate(direction)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment()
    }
}
