<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<head>
<link rel="stylesheet" type="text/css"  href="../extjs/ext-theme-classic/ext-theme-classic-all.css" />
<script type="text/javascript"   src="../extjs/ext-all.js"></script>
<script type="text/javascript"  src="../extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript"  src="../extjs/model.js"></script>
<script type="text/javascript"  src="../js/model/model.js"></script>
<script type="text/javascript"  src="../js/app/App.js"></script>
<style type="text/css">
.user{background: url(../images/user.png) no-repeat 2px 2px;}
.key{background: url(../images/key.png) no-repeat 2px 2px;}
.Userkey{background: url(../images/Userkey.png) no-repeat 2px 2px;}
.key,.user,.Userkey{
            background-color: #FFFFFF;
            padding-left: 20px;
            font-size: 12px;
        }
.bgimage {
   background:url(../images/logo.jpg ) no-repeat top;
   position:absolute;
}
</style>
</head>

<script type="text/javascript">
Ext.onReady(function() {
    var userLoginPanel = Ext.create('Ext.panel.Panel', {
//        bodyCls: 'bgimage',
        border: false,
        defaults:{
            margin:'58 0'
        },
        items:{
            xtype: 'tabpanel',
            id: 'loginTabs',
            activeTab: 0,
            height: 300,
            border: false,
            items:[{
                title: "身份认证",
                xtype: 'form',
                id: 'loginForm',
                defaults: {
                    width: 260,
                    margin: '10 0 0 70'
                },
                defaultType: 'textfield',
                labelWidth: 40,
                items: [{
                    fieldLabel: '用户名',
                    cls: 'user',
                    name: 'name',
                    labelAlign:'right',
                    labelWidth:65,
                    maxLength: 30,
                    emptyText:'请在这里填写用户名',
                    maxLengthText: '账号的最大长度为30个字符',
                    blankText:"用户名不能为空，请填写！",//错误提示信息，默认为This field is required!
                    allowBlank: false
                },{
                    fieldLabel: '密   码',
                    cls: 'key',
                    name: 'password',
                    inputType:"password",
                    labelWidth:65,
                    labelAlign:'right',
                    emptyText:'请在这里填写密码',
                    maxLengthText :'密码长度不能超过20',
                    maxLength: 128,
                    blankText:"密码不能为空，请填写！",//错误提示信息，默认为This field is required!
                    allowBlank: false
                },{
                    xtype:"combo",
                    fieldLabel: '角   色',
                    cls: 'Userkey',
                    name: 'role',
                    labelAlign:'right',
                    labelWidth:65,
                    store:["管理员","校领导","教职工"],//数据源为一数组
                    emptyText:'请选择角色.',
                    blankText:"请选择角色！",//错误提示信息，默认为This field is required!
                    allowBlank: false
                },{
                	fieldLabel: '验证码',
                    labelWidth:65,
                    name: 'code',
                    labelAlign:'right',
                    allowBlank: false
                },{
                	xtype:'image',
                	id:'code_image',
                	src:'login/code',
                	alt:'aaa'
                },{
                	xtype:'button',
                	text: 'Click me',
                	height:'20',
                	handler:function(){
               		var code_image = Ext.getCmp('code_image');
                		console.log('in');
                	}
                }]
            },{
                title: '关于',
                contentEl: 'aboutDiv',
                layout:'fit'
            }]
        }
    });

    Ext.create('Ext.window.Window', {
        title: 'ExtJs学习系统',
        width: 400,
        height: 500,
        layout: 'fit',
        plain: true,
        modal: true,
        maximizable: false,
        draggable: false,
        closable: false,
        resizable: false,
        items: userLoginPanel,
        // 重置 和 登录 按钮.
        buttons: [{
            text: '重置',
            iconCls: 'Wrench',
            handler: function() {
                Ext.Msg.alert('提示', '重置！');
            }
        }, {
            text: '登录',
            iconCls: 'User',
            handler: function() {
                Ext.Ajax.request({
                	url:'login/authen',
                	params:Ext.getCmp('loginForm').getForm().getValues(),
                	success: function(response, opts){                		 
//                		window.open("http://www.jb51.net"); 
                		var obj = Ext.decode(response.responseText);
                		if(obj.success == true){
                			window.location.href=obj.url;
                		}else{
                			alert(obj.errorMessage);
                		}               		
                	},
                    failure: function(response, opts) {
                        alert('failure');
                    }
                })
            }
        }]
    }).show();
});
</script>
<body>
	<div id="aboutDiv" class="x-hidden">
        <img border="0" src="../images/logo.jpg" />
    </div>
    <div id="aboutDiv1" class="x-hidden"
        style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
    思考者日记网ExtJs学习系统 v1.0
    ©2013-2014 思考者日记网|束洋洋个人博客（沪ICP备13005070）
    官方网站:<a href="http://www.shuyangyang.com.cn" target="_blank">[url=http://www.shuyangyang.com.cn]www.shuyangyang.com.cn[/url]</a>
    </div>
</body>
</html>