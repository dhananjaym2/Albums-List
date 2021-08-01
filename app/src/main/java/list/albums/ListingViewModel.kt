package list.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import list.albums.model.Album
import java.lang.IllegalArgumentException

class ListingViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var albumListLiveData: MutableLiveData<List<Album>>? = null

    fun fetchAlbumsList(): LiveData<List<Album>>? {
        albumListLiveData = AlbumRepository.getAlbumsList()
        return albumListLiveData
    }

//val albumList: List<Album> = savedStateHandle["albumsList"] ?: throw IllegalArgumentException("Missing albums list")
}