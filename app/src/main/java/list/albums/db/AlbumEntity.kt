package list.albums.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class AlbumEntity(
    val userId: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String = ""
)