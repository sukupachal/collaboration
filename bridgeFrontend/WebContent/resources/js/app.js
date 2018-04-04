var app = angular.module('myApp', [ 'ngRoute' ]);		//we need to include 'ngRoute' and 'ngCookies' as dependency in the application module.

app.config(function($routeProvider) {		//We can use config() block to inject only providers and constants in our AngularJS application.
										//The providers basically create new instances, but only once for each provider.
	$routeProvider				//With the $routeProvider we can define what page to display when a user clicks a link.

	/**
	 * Home page mapping
	 */
	.when('/', {							//when(path, route) where path is string type and route is object type
		templateUrl : 'home/home.html',			//'templateUrl' is used to specify the path of the view file that will load 
		controller : 'HomeController as ctrl'		//'controller' is used to specify the particular controller for this 'path' or view.
	})

		
	/**
	 * Blog related mapping
	 */

	.when('/create_blog', {
		templateUrl : 'blog/create_blog.html',
		controller : 'BlogController as ctrl'
	})

	.when('/list_blogs', {
		templateUrl : 'blog/list_blogs.html',
		controller : 'BlogController as ctrl'
	})

	.when('/view_blog', {
		templateUrl : 'blog/view_blog.html',
		controller : 'BlogController as ctrl'
	})

	
	.when('/list_newblogs', {
		templateUrl : 'blog/list_newblogs.html',
		controller : 'BlogController as ctrl'
	})
	
	
	
	/**
	 * Forum related mapping
	 */

	.when('/create_forum', {
		templateUrl : 'forum/create_forum.html',
		controller : 'ForumController as ctrl'
	})

	.when('/list_forum', {
		templateUrl : 'forum/list_forums.html',
		controller : 'ForumController as ctrl'
	})

	.when('/view_forum', {
		templateUrl : 'forum/view_forum.html',
		controller : 'ForumController as ctrl'
	})
	
		/**
	 * Job related mapping
	 */

	.when('/create_job', {
		templateUrl : 'job/create_job.html',
		controller : 'JobController as ctrl'
	})

	.when('/list_job', {
		templateUrl : 'job/list_jobs.html',
		controller : 'JobController as ctrl'
	})

	.when('/view_job', {
		templateUrl : 'job/view_job.html',
		controller : 'JobController as ctrl'
	})


	
		
		/**
	 * User related mapping
	 */

	.when('/create_user', {
		templateUrl : 'user/create_user.html',
		controller : 'UserController as ctrl'
	})

	.when('/list_user', {
		templateUrl : 'user/list_users.html',
		controller : 'UserController as ctrl'
	})

	.when('/view_user', {
		templateUrl : 'user/view_user.html',
		controller : 'UserController as ctrl'
	})
	.when('/login', {
		templateUrl : 'user/login.html',
		controller : 'UserController as ctrl'
	})
	.when('/myprofile', {
    templateUrl : 'myprofile/myprofile.html',
    controller : 'UserController as ctrl'
   
  })
  
	
	
		/**
	 * Friend related mapping
	 */

	.when('/friend', {
		templateUrl : 'friend/friend.html',
		controller : 'FriendController as ctrl'
	})
	.when('/search_friend', {
		templateUrl : 'friend/search_friend.html',
		controller : 'FriendController as ctrl'
	})
	.when('/view_friend', {
		templateUrl : 'friend/view_friend.html',
		controller : 'FriendController as ctrl'
	})



	
	
	
	/**
	 * If anything goes wrong then this mapping will handle the request...
	 */

	.otherwise({			//If none of the above link has been clicked, then 'otherwise' method get called.
		redirectTo : '/'		// otherwise method redirects to '/' path if path given in wrong way
	});
});

app.run(function($rootScope, $location) {		//run() block gives us facility to inject any instance and constants in our application.
	console.log("--> app : entered app.run");

	$rootScope.$on('$locationChangeStart', function(event, next, current) {		//The $locationChangeStart event can be used to prevent a location change going forward.
		console.log("--> $rootScope.$on <--");
		// redirect to login page if try to access any other page rather than the restricted pages
		var restrictedPage = $.inArray($location.path(), [ '/', 
		                                                   '/login', 
		                                                   '/logout', 
		                                                   '/register', 
		                                                   '/list_blogs', 
		                                                   '/view_blog', 
		                                                   '/about', 
		                                                   '/list_event',
		                                                   '/view_event', 
		                                                   '/list_forum',
		                                                    
		                                                   '/search_job', 
		                                                   '/view_job_details',
		                                                   '/chat',
		                                                   '/myprofile']) === -1;
		console.log("location "+$location.path());
		console.log("restrictedPage : " + restrictedPage);
		var loggedIn = $rootScope.currentUser;		//taking currentUser.id in $rootScope as 'loggedIn' so that we can use it throughout the session. 

		console.log("loggedIn : " + loggedIn);
		if (restrictedPage && !loggedIn) {
			console.log("Navigating to login page.");
			$location.path('/login');
		}
	});


	// keep user logged in after page refresh...
	
	  /*$rootScope.currentUser = $cookieStore.get('currentUser') || {}; if
	  ($rootScope.currentUser) { $http.defaults.header.common['Authorization'] =
	  'Basic' + $rootScope.currentUser; }*/
	 
});  // app.run
