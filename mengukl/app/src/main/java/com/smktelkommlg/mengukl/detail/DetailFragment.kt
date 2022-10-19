package com.smktelkommlg.mengukl.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asthiseta.bismillahtest.follow.FollowFragment

import com.google.android.material.tabs.TabLayoutMediator
import com.smkelkommlg.mengukl.detail.DetailFragmentArgs
import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.mengukl.R
import com.smktelkommlg.mengukl.databinding.DetailFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {
    private var _detailBinding : DetailFragmentBinding? = null
    private val detailBinding get() =_detailBinding!!
    private lateinit var pagerAdapter: PagerAdapter
    private val args: DetailFragmentArgs by navArgs()
    private val detailVM : DetailViewModel by viewModel()


    inner class PagerAdapter(
        private val tabList: Array<String>,
        private val username: String,
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = tabList.size

        override fun createFragment(position: Int): Fragment =
            FollowFragment.newInstance(username, tabList[position])
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = args.username

        _detailBinding = DetailFragmentBinding.inflate(layoutInflater, container, false)
        detailBinding.lifecycleOwner = viewLifecycleOwner
        val view = detailBinding.root
        observeDetail()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tablist = arrayOf(resources.getString(R.string.followers), resources.getString(R.string.following))

        pagerAdapter = PagerAdapter(tablist, args.username, this)
        detailBinding.pager.adapter = pagerAdapter
        detailBinding.tabs.let {
            TabLayoutMediator(it, detailBinding.pager){tab, position ->
                tab.text = tablist[position]
            }.attach()
        }

    }

    private fun observeDetail() {
        detailVM.detailUsers(args.username).observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    //user = it.data!!
                    detailBinding.apply {
                        data = it.data
                        content.visibility = View.VISIBLE
                    }
                }
                else -> {
                    detailBinding.content.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        _detailBinding = null
        super.onDestroyView()
    }
}