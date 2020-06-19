package com.ng.ngbaselib.show

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.ng.ngbaselib.show.main.bean.ItemInfo
import com.ng.ngbaselib.show.main.adapter.LeftListAdapter
import com.ng.ngbaselib.show.main.adapter.MainViewPagerAdapter
import com.ng.ngbaselib.R
import com.ng.ngbaselib.show.frag.DemoFragment
import com.ng.ngbaselib.show.frag.PermissionFrag
import com.ng.ngbaselib.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main_vp.*


/**
 * @author Jzn
 * @date 2020-04-11
 */
class MainActivity : AppCompatActivity() {

    private fun getContentViewLayoutID(): Int = R.layout.activity_main_vp

    private var itemInfoList = ArrayList<ItemInfo>()
    private var myViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, itemInfoList)
    private lateinit var mAdapter: LeftListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewLayoutID())
        initViewsAndEvents()
    }

    private val mFragName = arrayListOf("权限判断",
            "网络请求",
            "rv多布局",
            "tab按钮",
            "viewpager",
            "表单提交",
            "表单编辑",
            "全局异常",
            "文件下载"
    )

    private fun initViewsAndEvents() {

        initView()
        //start
        itemInfoList.add(ItemInfo(mFragName[0], PermissionFrag()))
        itemInfoList.add(ItemInfo(mFragName[1], DemoFragment()))
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

    private fun initView() {
        vp_maina.adapter = myViewPagerAdapter;
        vp_maina.setOnPageChangeListener(MyOnPageChangeListener())
        vp_maina.offscreenPageLimit = 3
        pts_main.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        pts_main.setTextColor(Color.WHITE)
        pts_main.setTabIndicatorColorResource(R.color.colorAccent)
        pts_main.drawFullUnderline = false
        main_toolbar.title = AppUtils.getAppName(this)
        setSupportActionBar(main_toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            //设置ActionBar左上角按钮
            actionBar.setHomeAsUpIndicator(R.drawable.ic_nav)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        mAdapter = LeftListAdapter(this, itemInfoList)
        mAdapter.setItemListener(object : LeftListAdapter.OnLeftItemClick {
            override fun onItem(pos: Int) {
                vp_maina.currentItem = pos
                drawer_main.closeDrawers()
            }

        })
        left_rv.layoutManager = LinearLayoutManager(this)
        left_rv.adapter = mAdapter
    }


    class MyOnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        //menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> if (drawer_main.isDrawerOpen(GravityCompat.START)) {
                drawer_main.closeDrawers()
            } else {
                drawer_main.openDrawer(GravityCompat.START)
            }
            //R.id.menu_1 -> Toast.makeText(this@MainActivity, item.getTitle(), Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

//    fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val menuInflater = menuInflater
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

//    fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.getItemId()) {
//            android.R.id.home -> if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                drawerLayout.closeDrawers()
//            } else {
//                drawerLayout.openDrawer(GravityCompat.START)
//            }
//            R.id.menu_1 -> Toast.makeText(this@MainActivity, item.getTitle(), Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }

}