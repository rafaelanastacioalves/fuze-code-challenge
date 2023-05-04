package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting
import MatchListingInteractor
import MainScreen
import MainScreenViewModel
import MainScreenViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.rafaelanastacioalves.fuzecodechallenge.application.MainApplication
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val mMainScreenViewModel: MainScreenViewModel by viewModels<MainScreenViewModel> {
        MainScreenViewModelFactory(MatchListingInteractor((requireActivity().application as MainApplication).appRepository))
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                RafaelanastacioalvesfuzechallengeTheme {
                    MainScreen(mMainScreenViewModel, { detailId -> goToDetailsScreen(detailId) })
                }
            }
        }
    }

    private fun goToDetailsScreen(detailId: Int) {

        val direction =
            HomeFragmentDirections.actionHomeFragmentToEntityDetailsFragment(detailId.toString())
        findNavController().navigate(direction)
    }

    private fun loadData() {
        mMainScreenViewModel.loadDataIfNecessary()
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
