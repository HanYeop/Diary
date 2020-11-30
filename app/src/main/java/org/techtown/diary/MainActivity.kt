package org.techtown.diary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnTabItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        val fragment1 = Fragment1()
        val fragment2 = Fragment2()
        val fragment3 = Fragment3()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container,fragment1).commit() // 처음 시작시 프래그먼트 1 표시

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.tab1->{
                    changeFragment(fragment1)
                }
                R.id.tab2->{
                    changeFragment(fragment2)
                }
                R.id.tab3->{
                    changeFragment(fragment3)
                }
            }
            true
        } // 전달받은 Id에 따른 네비게이션뷰 프래그먼트 설정
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment).commit()
    } // 각 선택된 프래그먼트로 변경

    override fun onTabSelected(position: Int) {
        when(position){
            0->bottom_navigation.setSelectedItemId(R.id.tab1)
            1->bottom_navigation.setSelectedItemId(R.id.tab2)
            2->bottom_navigation.setSelectedItemId(R.id.tab3)
        }
    } // 탭 선택시 화면 변경 (Id로 전달)
}
