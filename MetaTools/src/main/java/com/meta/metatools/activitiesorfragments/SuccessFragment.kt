package com.meta.metatools.activitiesorfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.meta.metacomponents.databinding.FragmentMetaSuccessBinding
import com.meta.metatools.models.SuccessFragmentModel

class SuccessFragment : Fragment() {

    private lateinit var _binding: FragmentMetaSuccessBinding
    private val binding get() = _binding
    private lateinit var model: SuccessFragmentModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMetaSuccessBinding.inflate(inflater, container, false)

        val root: View = binding.root

        arguments?.let { bundle ->
            model = bundle.getParcelable<SuccessFragmentModel>("SuccessFragmentModel")!!
            binding.titleSucessTextView.text = model.title

            model.titleColor?.let {
                binding.titleSucessTextView.setTextColor(ContextCompat.getColor(requireContext(), it))
            }

            model.image?.let {
                binding.imageSuccess.visibility = View.VISIBLE
                binding.containerDataSimple.root.visibility = View.GONE
                binding.containerView.background = null
                binding.imageSuccess.load(it)
            }

            binding.understoodButton.text = model.buttonTitle
            binding.understoodButton.setOnClickListener {
                model.action.invoke(this)
            }

            if (!model.buttonLeftTitle.isNullOrEmpty()) {
                binding.leftButton.visibility = View.VISIBLE
                binding.leftButton.text = model.buttonLeftTitle
                binding.leftButton.setOnClickListener {
                    model.actionLeft.invoke(this)
                }
            } else {
                binding.leftButton.visibility = View.GONE
            }

        }

        return root
    }

}