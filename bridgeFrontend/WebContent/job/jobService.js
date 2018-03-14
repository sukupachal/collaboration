'use strict';

app.factory('JobService', ['$http', '$q', '$rootScope',
		function($http, $q, $rootScope) {
			console.log("JobService...")
			var BASE_URL = 'http://localhost:9000/bridge'
				return {
			
				getSelectedJob : function(id) {
					console.log("-->JobService : calling getSelectedJob() method with id : " + id);
					return $http
								.get(BASE_URL+'/job/'+ id)
								.then(function(response) {
									$rootScope.selectedJob = response.data;
									return response.data;
								},
								function(errResponse) {
									console.error('Error while Fetching Job.');
									return $q.reject(errResponse);
								});
				},
				
				fetchAllJobs : function() {
					console.log("--> JobService : calling 'fetchAllJobs' method.");
					return $http
								.get(BASE_URL + '/jobs')
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while fetching Jobs');
									return $q.reject(errResponse);
								});
				},
				
				createJob : function(job) {
					console.log("--> JobService : calling 'createJob' method.");
					return $http
								.post(BASE_URL + '/job/', job)
								.then(function(response) {
									return response.data;
								}, 
								function(errResponse) {
									console.error('Error while creating job');
									return $q.reject(errResponse);
								});
				}
				

			};
		}]);
				
				
				
				
				
