function logout(){
    $.ajax({
        url: "user/logout",
        contentType: "application/json",
        success: function (r) {
            showSuccessModal("注销成功", function () {
                window.location.href = "public/index.html";
            });
        },
        error: function (error) {
            showError(error);
        }
    });
    return false;
}

/**
 * 班级管理
 */
let classesTabOptions = {
    id: "classes_table",//表格id
    url:'classes/query',//表格查询url
    toolbar: [{
        type: "add",//新增按钮
        title: "新增班级",//弹出框标题
        url: "classes/add",//弹出框提交url
        invisible:["createTime"],//弹出框不展示的字段
    },{
        type: "update",//修改按钮
        title: "修改班级",
        queryUrl: "classes/queryById",//弹出框初始化数据url
        url: "classes/update",
        // invisible:["createTime"],
    },{
        type: "delete",//删除按钮
        url: "classes/delete",
    }],
    columns: [{
        checkbox: true
    },{
        title: '班级名称',
        field: "classesName",
        switchable: false,//表格是否允许不展示列
        sortable: true,//表格字段是否可排序
        required: true,//新增/修改时，字段是否必填
        tooltip: "请输入班级名称",//提交时字段验证提示内容
    },{
        title: '毕业年份',
        field: 'classesGraduateYear',
        type: "select",//新增/修改时，字段为下拉菜单
        dictionaryKey: "000001",//数据字典键
    },{
        title: '专业',
        field: 'classesMajor',
        // submitName: 'classesMajor',//新增/修改提交的字段名
        type: "select",//新增/修改时，字段为下拉菜单
        dictionaryKey: "000002",//数据字典键
    },{
        title: '描述',
        field: 'classesDesc',
    },{
        title: '创建时间',
        field: 'createTime',
        type: "datetime",
        disabled: true,
    }],
};

/**
 * 学生管理
 */
let stuTabOptions = {
    id: "stu_table",//表格id
    url:'student/query',//表格查询url
    toolbar: [{
        type: "add",//新增按钮
        title: "新增学生",//弹出框标题
        url: "student/add",//弹出框提交url
        invisible:["classes.classesName", "createTime"],//弹出框不展示的字段
    },{
        type: "update",//修改按钮
        title: "修改学生",
        queryUrl: "student/queryById",//弹出框初始化数据url
        url: "student/update",
        invisible:["classes.classesName"],
    },{
        type: "delete",//删除按钮
        url: "student/delete",
    }],
    columns: [{
        checkbox: true
    },{
        title: '姓名',
        field: "studentName",
        switchable: false,//表格是否允许不展示列
        sortable: true,//表格字段是否可排序
        required: true,//新增/修改时，字段是否必填
        tooltip: "请输入学生姓名",//提交时字段验证提示内容
    },{
        title: '学号',
        field: 'studentNo',
    },{
        title: '身份证号',
        field: 'idCard',
    },{
        title: '班级',
        field: 'classes.classesName',
    },{
        title: '班级',
        field: 'classes.id',
        submitName: 'classesId',//新增/修改提交的字段名
        type: "select",//新增/修改时，字段为下拉菜单
        url: "classes/queryAsDict",//新增/修改时，下拉菜单初始化数据url
        visible: false,//表格不显示，新增/修改弹出框使用
        switchable: false,//表格是否允许不展示列
        search: true,//下拉菜单是否可搜索
        // required: true,//新增/修改时，字段是否必填
        // tooltip: "请选择班级",//提交时字段验证提示内容
    },{
        title: '毕业年份',
        field: 'classes.classesGraduateYear',
        // submitName: 'classesGraduateYear',//新增/修改提交的字段名
        type: "select",//新增/修改时，字段为下拉菜单
        dictionaryKey: "000001",//数据字典键
        disabled: true,//表单元素为禁用时，提交表单时不会包含禁用元素数据
        // readonly: true,//表单元素为只读时，提交表单时会包含只读元素数据
        relatedSelect: "classes.id",
        relatedField: "classesGraduateYear",//级联菜单url查询出的数据，关联字段
    },{
        title: '专业',
        field: 'classes.classesMajor',
        // submitName: 'classesMajor',//新增/修改提交的字段名
        type: "select",//新增/修改时，字段为下拉菜单
        dictionaryKey: "000002",//数据字典键
        disabled: true,//表单元素为禁用时，提交表单时不会包含禁用元素数据
        // readonly: true,//表单元素为只读时，提交表单时会包含只读元素数据
        relatedSelect: "classes.id",
        relatedField: "classesMajor",//级联菜单url查询出的数据，关联字段
    },{
        title: '邮箱',
        field: 'studentEmail',
    },{
        title: '创建时间',
        field: 'createTime',
        type: "datetime",
        disabled: true,
        // formatter: function(value, item, index) {
        //     return value;
        // },
    }],
};

/**
 * 用户管理
 */
let userTabOptions = {
    id: "user_table",//表格id
    url:'user/query',//表格查询url
    toolbar: [{
        type: "add",//新增按钮
        title: "新增用户",//弹出框标题
        url: "user/register",//弹出框提交url
        invisible:[],//弹出框不展示的字段
    },{
        type: "update",//修改按钮
        title: "修改用户",
        queryUrl: "user/queryById",//弹出框初始化数据url
        url: "user/update",
        invisible:[],
    },{
        type: "delete",//删除按钮
        url: "user/delete",
    }],
    columns: [{
        checkbox: true
    },{
        title: '用户账号',
        field: "username",
        switchable: false,//表格是否允许不展示列
        sortable: true,//表格字段是否可排序
        required: true,//新增/修改时，字段是否必填
        tooltip: "请输入用户账号",//提交时字段验证提示内容
    },{
        title: '密码',
        field: 'password',
    },{
        title: '用户昵称',
        field: 'nickname',
    },{
        title: '邮箱',
        field: 'email',
    },{
        title: '创建时间',
        field: 'createTime',
        type: "datetime",
        // disabled: true,
        // formatter: function(value, item, index) {
        //     return value;
        // },
    }],
};

$(function () {
    setTableDefaultSettings();
    initNav("main_nav", [{
        id: "classes_tab",
        init: function () {
            initTable(classesTabOptions);
        }
    },{
        id: "stu_tab",
        init: function () {
            initTable(stuTabOptions);
        },
    },{
        id: "user_tab",
        init: function () {
            initTable(userTabOptions);
        },
        default: true
    },{
        id: "settings_tab",
        init: function () {
        },
        active: false
    }]);
});