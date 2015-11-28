Ext.define('Eap.layout.Default', {
	singleton: true,

	menuTitle: 'Menu',

	welcomeText: '{0} Welcome',

	texts: {
		about: 'About',
		exit: 'Exit',
		home: 'Home',
		profile: 'Profile'
	},

	tooltips: {
		about: 'About',
		collapse: 'Collapse',
		exit: 'Exit',
		expand: 'Expand',
		home: 'Home',
		profile: 'Profile'
	},

	createMenu: function(config) {
		var me = this;
		store = config.store || {};
		var menu = Ext.create('Ext.panel.Panel', {
			id : 'mainMenu',
			region:'west',
			title: me.menuTitle,
			split: true,
			collapsible: true,
			width: 150,
			minWidth: 50,
			maxWidth: 300,
			stateful: true,
			layout: {
				type: 'accordion',
				titleCollapse: true,
				animate: true
			},
			items: store
		});
		return menu;
	},

	createBanner: function(config) {
		config = config || {};
		var me = this;
		var banner = Ext.create('Ext.toolbar.Toolbar', {
			width: '100%',
			height: 48,
			id: 'banner',
			region:'north',
			border: false,
			items: ['->',{
				xtype: 'tbtext',
				text: Ext.String.format(me.welcomeText, config.username),
				cls: 'my-text'
			},
			{
				iconCls: 'y-action-message',
				text: '',
				cls: 'my-btn',
				handler: function() {
					window.location = Eap.contextPath + '/eap/messages/inbox';
				},
				listeners:{
					afterrender: function(me, eOpts ){
						function messageRequest() {
							Ext.Ajax.request({
								url: Eap.contextPath + '/eap/messages/message-num.gson',
								success: function(response){
									var text = response.responseText;
									if(Ext.isNumeric(text)) {
										var num = Ext.Number.from(text);
										if(num === 0) {
											me.setText('');
											me.setIconCls('y-action-message');
										} else {
											me.setText(num);
											me.setIconCls('y-action-message-notice');
										}
									}
								}
							});
						}
						if(Eap.messagecheckinterval > 0) {
							Ext.TaskManager.start({
								run: function () {
									messageRequest();
								},
								interval: Eap.messagecheckinterval
							});
						} else {
							Ext.TaskManager.start({
								run: function () {
									messageRequest();
								},
								interval: Eap.messagecheckinterval,
								repeat: 1
							});
						}
					}
				}
			},
			'-',
			{
				iconCls: 'y-action-profile',
				text: me.texts.profile,
				tooltip: me.tooltips.profile,
				cls: 'my-btn',
				handler: function() {
					window.location = Eap.contextPath + '/eap/profile/index';
				}
			},
			'-',
			{
				iconCls: 'y-action-home',
				text: me.texts.home,
				tooltip: me.tooltips.home,
				cls: 'my-btn',
				handler: function() {
					window.location = Eap.contextPath + '/index';
				}
			},
			'-',
			{
				iconCls: 'y-action-about',
				text: me.texts.about,
				tooltip: me.tooltips.about,
				cls: 'my-btn',
				handler: function() {
					var about = Ext.create('Eap.AboutWindow');
					about.show();
				}
			},
			'-',
			{
				iconCls: 'y-action-exit',
				text: me.texts.exit,
				tooltip: me.tooltips.exit,
				cls: 'my-btn',
				handler: function() {
					window.location = Eap.contextPath + '/logout';
				}
			}]
		});
		return banner;
	},

	layout: function() {
		var content = Ext.getCmp('mainContent');
		if(content == null)
			content = {
				id: 'mainContent',
				html: ''
			};
		Ext.create('Ext.Viewport', {
			layout:'border',
			stateful: true,
				items: [Ext.getCmp('mainMenu'), {
				region:'center',
				border: false,
				layout: 'fit',
				items: content
			}, Ext.getCmp('banner')]
		});
	}

});
