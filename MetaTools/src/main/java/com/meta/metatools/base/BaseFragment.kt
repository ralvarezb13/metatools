package com.meta.metatools.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.meta.metacomponents.databinding.FragmentMetaProgressBinding
import com.meta.metatools.models.ProgressModel

abstract class BaseFragment<VDB : ViewDataBinding,
        INTENT : ViewIntent,
        STATE : ViewState,
        VM : BaseViewModel<INTENT, STATE>> : Fragment(), SetUpView<VM, STATE> {

    var binding: VDB? = null
    private var hasInitializedRootView = false
    private var rootView: View? = null

    private lateinit var viewModel: VM
    private var progress: Dialog? = null

    override fun onResume() {
        super.onResume()
        registerEvents()
    }

    private fun getPersistentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(getModelClass().kotlin.java)
        if (rootView == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
            rootView = binding?.root
        } else {
            (rootView?.parent as? ViewGroup)?.removeView(rootView)
        }
        return rootView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = getPersistentView(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Navigation.findNavController(view)

        hasInitializedRootView = true
        initialize()
        registerEvents()

        viewModel.state.observe(viewLifecycleOwner, {
            render(it)
        })

    }

    fun dispatchIntent(viewIntent: INTENT) {
        viewModel.dispatchIntent(viewIntent)
    }

    fun initState() {
        viewModel.initMutable()
    }

    fun showProgress(model: ProgressModel? = null) {
        progress = Dialog(requireActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        val binding: FragmentMetaProgressBinding = FragmentMetaProgressBinding.inflate(
            LayoutInflater.from(context)
        )
        if (model != null) {
            binding.titleProgressTextView.text = model.title
            model.image?.let {
                binding.imageProgress.load(it)
                binding.containerDataSimple.root.visibility = View.GONE
                binding.containerView. background = null
                binding.imageProgress.visibility = View.VISIBLE
            }
        }
        binding.let { progress?.setContentView(it.root) }
        progress?.show()

    }

    fun hideProgress() {
        if (progress != null) {
            progress?.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (progress != null) {
            progress?.dismiss()
        }

    }


}