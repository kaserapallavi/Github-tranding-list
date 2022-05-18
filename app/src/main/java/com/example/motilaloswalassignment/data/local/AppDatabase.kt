package com.example.motilaloswalassignment.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

import com.example.motilaloswalassignment.data.local.converter.TimestampConverter
import com.example.motilaloswalassignment.data.local.dao.GithubDao
import com.example.motilaloswalassignment.data.local.entity.GithubEntity


@Database(entities = [GithubEntity::class], version = 1, exportSchema = false)
@TypeConverters(TimestampConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao
}
