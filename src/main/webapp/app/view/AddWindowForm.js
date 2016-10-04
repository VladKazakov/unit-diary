/**
 * Форма добавления записи
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
Ext.define('DiaryDir.view.AddWindowForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.addWindowForm',
    store: 'DiaryDirectoryStore',
    autoShow: true,
    layout: 'fit',
    modal: true,


    items: [
        {
            bodyPadding: 10,
            xtype: 'form',
            items: [
                {
                    xtype: 'textfield',
                    name: 'name',
                    fieldLabel: 'Наименование',
                    allowBlank: false,
                    blankText: 'Это поле должно быть заполнено'
                },
                {
                    xtype: 'textareafield',
                    name: 'text',
                    fieldLabel: 'Текст'
                }
            ]
        }
    ],

    buttons: [
        {
            text: 'Сохранить',
            action: 'save',
            disabled: true
        },
        {
            text: 'Отменить',
            handler: function () {
                this.up('window').close();
            }

        }
    ]
});