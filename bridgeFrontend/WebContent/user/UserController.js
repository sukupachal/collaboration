'use strict';

app.controller('UserController', [
		'$scope',
		'UserService',
		'$location',
		'$rootScope',
		function($scope, UserService, $location, $rootScope) {
			console.log("UserController...")

			var self = this;
			self.user = {
			    errorCode : '',
				errorMessage : '',
				userId : '',
				name : '',
				password : '',
				role : '',
				email : '',
				description : '',
				gender : '',
				DOB : '',
				address : '',
				contactNo : '',
				IsOnline : '',
				status : ''
				}
			self.users = [];

			self.getSelectedUser = function(id) {
				console.log("-->UserController : calling getSelectedUser method : getting user with id : " + id);
				UserService.getSelectedUser(id).then(
						function(d) {
							self.user = d;
							$location.path('/view_user');
						}, 
						function(errResponse) {
							console.error('Error while fetching User...');
						});
			};

			self.fetchAllUsers = function() {
				console.log("--> UserController : calling fetchAllUsers method.");
				UserService.fetchAllUsers().then(
						function(d) {
							self.users = d;
						}, function(errResponse) {
							console.error('Error while fetching Users...');
						});
			};
			
			self.createUser = function(user) {
				console.log("--> UserController : calling createUser method.");
				UserService.createUser(user).then(
						function(d) {
							self.users = d;
							alert('User Created Successfully...')
						},
						function(errResponse) {
							console.error('Error while creating user...');
						});
			};

			self.updateUser = function(user, id) {
				console.log("-->UserController : calling updateUser method.");
				UserService.updateUser(user, id).then(
						self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while updating user...')
						});
			};

			self.deleteUser = function(id) {
				console.log("-->UserController : calling deleteUser method.");
				userService.deleteUser(id).then(
						self.fetchAllUsers,
						function(errResponse) {
							console.error('Error while deleting user...')
						});
			};
			
			self.authenticateUser = function(user) {
				console.log("-->UserController : calling authenticateUser method.");
				UserService.authenticateUser(user).then(
						function(d) {
							self.user = d;
							$rootScope.currentUser = self.user;
							console.log (self.user.errorMessage);
							$location.path('/view_blog');
						}, 
						function(errResponse) {
							console.error('Error while fetching User...');
						});
			};
			
			
			
			self.fetchAllUsers();

	/*****************************************************************************/
			
			self.submit = function() {
				{
					console.log("--> UserController : calling submit() method.", self.user);
					self.createUser(self.user);
					console.log('Saving new User', self.user);
				}
				self.reset();
			};			
			
			self.edit = function(id) {
				console.log("id to be edited : "+id);
				for (var i = 0; i < self.users.length; i++) {
					if (self.users[i].id === id) {
						self.user = angular.copy(self.users[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				if (self.user.id === id) {
					self.reset();
				}
				self.deleteUser(id);
			};

			self.reset = function() {
				console.log('submit a new User', self.user);
				self.user = {
						    errorCode : '',
							errorMessage : '',
							userId : '',
							name : '',
							password : '',
							role : '',
							email : '',
							description : '',
							gender : '',
							DOB : '',
							address : '',
							contactNo : '',
							IsOnline : '',
							status : ''

				};
				$scope.myForm.$setPristine(); // reset form...
			};
		} ]);