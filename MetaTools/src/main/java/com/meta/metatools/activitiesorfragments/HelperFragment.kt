package com.meta.metatools.activitiesorfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.meta.metacomponents.databinding.FragmentMetaHelperBinding
import com.meta.metatools.models.HelperFragmentModel

class HelperFragment: Fragment() {

    private lateinit var _binding: FragmentMetaHelperBinding
    private val binding get() = _binding
    private lateinit var model: HelperFragmentModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMetaHelperBinding.inflate(inflater, container, false)

        val root: View = binding.root

        arguments?.let { bundle ->
            model = bundle.getParcelable<HelperFragmentModel>("HelperFragmentModel")!!
            binding.titleHelperTextView.text= model.title
            binding.subtitleHelperTextView.text= model.subtitle

            model.titleColor?.let {
                binding.titleHelperTextView.setTextColor(ContextCompat.getColor(requireContext(), it))
            }

            model.image?.let {
                binding.imageHelper.visibility = View.VISIBLE
                binding.containerDataSimple.root.visibility = View.GONE
                binding.imageHelper.load(it)
            }

            binding.understoodButton.text = model.buttonTitle
            binding.understoodButton.setOnClickListener {
                model.action.invoke(this)
            }

        }

        return root
    }

}