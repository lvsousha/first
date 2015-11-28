Ext.define('Eap.form.Field.ColorField', {
    extend: 'Ext.form.field.Picker',
    alias: 'widget.colorfield',
    triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
    matchFieldWidth: true,
    initComponent: function () {
        var me = this;
        me.disabledDatesRE = null;
        me.callParent();
    },
    createPicker: function () {
        var me = this;
        return Ext.create('Ext.picker.Color', {
            pickerField: me,
            ownerCt: me.ownerCt,
            renderTo: document.body,
            floating: true,
            shadow: false,
            height:200,
            focusOnShow: true,
            listeners: {
                select:function(e, color){
                	me.setValue(color);
                	e.hide();
                }
            }
        });
    }
});