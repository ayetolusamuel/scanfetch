package com.codingwithset.sotech.images.fragment

import android.cims.chams.com.osun.dashbord.fragment.Fragment3
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class DashBoardAdapter(fm: FragmentManager, private val list: List<String>) :
    FragmentStatePagerAdapter(fm, list.size) {
  private  var registeredFragments = SparseArray<Fragment>()


    companion object {
        @Volatile
        @JvmStatic
        private var INSTANCE: DashBoardAdapter? = null

        @JvmStatic
        fun getInstance(fm: FragmentManager,list: List<String>)
        : DashBoardAdapter = INSTANCE ?: synchronized(this) {
            INSTANCE ?: DashBoardAdapter(fm,list)
    }
    }


    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
       // return super.instantiateItem(container, position)
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)

    }

    override fun getItem(position: Int): Fragment{
        return when (position) {
            0 -> {
               Fragment1(list[0])
            }
            1 -> {
               Fragment2(list[1])
            }
            2 -> {
                Fragment3(list[2])
            }

            else -> null!!
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val list = listOf("","","")
        return list[position]

    }

}

