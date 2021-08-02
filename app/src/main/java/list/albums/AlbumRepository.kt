package list.albums

import android.util.Log
import androidx.lifecycle.MutableLiveData
import list.albums.application.AlbumsListApplication
import list.albums.db.AlbumEntity
import list.albums.db.AppDatabase
import list.albums.model.Album
import list.albums.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AlbumRepository {

    private val logTag: String = AlbumRepository::class.java.simpleName

    val apiResponseMutableLiveData = MutableLiveData<List<Album>>()

    fun getAlbumsList(): MutableLiveData<List<Album>>? {
        val dbData = getAlbumsListFromDB()
        if (dbData.isNotEmpty()) {
            return apiResponseMutableLiveData
        } else {
            getAlbumsListFromApi()
            return apiResponseMutableLiveData
        }
    }

    private fun getAlbumsListFromDB(): List<Album> {
        val listOfAlbumEntity = albumDao().getAllAlbumsSortedByTitle()
        val listOfAlbums = mutableListOf<Album>()
        for (albumEntity in listOfAlbumEntity) {
            listOfAlbums.add((Album(albumEntity.userId, albumEntity.id, albumEntity.title)))
        }
        return listOfAlbums
    }

    private fun albumDao() = AppDatabase.getInstance(AlbumsListApplication.getInstance())
        .getAlbumDao()

    private fun getAlbumsListFromApi(): MutableLiveData<List<Album>>? {
        val call = RetrofitClient.apiInterface.getAlbumsList()

        call.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.e(logTag, t.message.toString())
                throw t
            }

            override fun onResponse(
                call: Call<List<Album>>,
                response: Response<List<Album>>
            ) {
                apiResponseMutableLiveData.value = response.body()
                saveAlbumsToDb(response.body())
            }
        })

        return apiResponseMutableLiveData
    }

    private fun saveAlbumsToDb(listFromApi: List<Album>?) {
        // convert to albums entity
        val listOfAlbums = mutableListOf<AlbumEntity>()
        if (listFromApi != null) {
            for (album in listFromApi) {
                listOfAlbums.add((AlbumEntity(album.userId, album.id, album.title)))
            }
        }
        albumDao().addAlbums(albums = listOfAlbums)
    }
}
//class AlbumLocalDataSource {
//
//}

//class AlbumRemoteDataSource(private val albumListService: AlbumListRetrofitService) {
//
//}