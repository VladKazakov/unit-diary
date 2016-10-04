/**
 * Модель объекта - записи
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
Ext.define('DiaryDir.model.DiaryDirectoryModel', {
    extend: 'Ext.data.Model',
    fields: ['name', 'text', 'created', 'updated'],


    proxy: {
        type: 'rest',

        api: {
            create: 'diary',
            read: 'diary',
            update: 'diary',
            destroy: 'diary'
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        writer: {
            type: 'json',
            writeAllFields: true
        }

    }
});