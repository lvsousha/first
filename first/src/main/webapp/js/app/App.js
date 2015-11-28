Ext.define('App', {
	singleton: true,

	menuTitle: 'Menu',

	leaveMessage: 'Leave this page will lose your unsaved data!',

	editTab: '',

	idProperty: 'id',

	currentId: null,

	dirty: false,

	init: function(config) {
		var me = this;
		Ext.apply(this, config);
		Ext.EventManager.on(window, 'beforeunload', me.onBeforeUnload, this);
	},

	createContentPanel: function(config) {
		config = config || {};

		return Ext.create('Ext.tab.Panel', {
			id: 'mainContent',
			plain: false,
			tbar: Ext.create('App.Toolbar', {
				itemId: 'mainToolbar',
				modelName: config.modelName || ''
			}),
			items: config.tabs || [],
			listeners: {
//				beforetabchange: function(panel, newCard, oldCard, eOpts) {
//					var newPanel = App.getActionPanel(newCard);
//					var oldPanel = App.getActionPanel(oldCard);
//					if(App.currentId == null && !Ext.isFunction(newPanel.editNew))
//						return false;
//					if(Ext.isFunction(oldPanel.cancel))
//						return oldPanel.cancel();
//					else
//						return true;
//				},
//				tabchange: function(panel, newCard, oldCard, eOpts) {
//					var ap = App.getActionPanel(newCard);
//					if(App.currentId)
//						ap.edit(App.currentId);
//					else
//						ap.editNew();
//				}
			}
		});
	},

	getContentPanel: function() {
		return Ext.getCmp('mainContent');
	},

	getTab: function(tab) {
		if(Ext.isEmpty(tab)) return this.getContentPanel().getActiveTab();
		else return this.getContentPanel().child('#' + tab);
	},

	getActionPanel: function(tab) {
		var card;
		if(Ext.isEmpty(tab) || Ext.isString(tab))
			card = this.getTab(tab);
		else
			card = tab;
		var form = card.child('form');
		if(form) return form;
		else return card;
	},

	getToolbar: function() {
		return this.getContentPanel().getDockedComponent('mainToolbar');
	},

	disableAllButton: function(){
		var each=Ext.each(Ext.ComponentQuery.query('#mainToolbar button'), function(item){
			item.disable();
		});
	},
	getButton: function(name) {
		return this.getToolbar().child('#' + name);
	},

	isDirty: function() {
		return this.dirty;
	},

	setDirty: function(dirty) {
		this.dirty = dirty;
	},

	cancelEdit: function() {
		var me = this;
		var panel = me.getActionPanel(me.getTab());
		if(Ext.isFunction(panel.cancel))
			return panel.cancel();
		else
			return true;
	},

	openEditTab: function(id, params) {
		this.openTab(this.editTab, id, params);
	},

	openTab: function(tab, id, params) {
		var panel = this.getContentPanel();
		var card = this.getTab(tab);
		if(card != null) {
			panel.suspendEvents();
			if(!card.active)
				panel.setActiveTab(card);
			var ap = this.getActionPanel(card);
			if(Ext.isEmpty(id)) {
				if(Ext.isFunction(ap.editNew))
					ap.editNew(params);
			} else {
				if(Ext.isFunction(ap.edit))
					ap.edit(id, params);
			}
			panel.resumeEvents();
		}
	},

	// private
	onBeforeUnload: function(e) {
		var me = this;
		var panel = me.getActionPanel(me.getTab());
		if(Ext.isFunction(panel.isDirty)) {
			if(panel.isDirty()) {
				e.browserEvent.returnValue = me.leaveMessage;
			}
		}
	}

});
