'use strict';

app.controller('BlogController', [
		'$scope',
		'BlogService',
		'$location',
		'$rootScope',
		function($scope, BlogService, $location, $rootScope) {
			console.log("BlogController...")

			var self = this;
			self.blog = {
			    errorCode : '',
				errorMessage : '',
				blogId : '',
				blogContent : '',
				blogTitle : '',
				blogDate : '',
				userId : '',
				blogStatus : '',
				blogCountLike : '',
				blogCommentCount : ''
				}
			self.blogs = [];
			
			 self.blogComment = 
			    {       errorCode : '',
						errorMessage : '',
			    		blogCommentId : '',
			    		blogId : '',
			    		userId : '',
			    		userName : '',
			    		blogCommentDate:'',
			    		blogComment : ''
				}		
			    
			    self.blogComments = [];
				


			self.getSelectedBlog = function(id) {
				console.log("-->BlogController : calling getSelectedBlog method : getting blog with id : " + id);
				BlogService.getSelectedBlog(id).then(
						function(d) {
							self.blog = d;
							$location.path('/view_blog');
						}, 
						function(errResponse) {
							console.error('Error while fetching Blog...');
						});
			};

			self.fetchAllBlogs = function() {
				console.log("--> BlogController : calling fetchAllBlogs method.");
				BlogService.fetchAllBlogs().then(
						function(d) {
							self.blogs = d;
						}, function(errResponse) {
							console.error('Error while fetching Blogs...');
						});
			};
			
			self.createBlog = function(blog) {
				console.log("--> BlogController : calling createBlog method.");
				BlogService.createBlog(blog).then(
						function(d) {
							self.blogs = d;
							alert('Blog Created Successfully...')
						},
						function(errResponse) {
							console.error('Error while creating blog...');
						});
			};

			self.updateBlog = function(blog, id) {
				console.log("-->BlogController : calling updateBlog method.");
				BlogService.updateBlog(blog, id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error('Error while updating blog...')
						});
			};

			self.deleteBlog = function(id) {
				console.log("-->BlogController : calling deleteBlog method.");
				BlogService.deleteBlog(id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error('Error while deleting blog...')
						});
			};
			
			self.approveBlog = function(blog, id) {
				console.log("-->BlogController : calling approveBlog() method : getting blog with id : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.approveBlog(blog, id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error("Error while approving blog...")
						});
			};

			self.rejectBlog = function(blog, id) {
				console.log("-->BlogController : calling rejectBlog() method : getting blog with id : " + id);
				console.log("-->BlogController",self.blog);
				BlogService.rejectBlog(blog, id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error("Error while rejecting blog...")
						});
			};
			
			self.likeBlog = function(blog, id) {
				console.log("-->BlogController : calling likeBlog() method : Blog id is : "+id);
				console.log("-->BlogController", self.blog);
				BlogService.likeBlog(blog, id).then(
						self.fetchAllBlogs,
						function(errResponse) {
							console.error("Error while liking the blog...")
						});
				
			};
			
			self.fetchAllBlogComments = function(id)
			{
				console.log("-->BlogController : calling fetchAllBlogComments method with id : "+ id);
				BlogService.fetchAllBlogComments(id).then
				(function(d) 
				{
					self.blogComments = d;
						//calling getSelectedBlog(id) method ...
					$rootScope.SelectedBlogComments = self.blogComments;
					
				},
				function(errResponse) 
				{
					console.error('Error while fetching BlogComments...');
				}
				);
			};
			
			
			
			self.createBlogComment = function(blogComment, id) {
				console.log("-->BlogController : calling 'createBlogComment' method.", self.blog);
				blogComment.blogId = id;
				console.log("-->BlogController BlogId :" +blogComment.blogId);
				BlogService.createBlogComment(blogComment,id).then
							(function(d) 
							{
								console.log('Current User :',$rootScope.currentUser.userId)
								self.blogComment = d;
								console.log('-->BlogController :', self.blogComment)
								self.fetchAllBlogComments(id);
								self.resetBlogComment();
								$rootScope.blogComment = blogComment.userId;
							},
							function(errResponse) {
								console.error('Error while creating blogComment...');
							}
							);
			};
			

			self.fetchAllBlogs();

	/*****************************************************************************/
			
			self.submit = function() {
				{
					console.log("--> BlogController : calling submit() method.", self.blog);
					self.createBlog(self.blog);
					console.log('Saving new Blog', self.blog);
				}
				self.reset();
			};			
			
			self.edit = function(id) {
				console.log("id to be edited : "+id);
				for (var i = 0; i < self.blogs.length; i++) {
					if (self.blogs[i].id === id) {
						self.blog = angular.copy(self.blogs[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.blog.id === id) {
					self.reset();
				}
				self.deleteBlog(id);
			};

			self.reset = function() {
				console.log('submit a new Blog', self.blog);
				self.blog = {
						 errorCode : '',
							errorMessage : '',
							blogId : '',
							blogContent : '',
							blogTitle : '',
							blogDate : '',
							userId : '',
							blogStatus : '',
							blogCountLike : '',
							blogCommentCount : ''

				};
			},
				self.resetBlogComment = function() 
				{
					console.log('submit a new BlogComment', self.blogComment);
					self.blogComment = {
							    errorCode : '',
							    errorMessage : '',
					    		blogCommentId : '',
					    		blogId : '',
					    		userId : '',
					    		userName : '',
					    		blogCommentDate:'',
					    		blogComment : ''
						};
					$scope.myForm.$setPristine(); // reset blogComment form...
				};
				
			} ]);