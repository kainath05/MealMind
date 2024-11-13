package com.example.mealmind.data.database

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(
    tableName = "preferences",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Preference(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    @ColumnInfo(name = "dietaryOptions") val dietaryOptions: String,
    @ColumnInfo(name = "mealOptions") val mealOptions: String,
    @ColumnInfo(name = "cuisineType") val cuisineType: String
)


@Dao
interface PreferenceDao {
    @Insert
    suspend fun insert(preference: Preference)

    @Query("SELECT * FROM preferences WHERE userId = :userId")
    suspend fun getPreferencesByUserId(userId: Int): List<Preference>
}