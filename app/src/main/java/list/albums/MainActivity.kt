package list.albums

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import list.albums.model.Album
import list.albums.view.adapter.VerticalRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListingViewModel
    private lateinit var albumsListRecyclerView: RecyclerView
    private lateinit var messageTextViewOnList: TextView

    private lateinit var recyclerAdapter: VerticalRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
        refreshData()
    }

    private fun initViews() {
        albumsListRecyclerView = findViewById(R.id.albumsListRecyclerView)
        albumsListRecyclerView.layoutManager = LinearLayoutManager(this)
        albumsListRecyclerView.setHasFixedSize(false)

        messageTextViewOnList = findViewById(R.id.albumTitleInListItem)
        messageTextViewOnList.text = getString(R.string.loadingMessage)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ListingViewModel::class.java)
    }

    private fun refreshData() {
        viewModel.fetchAlbumsList()?.observe(this, Observer { albumsListApiResponse ->
            setupRecyclerViewAdapter(albumsListApiResponse)
        })
    }

    private fun setupRecyclerViewAdapter(listFromRepository: List<Album>?) {
        when {
            listFromRepository == null -> {
                apiFailedErrorMessage()
            }
            listFromRepository.isEmpty() -> {
                emptyListDataMessage()
            }
            else -> {
                messageTextViewOnList.visibility = View.GONE
                albumsListRecyclerView.visibility = View.VISIBLE
                listFromRepository.let { listData ->
                    recyclerAdapter = VerticalRecyclerViewAdapter(this, listData)
                    albumsListRecyclerView.adapter = recyclerAdapter
                }
            }
        }
    }

    private fun emptyListDataMessage() {
        albumsListRecyclerView.visibility = View.GONE
        messageTextViewOnList.text = getString(R.string.loadingFinishedNoContentToShow)
        messageTextViewOnList.setTextColor(resources.getColor(R.color.neutralMessageColor, null))
        messageTextViewOnList.visibility = View.VISIBLE
    }

    private fun apiFailedErrorMessage() {
        albumsListRecyclerView.visibility = View.GONE
        messageTextViewOnList.text = getString(R.string.errorMessage)
        messageTextViewOnList.setTextColor(resources.getColor(R.color.errorMessageColor, null))
        messageTextViewOnList.visibility = View.VISIBLE
    }
}