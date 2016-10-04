/**
 * Описывает таблицу (grid) главной страницы
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
Ext.define('DiaryDir.view.DiaryGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.diaryGrid',
    width: 400,
    height: 300,
    frame: true,
    store: 'DiaryDirectoryStore',
    iconCls: 'icon-user',
    viewConfig:{
        markDirty:false
    },

    columns: [
        {
            "text": 'Дата создания',
            "sortable": true,
            "dataIndex": 'created',
            "type": 'timestamp',
            "renderer": function timeConverter(created) {
                var date = new Date(created);
                return Ext.Date.format(date, "Y.m.d H:i");
            }

        },
        {
            "text": 'Дата изменения',
            "sortable": true,
            "dataIndex": 'updated',
            "type": 'timestamp',
            "renderer": function timeConverter(updated) {
                var date = new Date(updated);
                return Ext.Date.format(date, "Y.m.d H:i");
            }
        },
        {
            "text": 'Наименование',
            "flex": 2,
            "sortable": true,
            "dataIndex": 'name'
        },
        {
            "flex": 4,
            "text": 'Текст',
            "sortable": true,
            "dataIndex": 'text',
            "renderer": function (value, meta, record) {
                var max = 83;
                meta.tdAttr = 'data-qtip="' + value + '"';
                return value.length < max ? value : value.substring(0, max - 3) + '...';
            }
        }
    ],




    dockedItems: [
        {
            xtype: 'toolbar',
            items: [
                {
                    text: 'Обновить',
                    action: 'refresh',
                    iconCls: 'icon-refresh'
                },
                '-',
                {
                    text: 'Добавить',
                    action: 'add',
                    iconCls: 'icon-add',
                    id: 'add'
                },
                '-',
                {
                    text: 'Удалить',
                    action: 'delete',
                    iconCls: 'icon-delete',
                    disabled: true
                },
                '-',
                {
                    text: 'Выход',
                    action: 'logout',
                    iconCls: 'icon-logout'
                }
            ]
        }
    ]
});