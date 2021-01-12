package com.ng.ngbaselib.show

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.ng.ngbaselib.BaseActivity
import com.ng.ngbaselib.BaseViewModel
import com.ng.ngbaselib.R
import com.ng.ngbaselib.ViewModelFactory
import com.ng.ngbaselib.databinding.ActivityMainVpBinding
import com.ng.ngbaselib.show.frag.DemoFragment
import com.ng.ngbaselib.show.frag.PermissionFrag
import com.ng.ngbaselib.show.frag.http.HttpFragment
import com.ng.ngbaselib.show.main.adapter.LeftListAdapter
import com.ng.ngbaselib.show.main.adapter.MainViewPagerAdapter
import com.ng.ngbaselib.show.main.bean.ItemInfo
import com.ng.ngbaselib.show.viewmodel.HomeViewModel
import com.ng.ngbaselib.utils.AppUtils


/**
 * @author Jzn
 * @date 2020-04-11
 */
class MainActivity : BaseActivity<HomeViewModel, ActivityMainVpBinding>() {

    private var itemInfoList = ArrayList<ItemInfo>()
    private var myViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, itemInfoList)
    private lateinit var mAdapter: LeftListAdapter

    override fun createViewBinding(): ActivityMainVpBinding? =
        ActivityMainVpBinding.inflate(layoutInflater)

    override fun createViewModel(): HomeViewModel? =
        ViewModelProvider(this, ViewModelFactory()).get(HomeViewModel::class.java)

    private val mFragName = arrayListOf(
        "网络请求",
        "权限判断",
        "rv多布局",
        "tab按钮",
        "viewpager",
        "表单提交",
        "表单编辑",
        "全局异常",
        "文件下载"
    )

    override fun layoutId(): Int = R.layout.activity_main_vp

    override fun initView(savedInstanceState: Bundle?) {
        mBinding!!.vpMain.adapter = myViewPagerAdapter;
        mBinding!!.vpMain.setOnPageChangeListener(MyOnPageChangeListener())
        mBinding!!.vpMain.offscreenPageLimit = 3
        mBinding!!.ptsMain.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        mBinding!!.ptsMain.setTextColor(Color.WHITE)
        mBinding!!.ptsMain.setTabIndicatorColorResource(R.color.colorAccent)
        mBinding!!.ptsMain.drawFullUnderline = false
        mBinding!!.mainToolbar.title = AppUtils.getAppName(this)
        setSupportActionBar(mBinding!!.mainToolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            //设置ActionBar左上角按钮
            actionBar.setHomeAsUpIndicator(R.drawable.ic_nav)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        mAdapter = LeftListAdapter(this, itemInfoList)
        mAdapter.setItemListener(object : LeftListAdapter.OnLeftItemClick {
            override fun onItem(pos: Int) {
                mBinding!!.vpMain.currentItem = pos
                mBinding!!.drawerMain.closeDrawers()
            }

        })
        mBinding!!.leftRv.layoutManager = LinearLayoutManager(this)
        mBinding!!.leftRv.adapter = mAdapter
        //start
        itemInfoList.add(ItemInfo(mFragName[0], HttpFragment()))
        itemInfoList.add(ItemInfo(mFragName[1], PermissionFrag()))
        itemInfoList.add(ItemInfo(mFragName[2], DemoFragment()))
        itemInfoList.add(ItemInfo(mFragName[3], DemoFragment()))
        itemInfoList.add(ItemInfo(mFragName[4], DemoFragment()))
        itemInfoList.add(ItemInfo(mFragName[5], DemoFragment()))
        itemInfoList.add(ItemInfo(mFragName[6], DemoFragment()))
        itemInfoList.add(ItemInfo(mFragName[7], DemoFragment()))
        itemInfoList.add(ItemInfo(mFragName[8], DemoFragment()))
        //notify
        myViewPagerAdapter.notifyDataSetChanged()
        mAdapter.notifyDataSetChanged()
        //vp_maina.currentItem = itemInfoList.size - 1
    }

    override fun initData() {
    }


    class MyOnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> if (mBinding!!.drawerMain.isDrawerOpen(GravityCompat.START)) {
                mBinding!!.drawerMain.closeDrawers()
            } else {
                mBinding!!.drawerMain.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRetryBtnClick() {
    }


}