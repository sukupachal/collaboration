'use strict';

app.controller('ForumController', [
               '$scope',
               'ForumService',
               '$location',
               '$rootScope',
               function($scope, ForumService, $location, $rootScope) {
                  console.log("ForumController...")
                        			
                  var self = this;
                  self.forum = {
                		errorCode : '',
          				errorMessage : '',
          				forumId : '',
          				forumName : '',
          				forumDescription : '',
          				userId : '',
          				userName : '',
          				forumCreationDate : '',
          				forumStatus :'',
          				forumCountComment : '',
          				forumUserCount:''
          					}
                  self.forums = [];
			
			self.forumComment = 
			{
					errorCode : '',
				    errorMessage : '',
					forumCommentId : '',
					forumId : '',
					forumComment : '',
					forumCommentDate : '',
					userId : '',
					userName:''
			}			
			self.forumComments = [];

			
			
			self.getUserProfile = function(id) {
				console.log("-->ForumController : calling getUserProfile method with id : " + id);
				ForumService.getUserProfile(id).then(function(d) {
					self.forum = d;
					
					console.log("test  "+d);
					$location.path('/view_profile');
				}, function(errResponse) {
					console.error('Error while fetching Forum...');
				});
			};
			
			self.sendFriendRequest = function sendFriendRequest(friendId) {
				console.log("--> sendFriendRequest : "+friendId);
				ForumService.sendFriendRequest(friendId).then(
						function(d) {
							self.friend = d;
							alert("Friend request sent...")
						},
						function(errResponse) {
							console.error("Error while fetching friends.");
						}
					);
			};
			
			self.getSelectedForum = function(id)
		    {
				console.log("-->ForumController : calling getSelectedForum method : getting forum with id : " + id);
				ForumService.getSelectedForum(id).then
				(function(d)
						{
							self.forum = d;
							
							$location.path('/view_forum');
						}, 
						function(errResponse)
						{
							console.error('Error while fetching Forum...');
						}
				);
			};
		    
			self.listforums = function() 
			{
				console.log("-->ForumController : calling 'listForums' method.");
				ForumService.listForums().then
				(function(d) 
						{
								self.forums = d;	
						},
						function(errResponse) 
						{
								console.error("Error while getting forum list.")
						}
				);
			};
			
			self.listforums();
			
			self.createForum = function(forum) 
			{
				console.log("-->ForumController : calling 'createForum' method.");
				ForumService.createForum(forum).then
				(function(d)
						{
								self.forum = d;
								alert('Post Forum?');
								self.listforums();
								$location.path('/forums');
						},
						function(errResponse)
						{
								console.error('Error while posting new Forum...');
						}
				);
			};
			
			self.approveForum = function(forum, id)
			{
				console.log("-->ForumController : calling approveForum() method : Forum id is : " + id);
				console.log("-->ForumController",self.forum);
				ForumService.approveForum(forum, id).then
				(
						function(d)
						{
						alert('Accept Forum?');
						//self.forum=d;
						self.listforums();
						$location.path('/forums');
						},
						function(errResponse) 
						{
							console.error("Error while approving forum...")
						}
				);
			};

			self.rejectForum = function(forum, id) 
			{
				console.log("-->ForumController : calling rejectForum() method : Forum id is : " + id);
				console.log("-->ForumController",self.forum);
				ForumService.rejectForum(forum, id).then
				(
						function(d)
						{
						alert('Reject Forum?');
						//self.forum=d;
						self.listforums();
						$location.path('/forums');
						},
						function(errResponse) 
						{
							console.error("Error while rejecting forum...")
						}
				);
			};
			
			self.fetchAllForumComments = function(id)
			{
				console.log("-->ForumController : calling fetchAllForumComments method with id : "+ id);
				ForumService.fetchAllForumComments(id).then
				(function(d) 
				{
					self.forumComments = d;
							//calling getSelectedForum(id) method ...
			
				},
				function(errResponse) 
				{
					console.error('Error while fetching ForumComments...');
				}
				);
			};
			
			self.createForumComment = function(forumComment, id) {
				console.log("-->ForumController : calling 'createForumComment' method.", self.forum);
				forumComment.forumId = id;
				console.log("-->ForumController ForumId :" +forumComment.forumId);
				ForumService.createForumComment(forumComment,id).then
							(function(d) 
							{
								console.log('Current User :',$rootScope.currentUser.userId)
								self.forumComment = d;
								console.log('-->ForumController :', self.forumComment)
								self.fetchAllForumComments(id);
								self.resetComment();
							},
							function(errResponse) {
								console.error('Error while creating forumComment...');
							}
							);
			};


			

	/*****************************************************************************/

			self.submit = function() 
			{
				{
					console.log("-->ForumController : calling 'submit()' method.", self.forum);
					self.createForum(self.forum);
					console.log('Saving new Forum', self.forum);
				}
				self.reset();
			};
			self.reset = function() 
			{
				console.log('submit a new Forum', self.forum);
				self.forum =
				{
						forumId : '',
						forumName : '',
						forumDescription : '',
						userId : '',
						forumCreationDate : '',
						forumStatus : '',
						userName:'',
						forumCommentCount:'',
					    errorCode : '',
					    errorMessage : ''
				};
				$scope.myForm.$setPristine(); // reset form...
			};
			
			self.resetComment = function() 
			{
				console.log('submit a new ForumComment', self.forumComment);
				self.forumComment = 
				{
						errorCode : '',
					    errorMessage : '',
						forumCommentId : '',
						forumId : '',
						forumComment : '',
						forumCommentDate : '',
						userId : '',
						userName:''
					};
				$scope.myForm.$setPristine(); // reset form...
			};
		} 
]
);