package com.smktelkommlg.mengukl.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.navArgs
import com.smktelkommlg.cores.data.Resource
import com.smktelkommlg.mengukl.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentDetail : Fragment() {
    private var bindingDetail : FragmentDetailBinding? = null
    private lateinit var item : Item

    private val args : FragmentDetailArgs by navArgs()
    private val detailVM : DetailVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = args.name
        bindingDetail = FragmentDetailBinding.inflate(layoutInflater, container, false)

        return bindingDetail!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingDetail?.lifecycleOwner = viewLifecycleOwner
        observeDetail()
    }

    private fun observeDetail() {
        detailVM.detailItem(args.name).observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success ->{
                    item = it.data!!

                    bindingDetail?.data  = it.data

                    }

                is Resource.Error -> {
                    bindingDetail?.data = null
                }

                is Resource.Loading -> {
                    bindingDetail?.data = null
                }
            }
        }
    }
}