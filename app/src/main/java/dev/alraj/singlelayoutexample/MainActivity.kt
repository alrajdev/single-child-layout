package dev.alraj.singlelayoutexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import dev.alraj.singlelayoutexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var b: ActivityMainBinding
    private var visibility = View.VISIBLE
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.visibilityToggle.setOnCheckedChangeListener { _, checkedId -> 
            when(checkedId) {
                R.id.visible -> visibility = View.VISIBLE
                R.id.invisible -> visibility = View.INVISIBLE
                R.id.gone -> visibility = View.GONE
            }
        }
        
        b.textviewToggle.children.forEach { view ->
            view.setOnClickListener {
                var fl_textview = b.flTextview1
                var scl_textview = b.sclTextview1
                when (view.id) {
                    R.id.textview1_toggle -> {
                        fl_textview = b.flTextview1
                        scl_textview = b.sclTextview1
                    }
                    R.id.textview2_toggle -> {
                        fl_textview = b.flTextview2
                        scl_textview = b.sclTextview2
                    }
                    R.id.textview3_toggle -> {
                        fl_textview = b.flTextview3
                        scl_textview = b.sclTextview3
                    }
                    R.id.textview4_toggle -> {
                        fl_textview = b.flTextview4
                        scl_textview = b.sclTextview4
                    }
                }

                fl_textview.visibility = visibility
                scl_textview.visibility = visibility
            }
        }

        b.restart.setOnClickListener { recreate() }
    }
}