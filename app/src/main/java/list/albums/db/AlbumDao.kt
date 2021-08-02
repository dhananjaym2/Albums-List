package list.albums.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbums(albums: List<AlbumEntity>)

    @Query("SELECT * FROM album_table ORDER BY title ASC")
    fun getAllAlbumsSortedByTitle(): List<AlbumEntity>
}