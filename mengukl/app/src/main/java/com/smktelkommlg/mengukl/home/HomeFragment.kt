package com.asthiseta.bismillahtest.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkelkommlg.mengukl.home.HomeFragmentDirections
import com.smktelkommlg.cores.adapter.UserAdapter
import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.mengukl.R
import com.smktelkommlg.mengukl.databinding.FollowFragmentBinding
import com.smktelkommlg.mengukl.databinding.FragmentGalleryBinding
import com.smktelkommlg.mengukl.databinding.HomeFragmentBinding
import com.smktelkommlg.mengukl.home.HomeViewModel
import com.smktelkommlg.mengukl.util.ShowState
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ViewModelParameter
import org.koin.android.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

class HomeFragment : Fragment(), ShowState {

    private inline fun<reified VM : ViewModel> Fragment.sharedGraphViewModel(
        @IdRes navGraphId : Int,
        qualifier : Qualifier? = null,
        noinline parameters: ParametersDefinition?=null
    ) = lazy{
        val store = findNavController().getViewModelStoreOwner(navGraphId).viewModelStore
        getKoin().getViewModel(ViewModelParameter(VM::class, qualifier, parameters, store))
    }

    private var _homeBinding : HomeFragmentBinding? = null
    private val homeBinding get() = _homeBinding!!
    private lateinit var homeAdapter : UserAdapter
    private val homeVM : HomeViewModel by sharedGraphViewModel(R.id.user_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar


        _homeBinding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)


        homeAdapter = UserAdapter(arrayListOf()) {username, iv ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(username),
                FragmentNavigatorExtras(iv to username)
            )
        }

        homeBinding.recyclerHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }

        observeHome()
    }

    private fun observeHome() {
        homeVM.users.observe(viewLifecycleOwner){
            if (it != null) {
                when(it) {
                    is Resource.Success -> {
                        onSuccessState(homeFragmentBinding = homeBinding)
                        it.data?.let { data -> homeAdapter.setData(data) }
                    }
                    is Resource.Loading -> onLoadingState(homeFragmentBinding = homeBinding)
                    is Resource.Error -> onErrorState(homeFragmentBinding = homeBinding, message = it.message)
                }
            }
        }

    }

    override fun onSuccessState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?
    ) {
        homeFragmentBinding?.apply {
            errLayout.mainNotFound.visibility = View.GONE
            progress.visibility = View.GONE
            recyclerHome.visibility = View.VISIBLE
        }
    }

    override fun onLoadingState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?
    ) {
        homeFragmentBinding?.apply {
            errLayout.mainNotFound.visibility = View.GONE
            progress.visibility = View.VISIBLE
            recyclerHome.visibility = View.GONE
        }
    }

    override fun onErrorState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?,
        message:String?
    ) {
        homeFragmentBinding?.apply {
            errLayout.apply {
                mainNotFound.visibility = View.VISIBLE
                if (message == null){
                    emptyText.text = resources.getString(R.string.not_found)
                }else{
                    emptyText.text = message
                }
            }
            progress.visibility = View.GONE
            recyclerHome.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        homeBinding.recyclerHome.adapter = null
        _homeBinding = null
        super.onDestroyView()
    }
}