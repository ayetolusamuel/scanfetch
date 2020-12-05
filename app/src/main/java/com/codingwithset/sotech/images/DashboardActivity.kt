//package com.codingwithset.sotech.images
//
//import com.codingwithset.sotech.images.fragment.DashBoardAdapter
//import android.cims.chams.com.osun.dashbord.util.DepthPageTransformer
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.codingwithset.sotech.R
//import kotlinx.android.synthetic.main.activity_dashboard.*
//
//class DashboardActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dashboard)
//
//
//
//        val list = listOf(
//            "", "", ""
//
//        )
//        val adapter = DashBoardAdapter.getInstance(supportFragmentManager, list)
//
//        tab_layout.setupWithViewPager(pager, true)
//        pager.adapter = adapter
//
//        pager.setPageTransformer(true, DepthPageTransformer())
//
//    }
//
//    override fun onBackPressed() {
//        if (pager.currentItem == 0) {
//            // If the user is currently looking at the first step, allow the system to handle the
//            // Back button. This calls finish() on this activity and pops the back stack.
//            finish()
//            finishAndRemoveTask()
//            super.onBackPressed()
//        } else {
//            // Otherwise, select the previous step.
//            pager.currentItem = pager.currentItem - 1
//        }
//    }
//}