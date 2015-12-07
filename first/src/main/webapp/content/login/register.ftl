var registerPanel = Ext.create('Ext.panel.Panel', {
        border: false,
        defaults:{
            margin:'58 0'
        },
        items:{
            xtype: 'tabpanel',
            id: 'register',
            activeTab: 0,
            height: 300,
            border: false,
            items:[{
                title: "Register",
                xtype: 'form',
                id: 'registerForm',
                defaults: {
                    width: 260,
                    margin: '10 0 0 70'
                },
                defaultType: 'textfield',
                labelWidth: 40,
                items: [{
                    fieldLabel: 'Email',
                    cls: 'user',
                    name: 'email',
                    labelAlign:'right',
                    labelWidth:65,
                    maxLength: 30,
                    emptyText:'imput Email',
                    blankText:"Email cant be empty",
                    vtype:'email',
                    allowBlank: false
                },{
                    fieldLabel: '密  码',
                    cls: 'key',
                    name: 'password',
                    inputType:"password",
                    labelWidth:65,
                    labelAlign:'right',
                    emptyText:'请在这里填写密码',
                    maxLengthText :'密码长度不能超过20',
                    maxLength: 128,
                    blankText:"密码不能为空，请填写！",
                    allowBlank: false
                },{
                	fieldLabel: '验证码',
                    labelWidth:65,
                    name: 'code',
                    labelAlign:'right',
                    allowBlank: false
                },{
                	xtype:'button',
                	text: '获取验证码',
                	height:'20',
                	handler:function(){
                		Ext.Ajax.request({
                        	url:'login/sendEmail',
                        	params:Ext.getCmp('registerForm').getForm().getValues(),
                        	success: function(response, opts){
                        		var obj = Ext.decode(response.responseText);
                        		if(obj.success == true){
                        			Ext.Msg.alert('提示', '发送成功');
                        		}else{
                        			Ext.Msg.alert('提示', '发送失败');
                        		}
                        	},
                            failure: function(response, opts) {
                                alert('failure');
                            }
                        })
                	}
                }]
            }]
        }
    });

var registerWindow = Ext.create('Ext.window.Window', {
    title: '用户注册',
    width: 500,
    height: 400,
    layout: 'fit',
    closeAction:'hide',
//    plain: true,
//    modal: true,
    items: registerPanel,
    buttons: [{
        text: '重置',
        iconCls: 'Wrench',
        handler: function() {
        	Ext.getCmp('registerForm').getForm().reset();
        }
    },{
        text: '确定',
        iconCls: 'User',
        handler: function() {
            Ext.Ajax.request({
            	url:'login/register',
            	params:Ext.getCmp('registerForm').getForm().getValues(),
            	success: function(response, opts){
            		var obj = Ext.decode(response.responseText);
            		if(obj.success == true){
            			Ext.Msg.alert('提示', '注册成功');
            			registerWindow.hide();
            		}else{
            			Ext.Msg.alert('提示', '注册失败');
            		}
            		Ext.getCmp('registerForm').getForm().reset();
            	},
                failure: function(response, opts) {
                    alert('failure');
                }
            })
        }
    },{
    	text: '取消',
        handler: function() {
        	registerWindow.hide();
        	Ext.getCmp('registerForm').getForm().reset();
        }
    }]
})