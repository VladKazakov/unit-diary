/**
 * Контроллер для управления видами и реализации скриптов кнопок
 *
 * @author Vlad Kazakov
 * @version 1.0
 */
Ext.define('DiaryDir.controller.DiaryDirectoryController', {
    extend: 'Ext.app.Controller',

    views: [
        'AddWindowForm',
        'DiaryDirectory',
        'DiaryGrid',
        'EditWindowForm'
    ],

    stores: ['DiaryDirectoryStore'],
    models: ['DiaryDirectoryModel'],

    refs: [
        {
            selector: 'diaryGrid',
            ref: 'diaryGrid'
        },
        {
            selector: 'diaryGrid button[action="refresh"]',
            ref: 'diaryGridRefresh'
        },
        {
            selector: 'diaryGrid button[action="add"]',
            ref: 'diaryGridAdd'
        },
        {
            selector: 'diaryGrid button[action="update"]',
            ref: 'diaryGridUpdate'
        },
        {
            selector: 'diaryGrid button[action="delete"]',
            ref: 'diaryGridDelete'
        },
        {
            selector: 'diaryGrid button[action="logout"]',
            ref: 'diaryGridLogout'
        },
        {
            selector: 'addWindowForm',
            ref: 'addWindowForm'
        },
        {
            selector: 'editWindowForm',
            ref: 'editWindowForm'
        },
        {
            selector: 'diaryDirectory',
            ref: 'diaryDirectory'
        },
        {
            selector: 'addWindowForm textfield[name=name] ',
            ref: 'addWindowFormName'
        },
        {
            selector: 'addWindowForm textfield[name=text]',
            ref: 'addWindowFormDiary'
        },
        {
            selector: 'addWindowForm textfield[name=created]',
            ref: 'addWindowFormCreated'
        },
        {
            selector: 'addWindowForm textfield[name=updated]',
            ref: 'addWindowFormUpdated'
        },
        {
            selector: 'addWindowForm textfield[name=idUserName]',
            ref: 'addWindowFormUpdated'
        },
        {
            selector: 'addWindowForm button[action=save]',
            ref: 'addWindowFormDiarySave'
        },
        {
            selector: 'editWindowForm textfield[name=name] ',
            ref: 'editWindowFormName'
        },
        {
            selector: 'editWindowForm textfield[name=text]',
            ref: 'editWindowFormDiary'
        },
        {
            selector: 'editWindowForm textfield[name=created]',
            ref: 'editWindowFormCreated'
        },
        {
            selector: 'editWindowForm textfield[name=updated]',
            ref: 'editWindowFormUpdated'
        },
        {
            selector: 'editWindowForm button[action=update]',
            ref: 'editWindowFormDiaryUpdate'
        }
    ],

    init: function () {
        this.control({
            'diaryGrid button[action=refresh]': {
                click: this.onRefresh
            },

            'diaryGrid button[action=add]': {
                click: this.onAddDiary
            },
            'diaryGrid button[action=delete]': {
                click: this.onDelDiary
            },
            'diaryGrid': {
                cellclick: this.onLineGrid,
                itemdblclick: this.onUpdDiary
            },
            'addWindowForm button[action=save]': {
                click: this.onSaveDiary
            },
            'editWindowForm button[action=update]': {
                click: this.onUpdateDiary
            },
            'diaryGrid button[action=logout]': {
                click: this.onLogoutDiary
            },
            'addWindowForm textfield[name=name]': {
                change: this.onValidation
            },
            'addWindowForm textfield[name=text]': {
                change: this.onValidation
            },
            'addWindowForm textfield[name=created]': {
                change: this.onValidation
            },
            'addWindowForm textfield[name=updated]': {
                change: this.onValidation
            },
            'addWindowForm textfield[name=idUserName]': {
                change: this.onValidation
            },
            'editWindowForm textfield[name=name]': {
                change: this.onValidationEdit
            },
            'editWindowForm textfield[name=text]': {
                change: this.onValidationEdit
            },
            'editWindowForm textfield[name=created]': {
                change: this.onValidationEdit
            },
            'editWindowForm textfield[name=updated]': {
                change: this.onValidationEdit
            }
        });
    },

    onRefresh: function () {
        this.getDiaryDirectoryStoreStore().reload()
    },

    onAddDiary: function () {
        Ext.widget('addWindowForm');
    },


    onUpdDiary: function() {
        var win = new Ext.widget('editWindowForm');
        var sm = this.getDiaryGrid().getSelectionModel();
        var rs = sm.getSelection();
        win.down('form').loadRecord(rs[0]);
    },



    onSaveDiary: function () {
        var me = this;
        var diaryModel = Ext.create('DiaryDir.model.DiaryDirectoryModel');
        diaryModel.set(this.getAddWindowForm().down('form').getValues());
        diaryModel.save({
            success: function (operation, response) {
                var objAjax = operation.data;
                me.getDiaryDirectoryStoreStore().add(objAjax);
                me.getAddWindowForm().close();
            },
            failure: function (dummy, result) {
                Ext.MessageBox.show({
                    title: 'Ошибка!',
                    msg: 'Произошла ошибка. Повторите попытку или обратитесь к администратору',
                    buttons: Ext.Msg.OK,
                    icon: Ext.Msg.ERROR
                });
            }
        });
    },

    onUpdateDiary: function() {
            var win = this.getEditWindowForm();
            form = win.down('form');
            record = form.getRecord();
            values = form.getValues();
            record.set(values);
            win.close();

    },

    onDelDiary:
            function() {
                var sm = this.getDiaryGrid().getSelectionModel();
                var rs = sm.getSelection()[0];
                this.getDiaryDirectoryStoreStore().remove(rs);
                sm.select(0);
    },

    onLogoutDiary: function () {
            window.location.href = 'logout';
    },

    onLineGrid: function () {
        this.getDiaryGridDelete().enable();
    },

    onValidation: function () {
        if (this.getAddWindowFormName().validate() && this.getAddWindowFormDiary().validate()) {
            this.getAddWindowFormDiarySave().enable();
        } else {
            this.getAddWindowFormDiarySave().disable();
        }
    },

    onValidationEdit: function () {
        if (this.getEditWindowFormName().validate() && this.getEditWindowFormDiary().validate()) {
            this.getEditWindowFormDiaryUpdate().enable();
        } else {
            this.getEditWindowFormDiaryUpdate().disable();
        }
    }

});