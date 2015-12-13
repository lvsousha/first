var addStaffForm = new Ext.form.Panel(
		{
			border : false,
			bodyPadding : 5,
			id : 'addStaffForm',
			height : 560,
			fieldDefaults : {
				labelWidth : 80,
				labelSeparator : ": ",
				anchor : '0',
				margin : '2 2 2 2'
			},
			items : [
					{
						xtype : 'fieldset',
						defaultType : 'textfield',
						items : [
								{
									xtype : 'container',
									anchor : '-5',
									layout : 'column',
									items : [{
												xtype : 'textfield',
												width : 180,
												fieldLabel : "姓名",
												id : 'staffname',
												name : 'staffname',
												allowBlank : true,
												blankText : '姓名必须输入',
												emptyText : '请输入真实姓名'
											}]
								},
								{
									xtype : 'container',
									anchor : '-5',
									layout : 'column',
									items : [ {
										xtype : 'textfield',
										fieldLabel : "职责",
										width : 180,
										id : 'staffprofession',
										name : 'staffprofession',
										emptyText : '请输入职责名称',
										allowBlank : true,
										blankText : '职责名称必须输入'
									}, {
										xtype : 'textfield',
										fieldLabel : "部门",
										width : 180,
										id : 'staffdepartment',
										name : 'staffdepartment',
										allowBlank : true,
										labelAlign : 'right'
									} ]
								},
								{
									xtype : 'numberfield',
									fieldLabel : "月薪",
									id : 'staffsalary',
									name : 'staffsalary',
									anchor : '95%',
									step : 50,
									maxValue : 999999,
									allowBlank : true,
									minValue : 0,
									negativeText : '月薪不能为负数',
									emptyText : '请填入月薪'

								},
								{
									xtype : 'fieldcontainer',
									fieldLabel : '电话',
									defaultType : 'textfield',
									layout : 'hbox',
									anchor : '95%',
									combineErrors : true,
									items : [ {
										name : 'staffzone',
										id : 'staffzone',
										width : 50,
										emptyText : '区号',
										maskRe : /[\d]/,
										reegx : /[\d{3,4}]/,
										minLength : 3,
										maxLength : 4,
										regexText : '请输入正确的区号'
									}, {
										xtype : 'label',
										text : '--'
									}, {
										name : 'stafftelnumber',
										id : 'stafftelnumber',
										flex : 1,
										emptyText : '请输入电话号码',
										maskRe : /[\d]/,
										reegx : /[\d{7,8}]/,
										minLength : 7,
										maxLength : 8,
										regexText : '请输入正确的电话号码'
									} ]
								},
								{
									fieldLabel : '手机号码',
									name : 'staffmobile',
									id : 'staffmobile',
									allowBlank : true,
									anchor : '95%',
									maskRe : /[\d]/,
									reegx : /[\d{11}]/,
									minLength : 11,
									maxLength : 11,
									emptyText : '请输入手机号码',
									regexText : '请输入正确的手机号码'
								},
								{
									fieldLabel : '电子邮箱',
									name : 'staffemail',
									id : 'staffemail',
									allowBlank : true,
									anchor : '95%',
									emptyText : '请输入电子邮箱地址',
									vtype : 'email'
								},
								{
									fieldLabel : '地址',
									name : 'staffpartaddress',
									id : 'staffpartaddress',
									allowBlank : true,
									emptyText : '请输入详细地址',
									anchor : '95%'
								},
								{
									xtype : 'filefield',
									fieldLabel : '上传头像',
									name : 'avatar',
									id : 'avatar',
									anchor : '95%',
									buttonText : '',
									buttonConfig : {
										iconCls : 'upload'
									},
									listeners : {
										change : function(btn, value) {
											// 是否是规定的图片类型
											var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
											if (img_reg.test(value)) {
												var img = Ext
														.getCmp('staffavatar');
												var file = btn.fileInputEl.dom.files[0];
												var url = URL
														.createObjectURL(file);
												img.setSrc(url);
											} else {
												Ext.Msg.alert('提示',
														'请选择图片类型的文件！');
												return;
											}
										}
									}
								} ]
					}, {
						xtype : 'fieldset',
						title : '图片预览',
						defaults : {
							margin : '1 1 1 100',
							width : 200,
							height : 260
						},
						items : [ {
							xtype : 'image',
							id : 'staffavatar',
							border : 2,
							style : {
								borderColor : 'blue',
								borderStyle : 'solid'
							}
						} ]
					} ],
			dockedItems : [ {
				xtype : 'toolbar',
				dock : 'bottom',
				ui : 'footer',
				layout : {
					pack : 'center'
				},
				items : [ {
					text : '添加',
					disabled : true,
					formBind : true,
					handler : function() {
						var form = this.up('form').getForm();
						if (form.isValid()) {
							form.submit({
								url : 'staff/add',
								method : 'post',
								submitEmptyText : false,
								waitMsg : '请稍等，系统正在帮您添加',
								success : function(form, action) {
									Ext.Msg.alert('成功', action.result.msg);
									// 清除填过的内容
									// form.findField('guestname').setValue();
								},
								failure : function(form, action) {
									Ext.Msg.alert('失败', action.result.msg);
								}
							})
						}
					}
				}, {
					text : '重置',
					handler : function() {
						this.up('form').getForm().reset();
					}
				}

				]
			} ]
		});