package com.meta.metatools.activitiesorfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meta.metacomponents.databinding.FragmentMetaSupportBinding

class SupportFragment: Fragment() {

    private lateinit var _binding: FragmentMetaSupportBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMetaSupportBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.buttonWhats.setOnClickListener {
           /* val number = getString(R.string.whats_app_number_intent, getString(R.string.contact_center_number))
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://api.whatsapp.com/send?phone=$number")
            }.also {
                startActivity(it)
            }*/
        }

        binding.buttonPhone.setOnClickListener {

        }

        return root
    }

}