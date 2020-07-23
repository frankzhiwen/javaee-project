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
 * 课程管理
 */
let courseTabOptions = {
    id: "course_table",//表格id
    url:'course/query',//表格查询url
    toolbar: [{
        type: "add",//新增按钮
        title: "新增课程",//弹出框标题
        url: "course/add",//弹出框提交url
        invisible:["createTime"],//弹出框不展示的字段
    },{
        type: "update",//修改按钮
        title: "修改课程",
        queryUrl: "course/queryById",//弹出框初始化数据url
        url: "course/update",
        // invisible:["createTime"],
    },{
        type: "delete",//删除按钮
        url: "course/delete",
    }],
    columns: [{
        checkbox: true
    },{
        title: '课程名称',
        field: 'courseName',
        switchable: false,//表格是否允许不展示列
        sortable: true,//表格字段是否可排序
        required: true,//新增/修改时，字段是否必填
        tooltip: "请输入课程名称",//提交时字段验证提示内容
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
 * 考试管理
 */
let examTabOptions = {
    id: "exam_table",//表格id
    url:'exam/query',//表格查询url
    toolbar: [{
        type: "add",//新增按钮
        title: "新增考试",//弹出框标题
        url: "exam/add",//弹出框提交url
        invisible:["course.courseName", "classes.classesName", "createTime"],//弹出框不展示的字段
    },{
        type: "update",//修改按钮
        title: "修改考试",
        queryUrl: "exam/queryById",//弹出框初始化数据url
        url: "exam/update",
        invisible:["course.courseName", "classes.classesName"],
    },{
        type: "delete",//删除按钮
        url: "exam/delete",
    }],
    columns: [{
        checkbox: true
    },{
        title: '考试名称',
        field: "examName",
        switchable: false,//表格是否允许不展示列
        sortable: true,//表格字段是否可排序
        required: true,//新增/修改时，字段是否必填
        tooltip: "请输入学生姓名",//提交时字段验证提示内容
    },{
        title: '考试说明',
        field: 'examDesc',
    },{
        title: '课程',
        field: 'course.courseName',
    },{
        title: '课程',
        field: 'course.id',
        submitName: 'courseId',//新增/修改提交的字段名
        type: "select",//新增/修改时，字段为下拉菜单
        url: "course/queryAsDict",//新增/修改时，下拉菜单初始化数据url
        visible: false,//表格不显示，新增/修改弹出框使用
        switchable: false,//表格是否允许不展示列
        search: true,//下拉菜单是否可搜索
        // required: true,//新增/修改时，字段是否必填
        // tooltip: "请选择班级",//提交时字段验证提示内容
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
 * 考试成绩管理
 */
let examRecordTabOptions = {
    id: "exam_record_table",//表格id
    url:'examRecord/query',//表格查询url
    toolbar: [{
        type: "add",//新增按钮
        title: "新增考试成绩",//弹出框标题
        url: "examRecord/add",//弹出框提交url
        invisible:["exam.examName", "student.studentName", "createTime"],//弹出框不展示的字段
    },{
        type: "update",//修改按钮
        title: "修改考试成绩",
        queryUrl: "examRecord/queryById",//弹出框初始化数据url
        url: "examRecord/update",
        invisible:["exam.examName", "student.studentName"],
    },{
        type: "delete",//删除按钮
        url: "examRecord/delete",
    }],
    columns: [{
        checkbox: true
    },{
        title: '考试名称',
        field: 'exam.examName',
    },{
        title: '考试名称',
        field: 'exam.id',
        submitName: 'examId',//新增/修改提交的字段名
        type: "select",//新增/修改时，字段为下拉菜单
        url: "exam/queryAsDict",//新增/修改时，下拉菜单初始化数据url
        visible: false,//表格不显示，新增/修改弹出框使用
        switchable: false,//表格是否允许不展示列
        search: true,//下拉菜单是否可搜索
        // required: true,//新增/修改时，字段是否必填
        // tooltip: "请选择班级",//提交时字段验证提示内容
    },{
        title: '学生姓名',
        field: 'student.studentName',
    },{
        title: '学生姓名',
        field: 'student.id',
        submitName: 'studentId',//新增/修改提交的字段名
        type: "select",//新增/修改时，字段为下拉菜单
        url: "student/queryAsDict",//新增/修改时，下拉菜单初始化数据url
        visible: false,//表格不显示，新增/修改弹出框使用
        switchable: false,//表格是否允许不展示列
        search: true,//下拉菜单是否可搜索
        // required: true,//新增/修改时，字段是否必填
        // tooltip: "请选择班级",//提交时字段验证提示内容
        relatedSelect: "exam.id",
        // relatedField: "studentName",//级联菜单url查询出的数据，关联字段
    },{
        title: '班级',
        field: 'classes.classesName',
        disabled: true,
        relatedSelect: "exam.id",
        relatedField: "classes.classesName",//级联菜单url查询出的数据，关联字段
    },{
        title: '课程名称',
        field: 'course.courseName',
        disabled: true,
        relatedSelect: "exam.id",
        relatedField: "course.courseName",//级联菜单url查询出的数据，关联字段
    },{
        title: '分数',
        field: 'score',
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
        id: "course_tab",
        init: function () {
            initTable(courseTabOptions);
        },

    },{
        id: "stu_tab",
        init: function () {
            initTable(stuTabOptions);
        },
    },{
        id: "exam_tab",
        init: function () {
            initTable(examTabOptions);
        },
    },{
        id: "exam_record_tab",
        init: function () {
            initTable(examRecordTabOptions);
        },
        default: true
    },{
        id: "user_tab",
        init: function () {
            initTable(userTabOptions);
        },
    },{
        id: "settings_tab",
        init: function () {
        },
        active: false
    }]);
});