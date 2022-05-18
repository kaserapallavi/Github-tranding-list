package com.example.motilaloswalassignment.ui.custom.recyclerview

import android.view.View
import com.example.motilaloswalassignment.data.local.entity.GithubEntity


interface RecyclerLayoutClickListener {

    fun redirectToDetailScreen(imageView: View,
                               titleView: View,
                               revealView: View,
                               languageView: View,
                               githubEntity: GithubEntity
    )

    fun sharePost(githubEntity: GithubEntity)
}
