'use strict';

app.controller('JobController', [
		'$scope',
		'JobService',
		'$location',
		'$rootScope',
		function($scope, JobService, $location, $rootScope) {
			console.log("JobController...")
			
			var self = this;
			self.job = {
			    errorCode : '',
				errorMessage : '',
				jobId : '',
				jobProfile : '',
				forumComment : '',
				forumCommentDate : '',
				userId : '',
				userName : ''
				}
			self.jobs = [];
			self.getSelectedJob = function(id) {
				console.log("-->JobController : calling getSelectedJob method : getting job with id : " + id);
				JobService.getSelectedJob(id).then(
						function(d) {
							self.job = d;
							$location.path('/view_job');
						}, 
						function(errResponse) {
							console.error('Error while fetching Job...');
						});
			};
			
			self.fetchAllJobs = function() {
				console.log("--> JobController : calling fetchAllJobs method.");
				JobService.fetchAllJobs().then(
						function(d) {
							self.Jobs = d;
						}, function(errResponse) {
							console.error('Error while fetching Jobs...');
						});
			};
			
			self.createJob = function(job) {
				console.log("--> JobController : calling createJob method.");
				JobService.createJob(job).then(
						function(d) {
							self.jobs = d;
							alert('Job Created Successfully...')
						},
						function(errResponse) {
							console.error('Error while creating job...');
						});
			};
			
			
			
			self.fetchAllJobs();

			/*****************************************************************************/
					
					self.submit = function() {
						{
							console.log("--> JobController : calling submit() method.", self.job);
							self.createBlog(self.job);
							console.log('Saving new Job', self.job);
						}
						self.reset();
					};			
					
					
					self.reset = function() {
						console.log('submit a new Job', self.job);
						self.job = {
								    errorCode : '',
									errorMessage : '',
									jobId : '',
									jobProfile : '',
									forumComment : '',
									forumCommentDate : '',
									userId : '',
									userName : ''
									
						};
						$scope.myForm.$setPristine(); // reset form...
					};
				} ]);







