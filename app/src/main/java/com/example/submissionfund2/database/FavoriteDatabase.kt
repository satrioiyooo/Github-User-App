package com.example.submissionfund2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteUser::class], version = 2
)
abstract class FavoriteDatabase : RoomDatabase()  {
    abstract fun favDao(): FavDao
    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteDatabase::class.java, "Favorite_database")
                        .build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }

}