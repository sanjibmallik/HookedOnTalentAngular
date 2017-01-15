'use strict';

angular.module('hot.services', []).
	service('$menuItems', function()
	{
		this.menuItems = [];

		var $menuItemsRef = this;

		var menuItemObj = {
			parent: null,

			title: '',
			link: '', // starting with "./" will refer to parent link concatenation
			state: '', // will be generated from link automatically where "/" (forward slashes) are replaced with "."
			icon: '',

			isActive: false,
			label: null,

			menuItems: [],

			setLabel: function(label, color, hideWhenCollapsed)
			{
				if(typeof hideWhenCollapsed == 'undefined')
					hideWhenCollapsed = true;

				this.label = {
					text: label,
					classname: color,
					collapsedHide: hideWhenCollapsed
				};

				return this;
			},

			addItem: function(title, link, icon)
			{
				var parent = this,
					item = angular.extend(angular.copy(menuItemObj), {
						parent: parent,

						title: title,
						link: link,
						icon: icon
					});

				if(item.link)
				{
					if(item.link.match(/^\./))
						item.link = parent.link + item.link.substring(1, link.length);

					if(item.link.match(/^-/))
						item.link = parent.link + '-' + item.link.substring(2, link.length);

					item.state = $menuItemsRef.toStatePath(item.link);
				}

				this.menuItems.push(item);

				return item;
			}
		};

		this.addItem = function(title, link, icon)
		{
			var item = angular.extend(angular.copy(menuItemObj), {
				title: title,
				link: link,
				state: this.toStatePath(link),
				icon: icon
			});

			this.menuItems.push(item);

			return item;
		};

		this.getAll = function()
		{
			return this.menuItems;
		};

		this.prepareSidebarMenu = function()
		{
			var dashboard    = this.addItem('Dashboard', 		'/app/dashboard', 			'linecons-cog');
			var users    = this.addItem('Users', 		'/app/users', 			'linecons-user');
            var clients    = this.addItem('Clients', 		'/app/clients', 			'linecons-heart');
			var requirements    = this.addItem('Requirements', 		'/app/requirements', 			'linecons-tag');
			var candidates    = this.addItem('Candidates', 		'/app/candidates', 			'linecons-graduation-cap');
			var questionBank    = this.addItem('Question Bank', 		'/app/questionBank', 			'linecons-lightbulb');

			


				
			// Subitems of Users
				users.addItem('Create User', 	'-/Create-User'); // "-/" will append parents link
				users.addItem('Display Users', 	'-/Display-Users');

            // Subitems of Clients
            clients.addItem('Create Client', 	'-/Create-Client'); // "-/" will append parents link
            clients.addItem('Display Clients', 	'-/Display-Clients');

            // Subitems of Requirements
            requirements.addItem('Create Requirement', 	'-/Create-Requirement'); // "-/" will append parents link
            requirements.addItem('Display Requirement', 	'-/Display-Requirement');

            // Subitems of questions
            questionBank.addItem('Add Questions', 	'-/Add-Questions'); // "-/" will append parents link
            questionBank.addItem('Display Questions', 	'-/Display-Questions');

            // Subitems of Candidates
            candidates.addItem('Add Candidate', 	'-/Add-Candidate'); // "-/" will append parents link
            candidates.addItem('Display Candidates', 	'-/Display-Candidates');
			


			return this;
		};

		this.prepareHorizontalMenu = function()
		{
			var dashboard    = this.addItem('Dashboard', 		'/app/dashboard', 			'linecons-cog');
			return this;
		}

		this.instantiate = function()
		{
			return angular.copy( this );
		}

		this.toStatePath = function(path)
		{
			return path.replace(/\//g, '.').replace(/^\./, '');
		};

		this.setActive = function(path)
		{
			this.iterateCheck(this.menuItems, this.toStatePath(path));
		};

		this.setActiveParent = function(item)
		{
			item.isActive = true;
			item.isOpen = true;

			if(item.parent)
				this.setActiveParent(item.parent);
		};

		this.iterateCheck = function(menuItems, currentState)
		{
			angular.forEach(menuItems, function(item)
			{
				if(item.state == currentState)
				{
					item.isActive = true;

					if(item.parent != null)
						$menuItemsRef.setActiveParent(item.parent);
				}
				else
				{
					item.isActive = false;
					item.isOpen = false;

					if(item.menuItems.length)
					{
						$menuItemsRef.iterateCheck(item.menuItems, currentState);
					}
				}
			});
		}
	});