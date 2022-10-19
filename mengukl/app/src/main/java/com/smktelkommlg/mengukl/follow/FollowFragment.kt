package com.asthiseta.bismillahtest.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asthiseta.bismillahtest.R
import com.asthiseta.bismillahtest.databinding.FollowFragmentBinding
import com.asthiseta.bismillahtest.databinding.FragmentGalleryBinding
import com.asthiseta.bismillahtest.databinding.HomeFragmentBinding
import com.asthiseta.bismillahtest.util.ShowState
import com.asthiseta.bismillahtest.util.TypeView
import com.asthiseta.core.data.Resource
import com.asthiseta.core.ui.UserAdapter
import com.shashank.sony.fancytoastlib.FancyToast
import com.smktelkommlg.mengukl.follow.FollowViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FollowFragment : Fragment() , ShowState{

    private var _followBinding : FollowFragmentBinding? = null
    private val followBinding get() = _followBinding!!

    private lateinit var followAdapter: UserAdapter
    private lateinit var username: String

    private var type: String? = null
    private val followViewModel: FollowViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME).toString()
            type = it.getString(TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _followBinding = FollowFragmentBinding.inflate(layoutInflater, container, false)
        followBinding.lifecycleOwner = viewLifecycleOwner

        return followBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followAdapter = UserAdapter(arrayListOf()) { user, _ ->
            FancyToast.makeText(context, user, FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show()
        }

        followBinding.recylerFollow.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = followAdapter
        }

        when(type) {
            resources.getString(R.string.following) ->{
                followViewModel.setFollow(username, TypeView.FOLLOWING)
                FancyToast.makeText(context, type, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
            }
            resources.getString(R.string.followers) ->{
                followViewModel.setFollow(username, TypeView.FOLLOWER)
                FancyToast.makeText(context, type, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
            }
            else -> onErrorState(followFragmentBinding = followBinding, message = null)
        }
        observeFollow()
    }

    private fun observeFollow() {
        followViewModel.favoriteUsers.observe(viewLifecycleOwner){
            it.let {
                when(it){
                    is Resource.Success ->
                        if (!it.data.isNullOrEmpty()) {
                            onSuccessState(followFragmentBinding = followBinding)
                            followAdapter.run { setData(it.data) }
                        } else {
                            onErrorState(followFragmentBinding = followBinding, message =  resources.getString(R.string.not_have, username, type))
                        }
                    is Resource.Loading -> onLoadingState(followFragmentBinding = followBinding)
                    is Resource.Error -> onErrorState(followFragmentBinding = followBinding, message = it.message)
                }
            }
        }
    }

    override fun onSuccessState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?
    ) {
        followFragmentBinding?.apply {
            errLayout.mainNotFound.visibility = View.GONE
            progress.visibility = View.GONE
            recylerFollow.visibility = View.VISIBLE
        }
    }

    override fun onLoadingState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?
    ) {
        followFragmentBinding?.apply {
            errLayout.mainNotFound.visibility = View.GONE
            progress.visibility = View.VISIBLE
            recylerFollow.visibility = View.GONE
        }
    }

    override fun onErrorState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?,
        message: String?
    ) {
        followFragmentBinding?.apply {
            errLayout.apply {
                mainNotFound.visibility = View.VISIBLE
                emptyText.text = message ?: resources.getString(R.string.not_found)
            }
            progress.visibility = View.GONE
            recylerFollow.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        followBinding.recylerFollow.adapter = null
        _followBinding = null
        super.onDestroyView()
    }

    companion object{
        fun newInstance(username: String, type: String) =
            FollowFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                    putString(TYPE, type)
                }
            }

        private const val USERNAME ="username"
        private const val TYPE = "type"
    }
}