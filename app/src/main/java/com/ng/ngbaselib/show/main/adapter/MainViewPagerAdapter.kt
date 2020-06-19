package com.ng.ngbaselib.show.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ng.ngbaselib.show.main.bean.ItemInfo

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/19
 */

class MainViewPagerAdapter(fm: FragmentManager?, infoLost: List<ItemInfo>?) : FragmentPagerAdapter(fm!!) {
    private val infoLost: List<ItemInfo>? = infoLost

    /**
     * 得到每个页面
     */
    override fun getItem(arg0: Int): Fragment {
        return (if (infoLost == null || infoLost.isEmpty()) null else infoLost[arg0].fragment)!!
    }

    /**
     * 每个页面的title
     */
    override fun getPageTitle(position: Int): CharSequence {
        return if (infoLost!!.size > position) infoLost[position].name else ""
    }

    /**
     * 页面的总个数
     */
    override fun getCount(): Int {
        return infoLost?.size ?: 0
    }

}
