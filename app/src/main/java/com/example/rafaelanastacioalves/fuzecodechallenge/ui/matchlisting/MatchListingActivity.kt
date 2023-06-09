package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting

import MainScreenViewModelFactory
import MatchDetailInteractor
import MatchListingInteractor
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import com.example.rafaelanastacioalves.fuzecodechallenge.R
import com.example.rafaelanastacioalves.fuzecodechallenge.application.MainApplication

class MatchListingActivity : FragmentActivity(), HasDefaultViewModelProviderFactory {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = MainScreenViewModelFactory(
            MatchListingInteractor((this.application as MainApplication).appRepository),
            MatchDetailInteractor((this.application as MainApplication).appRepository)
        )

}


