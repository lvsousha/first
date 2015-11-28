<#macro layout_init>
	Eap.init({
		contextPath: '${request.contextPath}',
		theme: '${action.user.theme?js_string}',
		lang: '${action.lang?js_string}',
		dateFormat: '${action.eapLocale.dateFormat?js_string}',
		datetimeFormat: '${action.eapLocale.datetimeFormat?js_string}',
		timeFormat: '${action.eapLocale.timeFormat?js_string}',
		messagecheckinterval: ${action.getParameter("eap", "messagecheckinterval")*1000}
	});

	Eap.layout.Default.createMenu({
		store: <@menutree action.mainMenus/>
	});
	Eap.layout.Default.createBanner({username: '${action.user.fullname!?js_string}'});
	function getTop(e){
		var offset=e.offsetTop;
		if(e.offsetParent!=null) offset+=getTop(e.offsetParent);
		return offset;
	}
</#macro>
<#macro layout_do>
	Eap.layout.Default.layout();
</#macro>

<#macro menuIcon menu>
	<#if menu.icon?? && menu.icon?length gt 0>
	icon: '${request.contextPath}' + '/static/' + Ext.String.format('${menu.icon!?js_string}', Eap.theme),
	<#else>
	<#if menu.uri?? && menu.uri.icon?? && menu.uri.icon?length gt 0>
	icon: '${request.contextPath}' + '/static/' + Ext.String.format('${menu.uri.icon!?js_string}', Eap.theme),
	</#if>
	</#if>
</#macro>

<#macro defaultMenuitems menus>
[
	<#list menus?if_exists as menu>
	{
		<@menuIcon menu/>
		<#if menu.folder>
		floatMenu: [<@floatMenuChildren menu/>],
		disableClick: true,
		text: <#if menu.name == "-"&& menu.uri??>'${menu.uri.name!?js_string}<img src="${request.contextPath}/static/images/default/action/more.png"  style=" right:3px;position:absolute;">'<#else>'${menu.name!?js_string}<img src="${request.contextPath}/static/images/default/action/more.png" style="right:3px;position:absolute;">'</#if>,
		<#else>
		text: <#if menu.name == "-"&& menu.uri??>'${menu.uri.name!?js_string}'<#else>'${menu.name!?js_string}'</#if>,
		</#if>
		<#if menu.uri?? &&  menu.uri.uri??>
		uri: <#if menu.uri.uri?starts_with("/")>Eap.contextPath + '${menu.uri.uri}'<#else>'${menu.uri.uri}'</#if>,
		</#if>
		leaf: true
	}<#if menu_has_next>,</#if>
	</#list>
]
</#macro>

<#macro floatMenuChildren father>
	new Ext.menu.Menu({
		items:[
			<#list father.submenus?if_exists as sub>
			{
				text:<#if sub.name == "-"&& sub.uri??>'${sub.uri.name!?js_string}'<#else>'${sub.name!?js_string}'</#if>,
				<#if sub.folder>
				menu: <@floatMenuChildren sub/>,
				</#if>
				<@menuIcon sub/>
				handler: function(){
				      <#if sub.uri?? &&  sub.uri.uri??>
					      window.location = <#if sub.uri.uri?starts_with("/")>Eap.contextPath + '${sub.uri.uri}'<#else>'${sub.uri.uri}'</#if>;
					  </#if>
				}
			}<#if sub_has_next>,</#if>
			</#list>
		]
	})
</#macro>

<#macro menutree menus>
[
	<#list menus?if_exists as menu>
	{
		title: <#if menu.name == "-"&& menu.uri??>'${menu.uri.name!?js_string}'<#else>'${menu.name!?js_string}'</#if>,
		<@menuIcon menu/>
		layout: 'fit',
		stateful: true,
		stateId: 'defaultMenu-${menu.id}',
		<#if menu.folder>
		items: [{
			xtype: 'treepanel',
			rootVisible: false,
			useArrows: true,
			listeners: {
				afterrender: function(panel){
					var currentUrl = unescape(document.URL);
			        var location = currentUrl.substr(currentUrl.indexOf(Eap.contextPath),currentUrl.length);
			        Ext.Array.each(panel.getRootNode().childNodes,function(node){
			        	var uri = node.get('uri');
			        	if(!uri || !location ) return;
			        	if(uri.indexOf('?') !== -1){
			        		if(uri === location)
			        			panel.getSelectionModel().select(node);
			        	}else{
			        		if((location.indexOf('?') !== -1 && location.substr(0,location.indexOf('?')) === uri) || uri === location){
			        			panel.getSelectionModel().select(node);
			        		}	
			        	}
			        });
				},
				itemclick: function(view,record){
					if(record.get('leaf')){
						window.location = record.get('uri');
					}
				},
				itemmouseenter:function(me,record,item, index, e, eOpts){
					if(record.raw.floatMenu)
						record.raw.floatMenu[0].showAt(me.getWidth(),getTop(item));
				},
				beforeitemclick: function(view,record,item, index, e, eOpts){
					if(record.raw.disableClick)
						return Ext.get(item).hasCls('x-item-disabled') ;
					else
					   return !Ext.get(item).hasCls('x-item-disabled');
				}
			},
			store: Ext.create('Ext.data.TreeStore', {
					model: 'Eap.model.Menu',
					root: {
				        expanded: true,
				        children: <@defaultMenuitems menu.submenus![]/>
			        	}
					})
        }]
		<#else>
		items: [{
			xtype: 'treepanel',
			rootVisible: false,
			useArrows: true,
			listeners: {
				itemclick: function(view,record){
					if(record.get('leaf')){
						window.location = record.get('uri');
					}
				}
			},
			store: Ext.create('Ext.data.TreeStore', {
				model: 'Eap.model.Menu',
					root: {
				        expanded: true,
				        children: [{
				        	text: '${menu.name?js_string}',
							<@menuIcon menu/>
				        	<#if menu.uri??>
				        	uri: <#if menu.uri.uri?starts_with("/")>Eap.contextPath + '${menu.uri.uri}'<#else>'${menu.uri.uri}'</#if>,
				        	</#if>
				        	leaf: true
			        	}]
		        	}
			})
		}]
		</#if>
	}<#if menu_has_next>,</#if>
	</#list>
]
</#macro>

