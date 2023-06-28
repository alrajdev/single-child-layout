package dev.alraj.singlelayoutexample

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.alraj.singlelayoutexample.databinding.ActivityMainBinding
import dev.alraj.singlelayoutexample.databinding.ActivityMainLivedataBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    lateinit var b: ActivityMainLivedataBinding
//    private var visibility = View.VISIBLE
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainLivedataBinding.inflate(layoutInflater)
        setContentView(b.root)

        vm.liveData.observe(this) {
            Timber.d("observe list %d", it.size)
            if(it.isEmpty()) {
                Timber.d("observe list.isEmpty")
                b.tv1.isVisible = true
            }
            else {
                Timber.d("observe list.isNotEmpty")
                b.tv2.isVisible = true
            }
        }
        vm.getList()
    }

//    private fun layout1() {
//        b.visibilityToggle.setOnCheckedChangeListener { _, checkedId ->
//            when(checkedId) {
//                R.id.visible -> visibility = View.VISIBLE
//                R.id.invisible -> visibility = View.INVISIBLE
//                R.id.gone -> visibility = View.GONE
//            }
//        }
//
//        b.textviewToggle.children.forEach { view ->
//            view.setOnClickListener {
//                var fl_textview = b.flTextview1
//                var scl_textview = b.sclTextview1
//                when (view.id) {
//                    R.id.textview1_toggle -> {
//                        fl_textview = b.flTextview1
//                        scl_textview = b.sclTextview1
//                    }
//                    R.id.textview2_toggle -> {
//                        fl_textview = b.flTextview2
//                        scl_textview = b.sclTextview2
//                    }
//                    R.id.textview3_toggle -> {
//                        fl_textview = b.flTextview3
//                        scl_textview = b.sclTextview3
//                    }
//                    R.id.textview4_toggle -> {
//                        fl_textview = b.flTextview4
//                        scl_textview = b.sclTextview4
//                    }
//                }
//
//                fl_textview.visibility = visibility
//                scl_textview.visibility = visibility
//            }
//        }
//
//        b.restart.setOnClickListener { recreate() }
//    }
}