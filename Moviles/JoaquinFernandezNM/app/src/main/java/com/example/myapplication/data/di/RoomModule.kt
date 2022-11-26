package com.example.myapplication.data.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.Const
import com.example.myapplication.data.dao.StudentDao
import com.example.myapplication.data.dao.SubjectDao
import com.example.myapplication.data.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Named(Const.SCHOOL_DATABASE)
    fun getHeadsetDB() = Const.SCHOOL_DATABASE_PATH

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named(Const.SCHOOL_DATABASE) dataPath: String
    ): Database =
        Room.databaseBuilder(context, Database::class.java, Const.DATABASE_NAME)
            .createFromAsset(dataPath).fallbackToDestructiveMigration().build()

    @Provides
    fun providesStudentDao(database: Database): StudentDao  =
        database.studentDao()

    @Provides
    fun providesSubjectDao(database: Database): SubjectDao  =
        database.subjectDao()

}