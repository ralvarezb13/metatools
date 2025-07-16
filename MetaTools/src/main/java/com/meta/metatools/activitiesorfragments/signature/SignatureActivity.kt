package com.meta.metatools.activitiesorfragments.signature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meta.metatools.R

const val SIGNATURE_CODE = 1001

class SignatureActivity : AppCompatActivity(), SignatureAction {

    private lateinit var signatureFragment: SignatureFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meta_signature)
        signatureFragment = SignatureFragment()
        signatureFragment.signatureAction = this
        supportFragmentManager.beginTransaction().replace(
            R.id.signature_container,
            signatureFragment,
            SignatureFragment::class.java.simpleName
        ).commit()
    }

    override fun getSignature(output: String?) {
        setResult(SIGNATURE_CODE, Intent().apply {
            putExtra(SIGNATURE_PARAM, output)
        })
        finish()
    }
}

interface SignatureAction {
    fun getSignature(output: String?)
}