/**
 * Описывает общую схему главной страницы
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
Ext.define('DiaryDir.view.DiaryDirectory', {
    extend: 'Ext.panel.Panel',
    width: 500,
    height: 360,
    padding: 10,
    alias: 'widget.diaryDirectory',
    layout: 'border',
    items: [
        {
            xtype: 'diaryGrid',
            region: 'center'
        },
        {
            xtype: 'panel',
            html: '<div style="font: normal 18px Arial"><center><font size = "10">Ежедневник</font></center></div>',
            region: 'north',
            height: 80
        }


    ],
    renderTo: Ext.getBody()
});