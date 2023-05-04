package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchdetail

import MatchDetail
import MatchDetailInteractor
import MatchDetailViewModel
import MatchDetailViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.rafaelanastacioalves.fuzecodechallenge.application.MainApplication
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme


/**
 * A simple [Fragment] subclass.
 */
class MatchDetailFragment : Fragment(), View.OnClickListener {
    val args: MatchDetailFragmentArgs by navArgs()
    private val mEntityDetailsViewModel: MatchDetailViewModel by viewModels<MatchDetailViewModel> {
        MatchDetailViewModelFactory(MatchDetailInteractor((requireActivity().application as MainApplication).appRepository), args.argentityid ?: "" )
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return ComposeView(requireContext()).apply {
            setContent {
                RafaelanastacioalvesfuzechallengeTheme {
                    MatchDetail(mEntityDetailsViewModel )
                }
            }
        }
    }

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
