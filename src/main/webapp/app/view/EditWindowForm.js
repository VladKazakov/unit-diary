/**
 * Форма изменения записи
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
Ext.define('DiaryDir.view.EditWindowForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.editWindowForm',
    store: 'DiaryDirectoryStore',
    autoShow: true,
    layout: 'fit',
    modal: true,
    loadStore: function() {
        this.getDiaryDirectoryStoreStore.load();
    },

    items: [
        {

            bodyPadding: 10,
            xtype: 'form',
            items: [
                {
                    xtype: 'displayfield',
                    name: 'created',
                    fieldLabel: 'Дата создания',
                    renderer: function renderDate(created)
                    {
                        if (created == '' || created == undefined) {
                            return '';
                        } else {
                            getDate = new Date(parseInt(created));
                            return Ext.util.Format.date(getDate, 'Y.m.d H:i');
                        }
                    }
                },

                {
                    xtype: 'displayfield',
                    name: 'updated',
                    fieldLabel: 'Дата изменения',

                    renderer: function renderDate(updated)
                    {
                        if (updated == '' || updated == undefined) {
                            return '';
                        } else {
                            getDate = new Date(parseInt(updated));
                            return Ext.util.Format.date(getDate, 'Y.m.d H:i');
                        }
                    }


                },
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
            action: 'update',
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