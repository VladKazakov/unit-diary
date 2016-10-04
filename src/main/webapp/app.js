/**
 * Created by Vlad Kazakov on 24.08.2016.
 */
Ext.application({
    name: 'DiaryDir',

    appFolder: 'app',

    controllers: [
        'DiaryDirectoryController'
    ],

    launch: function () {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: {
                xtype: 'diaryDirectory'
            }
        });
    }
});