package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchdetail

import EntityDetailsCompose
import EntityDetailsInteractor
import MatchDetailViewModel
import MatchDetailViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rafaelanastacioalves.fuzecodechallenge.application.MainApplication
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme


/**
 * A simple [Fragment] subclass.
 */
class EntityDetailsFragment : Fragment(), View.OnClickListener {
    private val mEntityDetailsViewModel: MatchDetailViewModel by viewModels<MatchDetailViewModel> {
        MatchDetailViewModelFactory(EntityDetailsInteractor((requireActivity().application as MainApplication).appRepository))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    private fun loadData() {
        val mEntityId = requireArguments().getString(ARG_ENTITY_ID)
        mEntityDetailsViewModel.loadData(mEntityId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                RafaelanastacioalvesfuzechallengeTheme {
                    EntityDetailsCompose(mEntityDetailsViewModel)
                }
            }
        }
    }


    private fun setupActionBarWithTitle(title: String) {
        val mActivity = activity as AppCompatActivity?
        val actionBar = mActivity!!.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title


        }
    }

//    private fun setViewsWith(entityDetails: EntityDetails?) {

//        detail_entity_detail_name!!.text = entityDetails?.price
//        setupActionBarWithTitle(entityDetails?.title ?: "")
//        val placeHolder: StateListDrawable = requireContext().resources.getDrawable(R.drawable.ic_placeholder_map_selector) as StateListDrawable;
//
//        Picasso.get()
//                .load(entityDetails?.imageUrl)
//                .placeholder(placeHolder)
//                .into(entity_detail_imageview, object : Callback {
//                    override fun onSuccess() {
//                        activity!!.supportStartPostponedEnterTransition()
//                    }
//
//                    override fun onError(e: Exception) {
//
//                    }
//                })


//    }


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
