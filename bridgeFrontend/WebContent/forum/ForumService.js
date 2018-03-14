'use strict';

app
		.factory(
				'ForumService',
				[
						'$http',
						'$q',
						'$rootScope',
						function($http, $q, $rootScope) {
							console.log("ForumService...")

							var BASE_URL = 'http://localhost:9000/bridge'
							return {
								getSelectedForum : function(id) {
									console
											.log("--> ForumService : calling getSelectedForum() method with id :"
													+ id);
									return $http
											.get(BASE_URL + '/forum/' + id)
											.then(
													function(response) {
														$rootScope.selectedForum = response.data;
														return response.data
													},
													function(errResponse) {
														console
																.error('Error while fetching forum');
														return $q
																.reject(errResponse);
													});
								},

								fetchAllForums : function() {
									console
											.log("--> ForumService : calling 'fetchAllForums' method.");
									return $http
											.get(BASE_URL + '/forums')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});

								},
								fetchAllApprovedForums : function() {
									console
											.log("-->ForumService : calling 'fetchAllApprovedForums' method.");
									return $http
											.get(BASE_URL + '/forum')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});
								},

								createForum : function(forum) {
									console
											.log("-->ForumService : calling 'createForum' method.");
									return $http
											.post(BASE_URL + '/forum/', forum)
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});
								},

								updateForum : function(forum, id) {
									console
											.log("-->ForumService : calling 'updateForum' method.");
									return $http
											.put(BASE_URL + '/forum')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});
								},

								deleteForum : function(id) {
									console
											.log("-->ForumService : calling 'deleteForum' method.");
									return $http
									delete (BASE_URL + 'forum')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});
								},

								approveForum : function(forum, id) {
									console
											.log("-->ForumService : calling 'approveForum' method.");
									return $http
											.put(BASE_URL + '/forum')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});
								},

								rejectForum : function(forum, id) {
									console
											.log("-->ForumService : calling 'rejectForum' method.");
									return $http
											.put(BASE_URL + '/forum')
											.then(
													function(response) {
														return response.data;
													},
													function(errResponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});
								},

								userCountForum : function(forum, id) {
									console
											.log("-->Forumservice : calling 'userCountForum' method");
									return $http
											.put(BASE_URL + '/forum')
											.then(
													function(response) {
														return response.data;
													},
													function(errresponse) {
														console
																.error('Error while fetching Forums');
														return $q
																.reject(errResponse);
													});
								}

							};
						} ]);