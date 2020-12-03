package com.ng.ngbaselib.http.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * 描述:
 * @author Jzn
 * @date 2020/8/7
 *
 */

//搜索结果
data class SearchResult(
        var total_count: Int? = null,
        var incomplete_results: Boolean? = null,
        var items: List<SearchItem>? = null
)


@Entity(tableName = "search")
data class SearchItem(
        @PrimaryKey
        var id: Long = 0,
        var private: Boolean? = null,
        var node_id: String? = null,
        var name: String? = null,
        var full_name: String? = null,
        var owner: Owner? = null,
        var html_url: String? = null,
        var description: String? = null,
        var fork: Boolean? = false,
        var url: String? = null,
        var forks_url: String? = null,
        var keys_url: String? = null,
        var collaborators_url: String? = null,
        var teams_url: String? = null,
        var hooks_url: String? = null,
        var issue_events_url: String? = null,
        var events_url: String? = null,
        var assignees_url: String? = null,
        var branches_url: String? = null,
        var tags_url: String? = null,
        var blobs_url: String? = null,
        var git_tags_url: String? = null,
        var git_refs_url: String? = null,
        var trees_url: String? = null,
        var statuses_url: String? = null,
        var languages_url: String? = null,
        var stargazers_url: String? = null,
        var contributors_url: String? = null,
        var subscribers_url: String? = null,
        var subscription_url: String? = null,
        var commits_url: String? = null,
        var git_commits_url: String? = null,
        var comments_url: String? = null,
        var issue_comment_url: String? = null,
        var contents_url: String? = null,
        var compare_url: String? = null,
        var merges_url: String? = null,
        var archive_url: String? = null,
        var downloads_url: String? = null,
        var issues_url: String? = null,
        var pulls_url: String? = null,
        var milestones_url: String? = null,
        var notifications_url: String? = null,
        var labels_url: String? = null,
        var releases_url: String? = null,
        var deployments_url: String? = null,
        var created_at: Date? = null,
        var updated_at: Date? = null,
        var pushed_at: Date? = null,
        var git_url: String? = null,
        var ssh_url: String? = null,
        var clone_url: String? = null,
        var svn_url: String? = null,
        var homepage: String? = null,
        var size: Int? = 0,
        var stargazers_count: Int? = 0,
        var watchers_count: Int? = 0,
        var language: String? = null,
        var has_issues: Boolean? = false,
        var has_projects: Boolean? = false,
        var has_downloads: Boolean? = false,
        var has_wiki: Boolean? = false,
        var has_pages: Boolean? = false,
        var forks_count: Int? = 0,
        var mirror_url: String? = null,
        var archived: Boolean? = false,
        var disabled: Boolean? = false,
        var open_issues_count: Int? = 0,
        var license: License? = null,
        var forks: Int? = 0,
        var open_issues: Int? = 0,
        var watchers: Int? = 0,
        var default_branch: String? = null,
        var score: Int? = 0
)
@Entity(tableName = "owner")
data class Owner(
        var login: String? = null,
        @PrimaryKey
        var id: Long = 0,
        var node_id: String? = null,
        var avatar_url: String? = null,
        var gravatar_id: String? = null,
        var url: String? = null,
        var html_url: String? = null,
        var followers_url: String? = null,
        var following_url: String? = null,
        var gists_url: String? = null,
        var starred_url: String? = null,
        var subscriptions_url: String? = null,
        var organizations_url: String? = null,
        var repos_url: String? = null,
        var events_url: String? = null,
        var received_events_url: String? = null,
        var type: String? = null,
        var site_admin: Boolean? = false
)

@Entity(tableName = "license")
data class License(
        @PrimaryKey
        var key: String = "",
        var name: String? = null,
        var spdx_id: String? = null,
        var url: String? = null,
        var node_id: String? = null
)