<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<head>
<link rel="stylesheet" type="text/css"  href="/first/extjs/ext-theme-classic/ext-theme-classic-all.css" />
<script type="text/javascript"   src="/first/extjs/ext-all.js"></script>
<script type="text/javascript"  src="/first/extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript"  src="/first/js/model/model.js"></script>
<script type="text/javascript"  src="/first/js/app/App.js"></script>
<script type="text/javascript"  src="/first/js/app/Toolbar.js"></script>
<script type="text/javascript"  src="/first/js/modules/westModule.js"></script>
<script type="text/javascript"  src="/first/js/modules/northModule.js"></script>
</head>

<script type="text/javascript">
Ext.onReady(function(){
	
//	var north = Ext.create('Ext.panel.Panel',{
//		id : 'North',
//		region:'north',
//		split: true,
//		collapsible: true,
//		height:100,
//		layout:'fit'
//	});
//	
//	var menu = Ext.create('Ext.panel.Panel', {
//		id : 'mainMenu',
//		region:'west',
//		title: '例子',
//		split: true,
//		collapsible: true,
//		width: 150,
//		minWidth: 50,
//		maxWidth: 300,
//		stateful: true,
//		layout: {
//			type: 'accordion',
//			titleCollapse: true,
//			animate: true
//		},
//		items:[],
//	});
	
	<#include  "hbjh/list.ftl">;
	<#include  "hbjh/linechart.ftl">;
	<#include  "hbjh/shareholder.ftl">;
	<#include  "hbjh/person.ftl">;

	App.createContentPanel({
		tabs: [basicTab,personTab,shareholderTab]
	});
	
	var content = Ext.getCmp('mainContent');
	Ext.create('Ext.Viewport', {
		layout:'border',
		stateful: true,
		padding: 3,
		items: [Ext.getCmp('mainMenu'), {
			region:'center',
			border: false,
			layout: 'fit',
			items: content
		},Ext.getCmp('North')],
		renderTo: Ext.getBody()
	});


       });
</script>
<body>
</body>
</html>