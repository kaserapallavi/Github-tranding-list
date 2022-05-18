package com.example.motilaloswalassignment.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import com.example.motilaloswalassignment.data.local.entity.GithubEntity

@Dao
interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(githubEntities: List<GithubEntity>): LongArray

    @Query("SELECT * FROM `GithubEntity` where page = :page")
    fun getRepositoriesByPage(page: Long): List<GithubEntity>
}
