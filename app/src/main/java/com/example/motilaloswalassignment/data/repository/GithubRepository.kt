package com.example.motilaloswalassignment.data.repository

import com.an.github.AppConstants.Companion.QUERY_ORDER
import com.an.github.AppConstants.Companion.QUERY_SORT
import com.example.motilaloswalassignment.data.NetworkBoundResource
import com.example.motilaloswalassignment.data.Resource
import com.example.motilaloswalassignment.data.local.dao.GithubDao
import com.example.motilaloswalassignment.data.local.entity.GithubEntity
import com.example.motilaloswalassignment.data.remote.api.GithubApiService
import com.example.motilaloswalassignment.data.remote.model.GithubApiResponse

import javax.inject.Singleton

import io.reactivex.Flowable
import io.reactivex.Observable


@Singleton
class GithubRepository(private val githubDao: GithubDao, private val githubApiService: GithubApiService) {

    fun getRepositories(page: Long): Observable<Resource<List<GithubEntity>>> {
        return object : NetworkBoundResource<List<GithubEntity>, GithubApiResponse>() {

            override fun saveCallResult(item: GithubApiResponse) {
                val repositories = item.items
                if (repositories != null) {
                    for (githubEntity in repositories) {
                        githubEntity.page = page
                        githubEntity.totalPages = item.totalCount
                    }
                }
                if (repositories != null) {
                    githubDao.insertRepositories(repositories)
                }
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override fun loadFromDb(): Flowable<List<GithubEntity>> {
                val repositories = githubDao.getRepositoriesByPage(page)
                return Flowable.just(repositories)
            }

            override fun createCall(): Observable<Resource<GithubApiResponse>> {
                return githubApiService.fetchRepositories(QUERY_SORT, QUERY_ORDER, page)
                        .flatMap<Resource<GithubApiResponse>> { response ->
                            Observable.just(if (response.isSuccessful)
                                Resource.success(response.body()!!)
                            else
                                Resource.error("", GithubApiResponse(0, arrayListOf())))
                        }
            }

        }.getAsObservable()
    }
}
