<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<head>
<link rel="stylesheet" type="text/css"  href="${request.contextPath}/extjs/ext-theme-classic/ext-theme-classic-all.css" />
<script type="text/javascript"   src="${request.contextPath}/extjs/ext-all.js"></script>
<script type="text/javascript"  src="${request.contextPath}/extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript"  src="${request.contextPath}/extjs/model.js"></script>
<script type="text/javascript"  src="${request.contextPath}/js/model/model.js"></script>
<script type="text/javascript"  src="${request.contextPath}/js/app/App.js"></script>
</head>

<script type="text/javascript">
Ext.onReady(function(){

	<#include  "file/tree.ftl">
	<#include  "file/form.ftl">
	<#include  "file/all.ftl">

	var content = Ext.create('Ext.tab.Panel', {
		id: 'mainContent',
		plain: false,
		items:[ftlTab],
		listeners: {}
	});

	var menu = Ext.create('Ext.panel.Panel', {
		id : 'mainMenu',
		region:'west',
		title: '例子',
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
		}
	});

	Ext.create('Ext.Viewport', {
		layout:'border',
		stateful: true,
		padding: 3,
		items: [Ext.getCmp('mainMenu'),{
			region:'center',
			border: false,
			layout: 'fit',
			items:content
		}],
		renderTo: Ext.getBody()
	});


});
</script>
<body>
</body>
</html>