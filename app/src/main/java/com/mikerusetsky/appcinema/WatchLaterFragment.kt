package com.mikerusetsky.appcinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mikerusetsky.appcinema.databinding.FragmentWatchlaterBinding

class WatchLaterFragment : Fragment() {
    private lateinit var binding3: FragmentWatchlaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watchlater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(binding3.watchLaterFragmentRoot, requireActivity(),3)
    }
}