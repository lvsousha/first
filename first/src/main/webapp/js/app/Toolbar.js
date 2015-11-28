Ext.define('App.Toolbar', {
	extend: 'Ext.toolbar.Toolbar',

	modelName: '',

	bookmarkText: 'Bookmark',
	allrecordsText: 'All Records',

	saveSthText: 'Save {0}',
	saveasSthText: 'Save {0} as',

	newText: '新建',
	saveText: '保存',
	resetText: '重置',
	deleteText: '删除',
	prevText: '上一页',
	nextText: '下一页',
	exportText: '导出',
	printText: 'Print',

	tooltips: {
		setupSth: 'Setup {0}',
		newSth: 'New {0}',
		saveSth: 'Save {0}',
		reset: 'Reset',
		deleteSth: 'Delete {0}',
		prevSth: 'Previous {0}',
		nextSth: 'Next {0}',
		'export': 'Export',
		print: 'Print'
	},

	constructor: function(config) {
		var me = this;
		config = config || {};
		me.modelName = config.modelName || '';
		config.items = [{
			xtype: 'button',
			itemId: 'new',
			text: me.newText,
			tooltip: Ext.String.format(me.tooltips.newSth, me.modelName),
			handler: function(btn, e) {
				if(App.cancelEdit())
					App.openEditTab();
			},
			iconCls: 'y-action-new'
		},{
			xtype: 'button',
			itemId: 'save',
			text: me.saveText,
//			tooltip: Ext.String.format(me.tooltips.saveSth, me.modelName),
			handler: function(btn, e) {
				var ap = App.getActionPanel();
				if(ap && Ext.isFunction(ap.save))
					ap.save();
			},
			iconCls: 'y-action-save'
		},{
			xtype: 'button',
			itemId: 'reset',
			text: me.resetText,
//			tooltip: Ext.String.format(me.tooltips.reset, me.modelName),
			handler: function(btn, e) {
				var ap = App.getActionPanel();
				if(ap && Ext.isFunction(ap.reset))
					ap.reset();
			},
			iconCls: 'y-action-reset'
		},{
			xtype: 'tbseparator'
		},{
			xtype: 'button',
			itemId: 'delete',
			text: me.deleteText,
//			tooltip: Ext.String.format(me.tooltips.deleteSth, me.modelName),
			handler: function(btn, e) {
				var ap = App.getActionPanel();
				if(ap && Ext.isFunction(ap.del))
					ap.del();
			},
			iconCls: 'y-action-delete'
		},{
			xtype: 'tbseparator'
		},{
			xtype: 'button',
			itemId: 'prev',
			text: me.prevText,
//			tooltip: Ext.String.format(me.tooltips.prevSth, me.modelName),
			iconCls: 'y-action-prev',
			handler: function(){
                App.getTab('list').previous();
			}
		},{
			xtype: 'button',
			itemId: 'next',
			text: me.nextText,
//			tooltip: Ext.String.format(me.tooltips.nextSth, me.modelName),
			iconCls: 'y-action-next',
			handler: function(){
                App.getTab('list').next();
			}
		},{
			xtype: 'tbseparator'
		},{
			xtype: 'button',
			itemId: 'export',
			text: me.exportText,
//			tooltip: me.tooltips['export'],
			iconCls: 'y-action-export'
		},{
			xtype: 'button',
			itemId: 'print',
			text: me.printText,
//			tooltip: me.tooltips.print,
			iconCls: 'y-action-print'
		}];
		config.listeners = {
				
		};
		me.callParent([config]);
	}
});
