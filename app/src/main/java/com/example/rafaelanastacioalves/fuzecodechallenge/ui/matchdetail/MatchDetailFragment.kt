package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchdetail

import MainScreenViewModel
import MatchDetail
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme


/**
 * A simple [Fragment] subclass.
 */
class MatchDetailFragment : Fragment(), View.OnClickListener {
    private val mMainScreenViewModel: MainScreenViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mMainScreenViewModel.loadData()
        return ComposeView(requireContext()).apply {
            setContent {
                RafaelanastacioalvesfuzechallengeTheme {
                    MatchDetail(mMainScreenViewModel) { goBack() }
                }
            }
        }
    }

    private fun goBack(): Unit  { findNavController().navigateUp() }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onClick(v: View) {
        Toast.makeText(activity, "Comprado!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        var ARG_ENTITY_ID: String? = null
    }


}// Required empty public constructor
