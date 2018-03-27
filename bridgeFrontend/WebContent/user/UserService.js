'use strict';

app.factory('UserService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("UserService...")

			var BASE_URL = 'http://localhost:9000/bridge'
				return {
				
				getSelectedUser : function(id) {
					console.log("-->UserService : calling getSelectedUser() method with id : " + id);
					return $http
								.get(BASE_URL+'/user/'+ id)
								.then(function(response) {
									$rootScope.selectedUser = response.data;
									return response.data;
								},
								function(errResponse) {
									console.error('Error while Fetching User.');
									return $q.reject(errResponse);
								});
				},
				
				fetchAllUsers : function() {
					console.log("--> UserService : calling 'fetchAllUsers' method.");
					return $http
								.get(BASE_URL + '/users')
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Users');
									return $q.reject(errResponse);
								});
				},
				
				

				createUser : function(user) {
					console.log("--> UserService : calling 'createUser' method.");
					return $http
								.post(BASE_URL + '/user/', user)
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while creating user');
									return $q.reject(errResponse);
								});
				},
				
				updateUser : function(user, id) {
					console.log("--> UserService : calling 'updateUser' method with id : "+id);
					return $http
								.put(BASE_URL+'/user/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.error('Error while updating User');
									return $q.reject(errResponse);
								});
				},
				
				deleteUser : function(id) {
					console.log("--> UserService : calling 'updateUser' method with id : "+id);
					return $http
								delete(BASE_URL+'/user/'+id)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log('Error while deleting User');
									return $q.reject(errResponse);
								});
				},
				
				authenticateUser : function(user) {
					console.log("--> UserService : calling 'authenticateUser' ");
					return $http
					            .post(BASE_URL+'/user/authenticate/', user)
								.then(function(response) {
									return response.data;
								},
								function(errResponse) {
									console.log('Error while fetching  User');
									return $q.reject(errResponse);
								});
				}
				
				
				
							};
		}]);
			