package com.example.quizdynamox.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizdynamox.model.entity.PlayerEntity

private const val DB_NAME = "quiz_database"

@Database(
    entities = [PlayerEntity::class],
    version = 1,
    exportSchema = true
)

abstract class QuizDataBase : RoomDatabase() {

    abstract fun playerDAO(): PlayerDAO

    companion object {
        @Volatile
        private var INSTANCE: QuizDataBase? = null

        fun getDatabase(
            context: Context,
        ): QuizDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDataBase::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}