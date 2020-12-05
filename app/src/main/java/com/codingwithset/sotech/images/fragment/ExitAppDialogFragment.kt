package com.codingwithset.sotech.images.fragment


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.codingwithset.sotech.R
import kotlinx.android.synthetic.main.fragment_exit_app_dialog.*

class ExitAppDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.fragment_exit_app_dialog, container, false)


    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
       yes.setOnClickListener {
           dialog?.dismiss()
           requireActivity().finish()
           requireActivity().finishAffinity()
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               requireActivity().finishAndRemoveTask()
           }

       }




        no.setOnClickListener {

            dismiss()
        }


    }

}