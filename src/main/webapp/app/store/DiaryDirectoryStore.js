/**
 * Хранилище
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
Ext.define('DiaryDir.store.DiaryDirectoryStore', {
    extend: 'Ext.data.Store',
    model: 'DiaryDir.model.DiaryDirectoryModel',
    autoLoad: true,
    autoSync: true,
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