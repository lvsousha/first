var uploadForm = Ext.create('Ext.form.Panel',{
	border:false,
	id:'upload',
	layout:'column',
//	height:'30%',
	items:[{
		xtype: 'filefield',
		fieldLabel: '上传头像',
		name: 'avatar',
		id: 'avatar',
		anchor: '95%',
		buttonText: '',
		buttonConfig: {
			iconCls: 'upload',
			text:'选择文件'
		},
		listeners:{
			change: function(btn, value){
				var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
				if (img_reg.test(value)) {
					var img = Ext.getCmp('staffavatar');
					var file = btn.fileInputEl.dom.files[0];
					var url = URL.createObjectURL(file);
					img.setSrc(url);
				} else {
//					Ext.Msg.alert('提示','请选择图片类型的文件！');
//					return;
				}
			}
		}
	},{
		xtype:'fieldset',
		title:'图片预览',
		defaults: {
			margin: '1 1 1 100',
			width: 200,
			height: 260
		},
		items:[{
			xtype: 'image',
			id: 'staffavatar',
			border: 2,
			style:{
				borderColor : 'blue',
				borderStyle : 'solid'
			}
		}]
	}],
	dockedItems:[{
		xtype: 'toolbar',
		dock: 'bottom',
		ui: 'footer',
		layout:{
			pack: 'center'
		},
		items:[{
			text : '添加',
			disabled: true,
			formBind: true,
			handler: function(){
				var form = this.up('form').getForm();
				if(form.isValid()){
					form.submit({
						url: 'file/upload',
						method: 'post',
						submitEmptyText: false,
						waitMsg: '请稍等，系统正在帮您添加',
						success: function(form, action){
							Ext.Msg.alert('成功', action.result.msg);
							// 清除填过的内容
							// form.findField('guestname').setValue();
						},
						failure: function(form, action){
							Ext.Msg.alert('失败', action.result.msg);
						}
					})
				}
			}
		},{
			text: '重置',
			handler: function(){
				this.up('form').getForm().reset();
			}
		}]
	}]
});