package com.asthiseta.bismillahtest.galeri


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
import androidx.recyclerview.widget.GridLayoutManager
import com.asthiseta.bismillahtest.R
import com.asthiseta.bismillahtest.databinding.FollowFragmentBinding
import com.asthiseta.bismillahtest.databinding.FragmentGalleryBinding
import com.asthiseta.bismillahtest.databinding.HomeFragmentBinding
import com.asthiseta.bismillahtest.util.ShowState
import com.asthiseta.core.data.Resource
import com.asthiseta.core.ui.GalleryUserAdapter
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ViewModelParameter
import org.koin.android.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier


class GalleryFragment :  Fragment(), ShowState {

    private inline fun<reified VM : ViewModel> Fragment.sharedGraphViewModel(
        @IdRes navGraphId : Int,
        qualifier : Qualifier? = null,
        noinline parameters: ParametersDefinition?=null
    ) = lazy{
        val store = findNavController().getViewModelStoreOwner(navGraphId).viewModelStore
        getKoin().getViewModel(ViewModelParameter(VM::class, qualifier, parameters, store))
    }

    private var _galleryBinding : FragmentGalleryBinding? = null
    private val galleryBinding get() = _galleryBinding!!
    private lateinit var galleryAdapter : GalleryUserAdapter
    private val galleryVM : GalleryViewModel by sharedGraphViewModel(R.id.user_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _galleryBinding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        return galleryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        galleryAdapter = GalleryUserAdapter(arrayListOf()) {username, iv ->
            findNavController().navigate(
                GalleryFragmentDirections.actionGalleryFragmentToDetailFragment(username),
                FragmentNavigatorExtras(iv to username)
            )
        }

        galleryBinding.recyclerGallery.apply {
            layoutManager = GridLayoutManager(context, 3)//GridLayoutManager(context, 3, false)
            adapter = galleryAdapter
        }

        observeHome()
    }

    private fun observeHome() {
        galleryVM.users.observe(viewLifecycleOwner){
            if (it != null) {
                when(it) {
                    is Resource.Success -> {
                        onSuccessState(galleryFragmentBinding = galleryBinding)
                        it.data?.let { data -> galleryAdapter.setData(data) }
                    }
                    is Resource.Loading -> onLoadingState(galleryFragmentBinding = galleryBinding)
                    is Resource.Error -> onErrorState(galleryFragmentBinding = galleryBinding, message = it.message)
                }
            }
        }

    }

    override fun onSuccessState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?
    ) {
        galleryFragmentBinding?.apply {
            errLayout.mainNotFound.visibility = View.GONE
            progress.visibility = View.GONE
            recyclerGallery.visibility = View.VISIBLE
        }
    }

    override fun onLoadingState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?
    ) {
        galleryFragmentBinding?.apply {
            errLayout.mainNotFound.visibility = View.GONE
            progress.visibility = View.VISIBLE
            recyclerGallery.visibility = View.GONE
        }
    }

    override fun onErrorState(
        homeFragmentBinding: HomeFragmentBinding?,
        followFragmentBinding: FollowFragmentBinding?,
        galleryFragmentBinding: FragmentGalleryBinding?,
        message:String?
    ) {
        galleryFragmentBinding?.apply {
            errLayout.apply {
                mainNotFound.visibility = View.VISIBLE
                if (message == null){
                    emptyText.text = resources.getString(R.string.not_found)
                }else{
                    emptyText.text = message
                }
            }
            progress.visibility = View.GONE
            recyclerGallery.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        galleryBinding.recyclerGallery.adapter = null
        _galleryBinding = null
        super.onDestroyView()
    }
}