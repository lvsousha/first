Ext.define('Eap.form.Field.ObjectField',{
    extend:'Ext.form.field.Trigger',
    alias: 'widget.objectfield',

    editable: false,
    trigger1Cls: 'x-form-clear-trigger',
    trigger2Cls: 'x-form-search-trigger',

    onTrigger1Click: function() {
        this.setValue(null);
    },

    onTrigger2Click: function() {
        var me = this;
        me.createSelector();
        if(!me.selectorWindow)
            return;
        me.selectorWindow.show();
    },

    //override
    setReadOnly: function(readOnly) {
        var me = this,
            old = me.readOnly;

        if (readOnly != old) {
            me.callParent(arguments);
            me.updateLayout();
        }
    },

    // override
    getSubmitValue: function() {
        if(this.idValue != null) return this.idValue;
        else return '';
    },

    // override
    getValue: function() {
        return this.value;
    },

    // override
    setValue: function(value) {
        var me = this;
        var v;
        if(value){
            if(typeof value === 'object'){
                 if(me.idProperty)
                    me.idValue = Eap.getProperty(value, me.idProperty);
                 else
                    me.idValue = value;
                 if(me.displayExpress){
                    v = new Ext.XTemplate(me.displayExpress).apply(value);
                 }else if(me.displayProperty)
                    v = Eap.getProperty(value, me.displayProperty);
                 else
                    v = value;
                 me.value = value;
                 me.setRawValue(v);
                 me.checkChange();
            }else{
                if(me.url){
                    var url = Ext.String.format(Eap.contextPath + '/' + me.url,value);
                    Ext.Ajax.request({
                        url: url,
                        callback: function(options, success, response) {
                            var data;
                            if(me.isIdValue === false){
                                me.idValue = null;
                            }else{
                                me.idValue = value;
                            }
                            if(success)
                                values = Ext.decode(response.responseText, true);
                            if(values == null)
                                values = {};
                            if(values.success) {
                                if(!Ext.isEmpty(me.root)){
                                    data = Eap.getProperty(values, me.root);
                                }else{
                                    data = values;
                                }
                                if(me.idProperty)
                                    me.idValue = Eap.getProperty(data, me.idProperty);
                                if(me.displayExpress){
                                    v = new Ext.XTemplate(me.displayExpress).apply(data);
                                }else if(me.displayProperty){
                                    v = Eap.getProperty(data, me.displayProperty);
                                }else{
                                    v = data;
                                }
                                if(me.loadcallback && Ext.isFunction(me.loadcallback)){
                            		me.loadcallback(me,data);
                            	}
                            }else{
                                v = null;
                            }
                            me.value = value;
                            me.setRawValue(v);
                            me.resetOriginalValue();
                            me.checkChange();
                        }
                   });
                }
            }
        }else{
            me.idValue = null;
            v = null;
            me.value = value;
			me.setRawValue(v);
			me.checkChange();
        }
        return me;
    },

    // override
    reset: function() {
        var me = this;
        me.beforeReset();
        me.setValue(me.originalValue);
        me.clearInvalid();
        delete me.wasValid;
    },

    // override
    resetOriginalValue: function() {
        var me = this;
        me.originalValue = me.getValue();
        me.checkDirty();
    },

    // override
    isEqual: function(value1, value2) {
        var me = this;
        if(Ext.isObject(value1) && me.idProperty)
            value1 = Eap.getProperty(value1, me.idProperty);
        if(Ext.isObject(value2) && me.idProperty)
            value2 = Eap.getProperty(value2, me.idProperty);
        return me.isEqualAsString(value1, value2);
    },

    // private
    createSelector: function() {
        var me = this;
        if(me.selectorWindow || !me.selector)
            return;
        me.selectorWindow = Eap.createSelector(me.selector);
        if (!me.selectorWindowOkHandler) {
            me.selectorWindowOkHandler = function(win, sel, checkbox) {
                var value;
            	if(!checkbox){
                     if(sel[0].get('model') === undefined){
                         value = sel[0];
                     }else{
                    	 value = sel[0].get('model');
                     }
                     me.setValue(value);
                }else{
                	 value = sel;
                     me.setValues(sel);
                }
            	if(me.callback && Ext.isFunction(me.callback)){
            		me.callback(me,value);
            	}
                win.hide();
            };
        }
        me.selectorWindow.onOkClick = me.selectorWindowOkHandler;
    },

    setValues:function (arr){
        var me = this;
        if(arr){
            var rawValue = '';
            var values = [];
            if(typeof arr === 'object'){
                Ext.Array.each(arr,function(item,index){
                    var value = item.get('model');
                    if(value === undefined)
                         value = item;
                    else
                         value = item.get('model');
                    if(value != null) {
                        if(me.idProperty){
                            me.idValue = Eap.getProperty(value, me.idProperty);
                            v = Eap.getProperty(value, me.idProperty);
                        }else{
                            v = value;
                        }
                    } else {
                        me.idValue = null;
                        v = null;
                    }

                    if(index == arr.length-1){
                        rawValue += v;
                    }else{
                        rawValue += v+',';
                    }
                    values.push(value);
                });
            }else if(typeof arr === 'string'){
                values = arr;
                rawValue = arr;
            }
            me.value = values;
            me.setRawValue(rawValue);
        }
    }

});