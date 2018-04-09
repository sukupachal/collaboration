'use strict';

app.factory('BlogService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("BlogService...")

			var BASE_URL = 'http://localhost:9000/bridge'
				return {
				
				getSelectedBlog : function(id) {
					console.log("-->BlogService : calling getSelectedBlog() method with id : " + id);
					return $http
								.get(BASE_URL+'/blog/'+ id)
								.then(function(response) {
									$rootScope.selectedBlog = response.data;
									return response.data;
								},
								function(errResponse) {
									console.error('Error while Fetching Blog.');
									return $q.reject(errResponse);
								});
				},
				
				fetchAllBlogs : function() {
					console.log("--> BlogService : calling 'fetchAllBlogs' method.");
					return $http
								.get(BASE_URL + '/blogs')
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Blogs');
									return $q.reject(errResponse);
								});
				},
				
				fetchAllApprovedBlogs : function() {
					console.log("--> BlogService : calling 'fetchAllApprovedBlogs' method.");
					return $http
								.get(BASE_URL + '/blogs')
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Blogs');
									return $q.reject(errResponse);
								});
				},

				createBlog : function(blog) {
					console.log("--> BlogService : calling 'createBlog' method.");
					return $http
								.post(BASE_URL + '/blog/', blog)
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while creating blog');
									return $q.reject(errResponse);
								});
				},
				
				updateBlog : function(blog, id) {
					console.log("--> BlogService : calling 'updateBlog' method with id : "+id);
					return $http
								.put(BASE_URL+'/blog/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.error('Error while updating Blog');
									return $q.reject(errResponse);
								});
				},
				
				deleteBlog : function(id) {
					console.log("--> BlogService : calling 'updateBlog' method with id : "+id);
					return $http
								delete(BASE_URL+'/blog/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log('Error while deleting Blog');
									return $q.reject(errResponse);
								});
				},
				
				approveBlog : function(blog, id) {
					console.log("-->BlogService : calling approveBlog() method : getting blog with id : " + id);
					return $http
								.put(BASE_URL+'/approveBlog/'+ id, blog)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while approving Blog");
									return $q.reject(errResponse);
								});
				},
				
				rejectBlog : function(blog, id) {
					console.log("-->BlogService : calling rejectBlog() method : getting blog with id : " + id);
					return $http
								.put(BASE_URL+'/rejectBlog/'+ id, blog)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while approving Blog");
									return $q.reject(errResponse);
								});
				},
				
				likeBlog : function(blog, id) {
					console.log("-->BlogService : calling likeBlog() method : getting blog with id : " + id);
					return $http
								.put(BASE_URL+'/likeBlog/'+id, blog)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log("Error while liking Blog.");
									return $q.reject(errResponse);
								});
				},
				
				fetchAllBlogComments : function(id)
				{
					console.log("-->BlogService : calling 'fetchAllBlogComments' method for id : " + id);
					return $http.get(BASE_URL + '/blogComments/'+id).then
					(function(response) 
							{
									$rootScope.selectedBlogComments = response.data;
									return response.data;
							}, 
								function(errResponse) {
									console.error('Error while fetching BlogComments');
									return $q.reject(errResponse);
								});
				},
				
				createBlogComment : function(blogComment)
				{
					console.log("-->BlogService : calling 'createBlogComment' method.");
					return $http.post(BASE_URL + '/blogComment/', blogComment).then
					(function(response)
							{
									return response.data;
							}, 
							function(errResponse) 
							{
									console.error('Error while creating blogComment');
									return $q.reject(errResponse);
							}
					);
				}
				
			
				
			};
		}]);