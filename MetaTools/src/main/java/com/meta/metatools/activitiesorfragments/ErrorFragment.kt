package com.meta.metatools.activitiesorfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.meta.metacomponents.databinding.FragmentMetaErrorBinding
import com.meta.metatools.models.ErrorFragmentModel

class ErrorFragment:  Fragment() {

    private lateinit var _binding: FragmentMetaErrorBinding
    private val binding get() = _binding
    private lateinit var model: ErrorFragmentModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMetaErrorBinding.inflate(inflater, container, false)

        val root: View = binding.root

        arguments?.let { bundle ->
            model = bundle.getParcelable<ErrorFragmentModel>("ErrorFragmentModel")!!
            binding.titleErrorTextView.text= model.title

            model.titleColor?.let {
                binding.titleErrorTextView.setTextColor(ContextCompat.getColor(requireContext(), it))
            }

            model.image?.let {
                binding.imageError.visibility = View.VISIBLE
                binding.containerDataSimple.root.visibility = View.GONE
                binding.imageError.load(it)
            }

            binding.understoodButton.text = model.buttonTitleAction
            binding.otherButton.text = model.buttonTitleSecondAction

            binding.understoodButton.setOnClickListener {
                model.action.invoke(this)
            }

            binding.otherButton.setOnClickListener {
                model.secondAction.invoke(this)
            }

        }

        return root
    }

}