package room

import android.database.Cursor
import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE city LIKE :city")
    fun getDataByWord(city: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

//    @Query("DELETE FROM HistoryEnity WHERE id = :id")
//    fun deleteById(id: Long)
//
//    @Query("SELECT id, city, temperature FROM HistoryEnity")
//    fun getHistoryCursor():Cursor
//
//    @Query("SELECT id, city, temperature FROM HistoryEnity WHERE id = :id")
//    fun getHistoryCursor(id: Long) :Cursor
}