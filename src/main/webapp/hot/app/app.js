'use strict';

var app = angular.module('hot-app', [
	'ngCookies',
    'ngMessages',
	'ui.router',
	'ui.bootstrap',
	'oc.lazyLoad',
    'FBAngular',
    'pagerApp',
	'hot.controllers',
	'hot.directives',
	'hot.factory',
	'hot.services',
    'hot.userControllers',
    'hot.clientControllers',
    'hot.clientFactory',
    'hot.settingControllers',
    'hot.settingFactory',
    'hot.userFilter',
    'hot.profileControllers',
    'hot.profileFactory',
    'ngTable',
    'ngImgCrop',
    'ngFileUpload',
    'ngAnimate',
    'toastr'


]);

app.run(function()
{
	// Page Loading Overlay
	public_vars.$pageLoadingOverlay = jQuery('.page-loading-overlay');

	jQuery(window).load(function()
	{
		public_vars.$pageLoadingOverlay.addClass('loaded');
	})
});


app.config(function($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, ASSETS){

	$urlRouterProvider.otherwise('/login');

	$stateProvider.
		// Main Layout Structure
		state('app', {
			abstract: true,
			url: '/app',
			templateUrl: appHelper.templatePath('layout/app-body'),
			controller: function($rootScope){
				$rootScope.isLoginPage        = false;
				$rootScope.isLightLoginPage   = false;
				$rootScope.isLockscreenPage   = false;
				$rootScope.isMainPage         = true;
			}
		}).

		// Dashboards
		state('app.dashboard', {
			url: '/dashboard',
			templateUrl: appHelper.templatePath('dashboard'),
			resolve: {
				resources: function($ocLazyLoad){
					return $ocLazyLoad.load([
						ASSETS.charts.dxGlobalize,
						ASSETS.extra.toastr
					]);
				},
				dxCharts: function($ocLazyLoad){
					return $ocLazyLoad.load([
						ASSETS.charts.dxCharts
					]);
				}
			}
		}).
        state('login', {
            url: '/login',
            templateUrl: 'app/views/common/login.html',
            controller: 'LoginCtrl',
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.forms.jQueryValidate,
                        ASSETS.extra.toastr
                    ]);
                }
            }
        }).
		
		state('app.users-Create-User', {
			url: '/users-Create-User',
			templateUrl: appHelper.templatePath('users/Create-User'),
			resolve: {
				resources: function($ocLazyLoad){
					return $ocLazyLoad.load([
						ASSETS.charts.dxGlobalize,
						ASSETS.extra.toastr,
						ASSETS.core.bootstrap,
						ASSETS.core.jQueryUI,
						ASSETS.forms.jQueryValidate,
						ASSETS.forms.inputmask,
						ASSETS.forms.multiSelect,
						ASSETS.forms.datepicker,
						ASSETS.forms.selectboxit,
						ASSETS.forms.formWizard/*,
                        ASSETS.imageCrop.crop*/
					]);
				},
				dxCharts: function($ocLazyLoad){
					return $ocLazyLoad.load([
						ASSETS.charts.dxCharts
					]);
				}
			},
            controller: 'UIModalsCtrl'
		}).
		
		state('app.users-Display-Users', {
			url: '/users-Display-Users',
			templateUrl: appHelper.templatePath('users/Display-Users'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
		}).

        state('app.candidates-Add-Candidate', {
            url: '/candidates-Add-Candidate',
            templateUrl: appHelper.templatePath('candidates/Add-Candidate'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr,
                        ASSETS.core.bootstrap,
                        ASSETS.core.jQueryUI,
                        ASSETS.forms.jQueryValidate,
                        ASSETS.forms.inputmask,
                        ASSETS.forms.multiSelect,
                        ASSETS.forms.datepicker,
                        ASSETS.forms.selectboxit,
                        ASSETS.forms.formWizard
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        }).

        state('app.candidates-Display-Candidates', {
            url: '/candidates-Display-Candidates',
            templateUrl: appHelper.templatePath('candidates/Display-Candidates'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
        }).
        state('app.clients-Create-Client', {
            url: '/clients-Create-Client',
            templateUrl: appHelper.templatePath('clients/Create-Client'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr,
                        ASSETS.core.bootstrap,
                        ASSETS.core.jQueryUI,
                        ASSETS.forms.jQueryValidate,
                        ASSETS.forms.inputmask,
                        ASSETS.forms.multiSelect,
                        ASSETS.forms.datepicker,
                        ASSETS.forms.selectboxit,
                        ASSETS.forms.formWizard
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        }).

        state('app.clients-Display-Clients', {
            url: '/clients-Display-Clients',
            templateUrl: appHelper.templatePath('clients/Display-Clients'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
        }).
        state('app.questionBank-Add-Questions', {
            url: '/questionBank-Add-Questions',
            templateUrl: appHelper.templatePath('questionBank/Add-Questions'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr,
                        ASSETS.core.bootstrap,
                        ASSETS.core.jQueryUI,
                        ASSETS.forms.jQueryValidate,
                        ASSETS.forms.inputmask,
                        ASSETS.forms.multiSelect,
                        ASSETS.forms.datepicker,
                        ASSETS.forms.selectboxit,
                        ASSETS.forms.formWizard
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        }).

        state('app.questionBank-Display-Questions', {
            url: '/questionBank-Display-Questions',
            templateUrl: appHelper.templatePath('questionBank/Display-Questions'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
        }).
        state('app.requirements-Create-Requirement', {
            url: '/requirements-Create-Requirement',
            templateUrl: appHelper.templatePath('requirements/Create-Requirement'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr,
                        ASSETS.core.bootstrap,
                        ASSETS.core.jQueryUI,
                        ASSETS.forms.jQueryValidate,
                        ASSETS.forms.inputmask,
                        ASSETS.forms.multiSelect,
                        ASSETS.forms.datepicker,
                        ASSETS.forms.selectboxit,
                        ASSETS.forms.formWizard
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        }).

        state('app.requirements-Display-Requirement', {
            url: '/requirements-Display-Requirement',
            templateUrl: appHelper.templatePath('requirements/Display-Requirement'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
        }).

        state('app.requirements-Requirement-Candidates', {
            url: '/requirements-Requirement-Candidates',
            templateUrl: appHelper.templatePath('requirements/Requirement-Candidates'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
        }).

        state('app.requirements-Add-Resources', {
            url: '/requirements-Add-Resources',
            templateUrl: appHelper.templatePath('requirements/Add-Resources'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
        }).

        state('app.requirements-Display-Resources', {
            url: '/requirements-Display-Resources',
            templateUrl: appHelper.templatePath('requirements/Display-Resources'),
            resolve: {
                deps: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.tables.datatables
                    ]);
                }
            },
            controller: 'UIModalsCtrl'
        }).

        state('app.profile', {
            url: '/profile',
            templateUrl: appHelper.templatePath('profile'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr,
                        ASSETS.core.bootstrap,
                        ASSETS.core.jQueryUI,
                        ASSETS.forms.jQueryValidate,
                        ASSETS.forms.inputmask,
                        ASSETS.forms.multiSelect,
                        ASSETS.forms.datepicker,
                        ASSETS.forms.selectboxit,
                        ASSETS.forms.formWizard
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        }).

        state('app.settings', {
            url: '/settings',
            templateUrl: appHelper.templatePath('settings'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr,
                        ASSETS.core.bootstrap,
                        ASSETS.core.jQueryUI,
                        ASSETS.forms.jQueryValidate,
                        ASSETS.forms.inputmask,
                        ASSETS.forms.multiSelect,
                        ASSETS.forms.datepicker,
                        ASSETS.forms.selectboxit,
                        ASSETS.forms.formWizard
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        });
});


app.constant('ASSETS', {
	'core': {
		'bootstrap': appHelper.assetPath('js/bootstrap.min.js'), // Some plugins which do not support angular needs this

		'jQueryUI': [
			appHelper.assetPath('js/jquery-ui/jquery-ui.min.js'),
			appHelper.assetPath('js/jquery-ui/jquery-ui.structure.min.css')
		],

		'moment': appHelper.assetPath('js/moment.min.js'),

		'googleMapsLoader': appHelper.assetPath('app/js/angular-google-maps/load-google-maps.js')
	},

	'charts': {

		'dxGlobalize': appHelper.assetPath('js/devexpress-web-14.1/js/globalize.min.js'),
		'dxCharts': appHelper.assetPath('js/devexpress-web-14.1/js/dx.chartjs.js'),
		'dxVMWorld': appHelper.assetPath('js/devexpress-web-14.1/js/vectormap-data/world.js')
	},

	'xenonLib': {
		notes: appHelper.assetPath('js/xenon-notes.js')
	},

	'maps': {

		'vectorMaps': [
			appHelper.assetPath('js/jvectormap/jquery-jvectormap-1.2.2.min.js'),
			appHelper.assetPath('js/jvectormap/regions/jquery-jvectormap-world-mill-en.js'),
			appHelper.assetPath('js/jvectormap/regions/jquery-jvectormap-it-mill-en.js')
		]
	},

	'icons': {
		'meteocons': appHelper.assetPath('css/fonts/meteocons/css/meteocons.css'),
		'elusive': appHelper.assetPath('css/fonts/elusive/css/elusive.css')
	},

	'tables': {
		'rwd': appHelper.assetPath('js/rwd-table/js/rwd-table.min.js'),

		'datatables': [
			appHelper.assetPath('js/datatables/dataTables.bootstrap.css'),
			appHelper.assetPath('js/datatables/datatables-angular.js')
		]

	},

	'forms': {

		'select2': [
			appHelper.assetPath('js/select2/select2.css'),
			appHelper.assetPath('js/select2/select2-bootstrap.css'),

			appHelper.assetPath('js/select2/select2.min.js')
		],

		'daterangepicker': [
			appHelper.assetPath('js/daterangepicker/daterangepicker-bs3.css'),
			appHelper.assetPath('js/daterangepicker/daterangepicker.js')
		],

		'colorpicker': appHelper.assetPath('js/colorpicker/bootstrap-colorpicker.min.js'),

		'selectboxit': appHelper.assetPath('js/selectboxit/jquery.selectBoxIt.js'),

		'tagsinput': appHelper.assetPath('js/tagsinput/bootstrap-tagsinput.min.js'),

		'datepicker': appHelper.assetPath('js/datepicker/bootstrap-datepicker.js'),

		'timepicker': appHelper.assetPath('js/timepicker/bootstrap-timepicker.min.js'),

		'inputmask': appHelper.assetPath('js/inputmask/jquery.inputmask.bundle.js'),

		'formWizard': appHelper.assetPath('js/formwizard/jquery.bootstrap.wizard.min.js'),

		'jQueryValidate': appHelper.assetPath('js/jquery-validate/jquery.validate.min.js'),

		'dropzone': [
			appHelper.assetPath('js/dropzone/css/dropzone.css'),
			appHelper.assetPath('js/dropzone/dropzone.min.js')
		],

		'typeahead': [
			appHelper.assetPath('js/typeahead.bundle.js'),
			appHelper.assetPath('js/handlebars.min.js')
		],

		'multiSelect': [
			appHelper.assetPath('js/multiselect/css/multi-select.css'),
			appHelper.assetPath('js/multiselect/js/jquery.multi-select.js')
		],

		'icheck': [
			appHelper.assetPath('js/icheck/skins/all.css'),
			appHelper.assetPath('js/icheck/icheck.min.js')
		],

		'bootstrapWysihtml5': [
			appHelper.assetPath('js/wysihtml5/src/bootstrap-wysihtml5.css'),
			appHelper.assetPath('js/wysihtml5/wysihtml5-angular.js')
		]
	},

	'uikit': {
		'base': [
			appHelper.assetPath('js/uikit/uikit.css'),
			appHelper.assetPath('js/uikit/css/addons/uikit.almost-flat.addons.min.css'),
			appHelper.assetPath('js/uikit/js/uikit.min.js')
		],

		'codemirror': [
			appHelper.assetPath('js/uikit/vendor/codemirror/codemirror.js'),
			appHelper.assetPath('js/uikit/vendor/codemirror/codemirror.css')
		],

		'marked': appHelper.assetPath('js/uikit/vendor/marked.js'),
		'htmleditor': appHelper.assetPath('js/uikit/js/addons/htmleditor.min.js'),
		'nestable': appHelper.assetPath('js/uikit/js/addons/nestable.min.js')
	},

   /* 'imageCrop': {
        'crop': [
            appHelper.assetPath('js/cropImage/ng-img-crop.js'),
            appHelper.assetPath('css/cropImage/ng-img-crop.css')
            ]
    },*/

	'extra': {
		'tocify': appHelper.assetPath('js/tocify/jquery.tocify.min.js'),

		'toastr': appHelper.assetPath('js/toastr/toastr.min.js'),

		'fullCalendar': [
			appHelper.assetPath('js/fullcalendar/fullcalendar.min.css'),
			appHelper.assetPath('js/fullcalendar/fullcalendar.min.js')
		],

		'cropper': [
			appHelper.assetPath('js/cropper/cropper.min.js'),
			appHelper.assetPath('js/cropper/cropper.min.css')
		]
	}
});