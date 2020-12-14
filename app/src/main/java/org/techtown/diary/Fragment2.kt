package org.techtown.diary

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_2.view.*

class Fragment2 : Fragment() {
    companion object{private final val TAG = "Fragment2"}

    private var _context : Context? = null
    private var listener : OnTabItemSelectedListener? = null
    private var requestListener : OnRequestListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        this._context = context

        if(context is OnTabItemSelectedListener){
            listener = context
        }

        if(context is OnRequestListener){
            requestListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        if(context!=null){
            _context = null
            listener = null
            requestListener = null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_2, container, false) as ViewGroup

        initUI(rootView)

        requestListener?.onRequest("getCurrentLocation")

        return rootView
    }

    private fun initUI(rootView : ViewGroup){
        rootView.saveButton.setOnClickListener{
            listener?.onTabSelected(0)
        } // 저장 버튼 클릭시 목록으로

        rootView.deleteButton.setOnClickListener {
            listener?.onTabSelected(0)
        } // 삭제 버튼 클릭시 목록으로

        rootView.closeButton.setOnClickListener {
            listener?.onTabSelected(0)
        } // 닫기 버튼 클릭시 목록으로

        rootView.sliderView.setOnSlideListener {
            index -> Toast.makeText(context, "moodIndex changed to $index", Toast.LENGTH_SHORT).show()
        } // 기분 선택되면 호출될 함수

        rootView.sliderView.setInitialIndex(2) // 기본값을 중간으로
    }

}