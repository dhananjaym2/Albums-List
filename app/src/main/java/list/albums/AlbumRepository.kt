package list.albums

import android.util.Log
import androidx.lifecycle.MutableLiveData
import list.albums.model.Album
import list.albums.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AlbumRepository {

    private val logTag: String = AlbumRepository::class.java.simpleName

    val apiResponseMutableLiveData = MutableLiveData<List<Album>>()

    fun getAlbumsList(): MutableLiveData<List<Album>>? {
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
            }
        })

        return apiResponseMutableLiveData
    }
}


class AlbumLocalDataSource {

}

//class AlbumRemoteDataSource(private val albumListService: AlbumListRetrofitService) {
//
//}