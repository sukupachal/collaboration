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
                  self.getSelectedForum = function(id) {
                	  console.log("-->ForumController : calling getSelectedForum method : getting forum with id : " + id);
                	  ForumService.getSelectedForum(id).then(
                			  function(d) {
      							self.forum = d;
      							$location.path('/view_forum');
      						}, 
      						function(errResponse) {
      							console.error('Error while fetching Forum...');
      						});
      			};
      			self.fetchAllForums = function() {
      				
    				console.log("--> ForumController : calling fetchAllForums method.");
    				ForumService.fetchAllForums().then(
    						function(d) {
    							self.forums = d;
    						}, function(errResponse) {
    							console.error('Error while fetching Forums...');
    						});
    			};
    			
    			
    			self.createForum = function(forum) {
    				console.log("--> ForumController : calling createForum method.");
    				ForumService.createForum(forum).then(
    						function(d) {
    							self.forums = d;
    							alert('Forum Created Successfully...')
    						},
    						function(errResponse) {
    							console.error('Error while creating forum...');
    						});
    			};
    			
    			self.updateForum = function(forum, id) {
    				console.log("-->ForumController : calling updateForum method.");
    				BlogService.updateForum(forum, id).then(
    						self.fetchAllForums,
    						function(errResponse) {
    							console.error('Error while updating forum...')
    						});
    			};
    			
    			self.deleteForum = function(id) {
    				console.log("-->ForumController : calling deleteForum method.");
    				ForumService.deleteForum(id).then(
    						self.fetchAllForums,
    						function(errResponse) {
    							console.error('Error while deleting forum...')
    						});
    			};
    			
    			self.approveForum = function(forum, id) {
    				console.log("-->ForumController : calling approveForum() method : getting forum with id : " + id);
    				console.log("-->ForumController",self.forum);
    				ForumService.approveForum(forum, id).then(
    						self.fetchAllBlogs,
    						function(errResponse) {
    							console.error("Error while approving forum...")
    						});
    			};
    			
    			self.rejectForum = function(forum, id) {
    				console.log("-->ForumController : calling rejectForum() method : getting forum with id : " + id);
    				console.log("-->ForumController",self.forum);
    				ForumService.rejectForum(forum, id).then(
    						self.fetchAllForums,
    						function(errResponse) {
    							console.error("Error while rejecting forum...")
    						});
    			};
    			
    			
    			self.userCountForum = function(forum, id) {
    				console.log("-->ForumController : calling userCountForum() method : getting forum with id : " + id);
    				console.log("-->ForumController",self.forum);
    				ForumService.userCountForum(forum, id).then(
    						self.fetchAllForums,
    						function(errResponse){
    							console.error("Error while counting user...")
    						});
    			};
    			
    			self.fetchAllForums();

    			/*****************************************************************************/
    					
    					self.submit = function() {
    						{
    							console.log("--> ForumController : calling submit() method.", self.forum);
    							self.createForum(self.forum);
    							console.log('Saving new Forum', self.forum);
    						}
    						self.reset();
    					};			
    					
    					self.edit = function(id) {
    						console.log("id to be edited : "+id);
    						for (var i = 0; i < self.forums.length; i++) {
    							if (self.forums[i].id === id) {
    								self.forum = angular.copy(self.forums[i]);
    								break;
    							}
    						}
    					};

    					self.remove = function(id) {
    						console.log('id to be deleted', id);
    						if (self.forum.id === id) {
    							self.reset();
    						}
    						self.deleteForum(id);
    					};

    					self.reset = function() {
    						console.log('submit a new Forum', self.forum);
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
    		          				

    						};
    						$scope.myForm.$setPristine(); // reset form...
    					};
    				} ]);
    					




    			





      			
                  
                  
                        			

