package list.albums

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()

        if (savedInstanceState == null) {
            refreshData()
        }
    }

    private fun refreshData() {
        viewModel.fetchAlbumsList()?.observe(this, Observer { albumsListApiResponse ->
            //TODO render list adapter
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ListingViewModel::class.java)
    }
}