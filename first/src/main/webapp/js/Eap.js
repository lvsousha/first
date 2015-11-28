Ext.define('Eap', {
	singleton: true,

	contextPath: '/jiudao',
	theme: 'gray',
	timezone: '',	// TODO user value
	lang: 'zh_CN',
	dateFormat: 'Y-m-d',
	datetimeFormat: 'Y-m-d H:i:s',
	timeFormat: 'H:i:s',
	messagecheckinterval: 60000,

	types: [
		'string',
		'boolean',
		'byte',
		'short',
		'integer',
		'long',
		'double',
		'float',
		'date',
		'datetime',
		'time',
		'text'
	],

	errors: {
		inputError: 'Input error'
	},

	init: function(config) {
		config = config || {};

		Ext.apply(this, config);

		Ext.BLANK_IMAGE_URL = this.contextPath + '/static/images/s.gif';

		Ext.tip.QuickTipManager.init();

		Ext.state.Manager.setProvider(
			new Ext.state.CookieProvider({
			})
		);
	},

	getType: function(type) {
		return this.types[type];
	},

	createSelector: function(config) {
		var type, cfg;
		if(Ext.isString(config)) {
			var o = Ext.create(config);
			type = o.type;
			cfg = o.cfg;
		} else {
			if(config.cfg){
				var o = Ext.create(config.type,config.cfg);
				type = o.type;
				cfg = o.cfg;
			} else {
				type = config.type || 'Eap.view.GridSelector';
				cfg = config;
			}
		}
		return Ext.create(type, cfg);
	},

	rendererUnread: function(value, data, record, rowIndex, colIndex, store) {
		if(record.get('um_unread')) {
			return '<b>' + value + '</b>';
		} else {
			return value;
		}
	},

	checkUnread: function(value, data, record, rowIndex, colIndex, store) {
		if(record.get('unread')) {
			return '<b>' + value + '</b>';
		} else {
			return value;
		}
	},

	markFieldErrors: function(panel, errors) {
		var form = Ext.getCmp(panel).getForm();
		Ext.each(errors, function(error, index, errors) {
			var field = form.findField(error[0]);
			if(field != null)
				field.markInvalid(error[1].join('<br>'));
		});
	},

	MSG: function(){
		var msgCt = null;

		function createBox(t, s){
			return '<div class="msg"><h3>' + t + '</h3><p>' + s + '</p></div>';
		}
		var obj = {};
		obj.msg = function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            var s = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, createBox(title, s), true);
            m.hide();
            m.slideIn('t').ghost("t", { delay: 3000, remove: true});
        };
        return obj;
	}(),

	getProperty: function(o, s){
		// http://stackoverflow.com/questions/6491463/accessing-nested-javascript-objects-with-string-key
		if(!Ext.isObject(o)) return null;
		s = s.replace(/\[(\w+)\]/g, '.$1');
		s = s.replace(/^\./, '');
		var a = s.split('.');
		for (var i = 0, n = a.length; i < n; ++i) {
			var k = a[i];
			if(o instanceof Ext.data.Model || o instanceof Ext.util.HashMap) {
				o = o.get(k);
			}else if (k in o) {
				o = o[k];
			} else {
				return ;
			}
		}
		return o;
	}
});
/* vim:ts=4:sw=4:ai:si
 */
