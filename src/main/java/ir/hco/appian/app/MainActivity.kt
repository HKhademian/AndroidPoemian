package ir.hco.appian.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity(), AppianMainActivity {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		onCreateActivity(savedInstanceState)
	}
}
