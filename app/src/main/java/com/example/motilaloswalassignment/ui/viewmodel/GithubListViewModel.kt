package com.example.motilaloswalassignment.ui.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import com.example.motilaloswalassignment.data.SingleLiveEvent
import com.example.motilaloswalassignment.data.local.dao.GithubDao
import com.example.motilaloswalassignment.data.local.entity.GithubEntity
import com.example.motilaloswalassignment.data.remote.api.GithubApiService
import com.example.motilaloswalassignment.data.repository.GithubRepository


import java.util.ArrayList

import javax.inject.Inject

class GithubListViewModel @Inject
constructor(githubDao: GithubDao, githubApiService: GithubApiService) : ViewModel() {

    private var currentPage: Long = 0L
    private val repository: GithubRepository = GithubRepository(githubDao, githubApiService)

    private val repositories = ArrayList<GithubEntity>()
    private val repositoryListLiveData = SingleLiveEvent<List<GithubEntity>>()


    @SuppressLint("CheckResult")
    fun fetchRepositories() {
        repository.getRepositories((++currentPage))
                .subscribe { resource ->
                    if (resource.isLoaded) {
                        resource.data?.let { repositories.addAll(it) }
                        repositoryListLiveData.postValue(resource.data)
                    }
                }
    }

    fun isLastPage(): Boolean {
        return repositoryListLiveData.value?.let { it[0].isLastPage() }
        ?: run {
            false
        }
    }

    fun getRepositoryLiveData() = repositoryListLiveData

    fun getRepositories(): List<GithubEntity> {
        return repositories
    }
}
