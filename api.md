# API.md

## Reviews

### /reviews/id/{id}

请求对应id的书评。

- GET
- RequiresAuthentication

Answer:

    {
        "rid": 1,
        "createTime": "2020-07-01T13:43:24.224+00:00",
        "content": "分死殴打杰佛逊 。 搜房isdfojs分isdosj分 ， 死。 近似等级封禁，封禁递送。四季豆腐i，。Sar Por Una Para mi sado.",
        "title": "莫泊桑小说",
        "userId": 2,
        "username": "user0",
        "userNick": "superman",
        "userResume": null,
        "bookId": 1,
        "bookAuthor": "黑柳",
        "bookName": "黑柳"
    }

### /reviews/new

- POST
- RequiresRoles: reader
- RequiresPermissions: new:review

Request:

    {
        "title": "莫泊桑小说",
        "bookName": "我是猫", 
        "bookAuthor": "黑柳",
        "content": "分死殴打杰佛逊 。 搜房isdfojs分isdosj分 ， 死。 近似等级封禁，封禁递送。四季豆腐i，。Sar Por Una Para mi sado."
    }

Answer:

    {
        "msg": "发布成功",
        "code": 1,
        "succ": true,
        "oper": "default"
    }


### /reviews/all

- GET
- RequiresAuthentication

Answer:

    [
        {
            "rid": 1,
            "createTime": "2020-07-01T13:43:24.224+00:00",
            "content": "分死殴打杰佛逊 。 搜房isdfojs分isdosj分 ， 死。 近似等级封禁，封禁递送。四季豆腐i，。Sar Por Una Para mi sado.",
            "title": "莫泊桑小说",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 1,
            "bookAuthor": "黑柳",
            "bookName": "黑柳"
        },
        {
            "rid": 2,
            "createTime": "2020-07-01T14:13:28.923+00:00",
            "content": "<a> Go! </a>Sar Por Una Para mi sado.",
            "title": "莫泊桑小说",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 1,
            "bookAuthor": "黑柳",
            "bookName": "黑柳"
        }
    ]


### /reviews/allu

获得某用户的所有书评。

- GET
- RequiresAuthentication


    uid int
    
Request:

    /reviews/allu?uid=2

Answer:

    [
        {
            "rid": 1,
            "createTime": "2020-07-01T13:43:24.224+00:00",
            "content": "<h1>富强、民主、文明、和谐</h1><br/>",
            "title": "莫泊桑小说",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 1,
            "bookAuthor": "黑柳",
            "bookName": "黑柳"
        },
        {
            "rid": 2,
            "createTime": "2020-07-01T14:13:28.923+00:00",
            "content": "<h3><font color=\"blue\">爱国、敬业、诚信、友善</font></h3>",
            "title": "莫泊桑小说",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 1,
            "bookAuthor": "黑柳",
            "bookName": "黑柳"
        }
    ]

### /reviews/bylimit

流式分页加载书评。

- GET
- RequiresAuthentication


    offset int
    limit int
    desc bool

Request
    
    /square/reviewsbylimits?offset=3&limit=3&desc=true
    
Post

    [
        {
            "rid": 4,
            "createTime": "2020-07-02T04:10:01.411+00:00",
            "content": "PHP is the <p> BEST PL in the world.",
            "title": "PHP is the BEST",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 3,
            "bookAuthor": "L.R",
            "bookName": "L.R"
        }
    ]
    
### /reviews/my

返回用户创建的书评。

- GET
- RequiresRoles: reader

Request:

    /reviews/my
    
Answer:
    
    [
        {
            "rid": 1,
            "createTime": "2020-07-01T13:43:24.224+00:00",
            "content": "分死殴打杰佛逊 。 搜房isdfojs分isdosj分 ， 死。 近似等级封禁，封禁递送。四季豆腐i，。Sar Por Una Para mi sado.",
            "title": "莫泊桑小说",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 1,
            "bookAuthor": "黑柳",
            "bookName": "黑柳"
        },
        {
            "rid": 4,
            "createTime": "2020-07-02T04:10:01.411+00:00",
            "content": "PHP is the <p> BEST PL in the world.",
            "title": "PHP is the BEST",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 3,
            "bookAuthor": "L.R",
            "bookName": "L.R"
        }
    ]


### /reviews/getlike

返回用户是否收藏过某书评。

- GET
- RequiresRoles: reader


    rid int

Request:
    
    /getlike?rid=1

Answer:

    bool
    
### /reviews/setlike

设置用户是否收藏书评。

- GET
- RequiresRoles: reader


    rid int
    like boolean
    
Request:

    /reviews/setlike?rid=1&like=true
    
Answer:

    {
        "msg": "Ok.",
        "code": 1,
        "succ": true,
        "oper": "default"
    }

### /reviews/countlike

查询用户收藏书评的总数。

- GET
- RequiresRoles: reader

Answer:

    int

### /reviews/like

返回用户收藏的所有书评。

- GET
- RequiresRoles: reader

Answer:

    [
        {
            "rid": 1,
            "createTime": "2020-07-01T13:43:24.224+00:00",
            "content": "分死殴打杰佛逊 。 搜房isdfojs分isdosj分 ， 死。 近似等级封禁，封禁递送。四季豆腐i，。Sar Por Una Para mi sado.",
            "title": "莫泊桑小说",
            "userId": 2,
            "username": "user0",
            "userNick": "superman",
            "userResume": null,
            "bookId": 1,
            "bookAuthor": "黑柳",
            "bookName": "黑柳"
        }
    ]
    
    
### /reviews/countekil

返回书评的总赞数。

- GET
- RequiresAuthentication


    rid int

Answer

    int
    
    
### /reviews/getekil

返回赞的状态。

- GET
- RequiredRoles: reader

Answer:

    boolean
    
## /reviews/setekil

设置赞的状态

- GET
- RequiredRoles: reader

    
    rid int
    ekil bool

Answer:

    {
        "msg": "msg",
        "code": 1,
        "succ": true,
        "oper": "default"
    }

Answer 2:

    {
        "msg": "msg",
        "code": 1,
        "succ": false,
        "oper": "default"
    }
        
    

## Comments

### /comments/all

- GET
- RequiresRoles: reader
- RequiresPermissions: new:comment


    rid int
       
Request:

    /comments/all?rid=1

Answer:

    [
        {
            "id": 1,
            "reviewId": null,
            "creatorId": 2,
            "creatorUserName": "user0",
            "creatorUserNick": "superman",
            "creatorUserResume": null,
            "createTime": "2020-07-02T06:29:27.021+00:00",
            "fatherCommentId": null,
            "content": "好"
        }
    ]

### /comments/new

- POST
- RequiresRoles: reader
- RequiresPermissions: new:comment

Request:

    {
        "reviewId": 1,
        "ftCmtId": null,
        "content": "好"
    }

Answer:

    {
        "msg": "评论成功",
        "code": 1,
        "succ": true,
        "oper": "default"
    }

## Users

### /login

- POST

Request:

    {
        "username": "user0",
        "password": "123456"
    }

Answer:
    
    {
        "msg": "ok",
        "nick": "abc",
        "uid": 8,
        "code": 1,
        "succ": true,
        "roles": [
            "reader"
        ],
        "oper": "default",
        "perms": [],
        "token": "b856a716-9e33-477b-8113-dffcef5c7a2f",
        "imgSrc": "/img/1.png"
    }

Answer 2:

    {
        "msg": "用户帐号或密码不正确",
        "code": -1,
        "succ": false,
        "oper": "default"
    }

### /signup

- POST

Request:

    {
        "username": "user0",
        "password": "123456",
        "nick": "superman",
        "email": "",
        "phone": "12355555"
    }

Answer:

    {
        "msg": "注册成功",
        "code": 1,
        "succ": true,
        "oper": "default"
    }

### /user/check

- GET POST

返回用户名是否可用于注册新用户。

Request:

    {
        "username": "user0"
    }

Answer:

    {
        "msg": "false",
        "code": -1,
        "succ": false,
        "oper": "default"
    }

### /user/changeIcon

更换用户头像。

- GET
- RequiresAuthentication


    src string


### /user/edit

编辑用户信息。

- POST
- RequiresAuthentication

    
    userResume string
    userNick string
    userEmail string
    phone string
    imgSrc string


## Others

### /count

- GET
- RequiresRoles
- RequiresAuthentication

访问次数统计。

Answer:

    {
        "status": "ok",
        "count": 1
    }

### /init
- GET POST

初始化数据库（在删库之后使用）. 将会生成一个管理员：admin0, 123456.

Answer:

    Success


## Drifting

### /drifting/new

- POST
- RequiresRoles: reader
- RequiresPermissions: new:drifting


    data: int  //创建好的漂流的id

Request:

    {
        "bookName":"Shirro",
        "author":"Romon Handson",
        “isbn”:"XXXXXXXXXXXXXXXX",
        "position":"东起第一根电线杆",
        "guardian":"Mr.WANG"
    }
    
Answer:

    {
        "msg": "发起成功",
        "code": 1,
        "succ": true,
        "data": 1,
        "oper": "default"
    }


### /drifting/all

获取该用户本人发起的所有漂流。

- GET
- RequiresRoles: reader

Answer:

    [
        {
                "driId": 3,
                "uid": 2,
                "curUid": null,
                "bookName": "基本乐理通用教材",
                "author": "李重光",
                "isbn": "978-7-04-015553-4",
                "curPosition": "石大19号楼值班室",
                "curState": null,
                "available": true,
                "guard": "宿管阿姨",
                "imgSrc": null,
                "createTime": null,
                "events": null
            }
    ]
    
### /drifting/id/{id}

- GET
- RequiresAuthentication

Request

    /drifting/id/1
    
Answer:

    {
        "driId": 1,
        "uid": 2,
        "bookName": "Shirro",
        "author": "Romon Handson",
        "isbn": null,
        "curPosition": "东起第一根电线杆",
        "curState": null,
        "available": true,
        "guard": null,
        "imgSrc": null,
        "createTime": null,
        "events": null
    }
    
    
### /drifting/edit

- POST
- RequiresRoles: reader
- RequiresPermissions: new:drifting


    driftId int
    bookName string
    author string
    isbn string
    position string
    guardian string

### /drifting/borrow

- GET
- RequiresRoles: reader
- RequiresPermissions: new:drifting

借阅该书籍。将available置为false，curUid置为借阅者的uid。

    driftId int
    
### /drifting/return

- GET
- RequiresRoles: reader
- RequiresPermissions: new:drifting

借阅该书籍。将available置为true。

    driftId int
    
### /drifting/bylimit

- GET
- RequiresAuthentication


    cursor int
    limit int
    desc bool

Request:
    
    cursor=2&limit=30&desc=false
    
Answer:

    [
        {
            "driId": 2,
            "uid": 2,
            "curUid": null,
            "bookName": "古代民主与现代民主",
            "author": "M.I.芬利",
            "isbn": "978-7-100-16712-3",
            "curPosition": "石大南教110讲桌",
            "curState": null,
            "available": true,
            "guard": "无",
            "imgSrc": null,
            "createTime": null,
            "events": null
        },
        {
            "driId": 3,
            "uid": 2,
            "curUid": null,
            "bookName": "基本乐理通用教材",
            "author": "李重光",
            "isbn": "978-7-04-015553-4",
            "curPosition": "石大19号楼值班室",
            "curState": null,
            "available": true,
            "guard": "宿管阿姨",
            "imgSrc": null,
            "createTime": null,
            "events": null
        }
    ]
    

## Admin

### /admin/getallusers

- GET
- RequiresRoles: admin


Answer:

    [
        {
            "uid": 2,
            "uname": "user0",
            "nick": "superman",
            "created": "2020-07-01T13:35:14.388+00:00",
            "updated": "2020-07-04T05:46:42.140+00:00",
            "email": "",
            "phone": "12355555",
            "resume": null,
            "imgSrc": null,
            "disabled": false,
            "roles": [
                {
                    "rid": 2,
                    "rname": "读者",
                    "rval": "reader"
                }
            ],
            "perms": [
                {
                    "pid": 2,
                    "pname": "发布书评",
                    "pval": "new:review"
                },
                {
                    "pid": 4,
                    "pname": "发布漂流",
                    "pval": "new:drifting"
                },
                {
                    "pid": 3,
                    "pname": "发布评论",
                    "pval": "new:comment"
                }
            ]
        }
    ]

### /admin/disableuser

封禁帐号。

- GET
- RequiresRoles: admin
- RequiresPermissions: disable:reader

    
    id int
    
Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "oper": "default"
    }

### /admin/enableuser
    

- GET
- RequiresRoles: admin
- RequiresPermissions: enable:reader

    
    id int
    
Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "oper": "default"
    }
    

### /admin/addpermission

- GET
- RequiresRoles: admin
- RequiresPermissions: addperm:reader

    
    id int
    val string

### /admin/removepermission

- GET
- RequiresRoles: admin
- RequiresPermissions: rmperm:reader


    id int
    val string

Request:

    /admin/removepermission?id=2&val=new:drifting
    
Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "oper": "default"
    }
    
    
### /admin/disablereview

封禁和解封书评。

- GET
- RequiresRoles: admin


    rid int
    disable bool

## Report

### /report/review

举报书评。

- POST
- RequiresRoles reader


    reviewId int
    content string

### /report/admin/reviews

- GET
- RequiresRoles admin

管理员查看所有对书评的举报。

Answer:

    [
        {
            "reportId": 14,
            "review": {
                "rid": 2,
                "createTime": "2020-07-01T14:13:28.923+00:00",
                "content": "<h3><font color=\"blue\">爱国、敬业、诚信、友善</font></h3>",
                "title": "莫泊桑小说",
                "disabled": false
            },
            "reporter": {
                "uid": 2,
                "uname": "user0",
                "nick": "superman",
                "created": "2020-07-01T13:35:14.388+00:00",
                "updated": "2020-07-01T13:35:14.388+00:00",
                "email": "",
                "phone": "12355555",
                "resume": null,
                "disabled": false
            },
            "content": "!DJFOSJF>>>>>"
        },
        {
            "reportId": 14,
            "review": {
                "rid": 2,
                "createTime": "2020-07-01T14:13:28.923+00:00",
                "content": "<h3><font color=\"blue\">爱国、敬业、诚信、友善</font></h3>",
                "title": "莫泊桑小说",
                "disabled": false
            },
            "reporter": {
                "uid": 2,
                "uname": "user0",
                "nick": "superman",
                "created": "2020-07-01T13:35:14.388+00:00",
                "updated": "2020-07-01T13:35:14.388+00:00",
                "email": "",
                "phone": "12355555",
                "resume": null,
                "disabled": false
            },
            "content": "!DJFOSJF>>>>>"
        }
    ]

### /report/admin/reviews

管理员处理举报。每个举报信息只能被处理一次，处理后不会被同名GET方法查询到。

- POST
- RequiresRoles admin
- process:report


    reportId int 必填
    status string 必填
    log string

### /friends/add

- GET


    uid int


### /friends/del

- GET


    uid int
    
### /friends/all

- GET


Answer:
    
    [
        {
            "uid": ,
            "uname": "abc",
            "nick": "cccc",
            "created": null,
            "updated": null,
            "imgSrc": null,
            "disabled": false,
            "roles": null,
            "perms": null
        }
    ]

### /friend/find

- GET

    uid int

## Message

### /message/update

设置user的更新时间为当前时间。返回更新后的时间。

- GET
- RequiresAuthentication

Request:

    /message/update
    
Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "data": "2020-07-04T06:14:36.579+00:00",
        "oper": "default"
    }

### /message/getupdated

- GET
- RequiresAuthentication

返回user的更新时间。

Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "data": "2020-07-04T06:14:36.579+00:00",
        "oper": "default"
    }
    
    
### /message/send

- POST
- RequiresPermissions: new:message

    receiverId int
    content string
    
Request:

    {
        "receiverId": 1,
        "content": "!DJFOSJF>>>>>"
    }
    
Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "oper": "default"
    }
    
    
### /message/allsent

返回发送的所有信息。包含接受者的部分信息。

- GET
- RequiresAuthentication


Answer:

    [
        {
            "message": {
                "msgId": 1,
                "uid": 2,
                "rvId": 1,
                "msg": "!DJFOSJF>>>>>",
                "time": "2020-07-04T06:43:12.582+00:00"
            },
            "user": {
                "uid": 1,
                "uname": "admin001",
                "nick": "管理员1",
                "created": null,
                "updated": null,
                "imgSrc": null,
                "disabled": null,
                "roles": null,
                "perms": null
            }
        }
    ]
    

### /message/all

返回收到的所有信息。包含发送者的部分信息。

- GET
- RequiresAuthentication

Answer:

    [
        {
            "message": {
                "msgId": 1,
                "uid": 2,
                "rvId": 1,
                "msg": "!DJFOSJF>>>>>",
                "time": "2020-07-04T06:43:12.582+00:00"
            },
            "user": {
                "uid": 2,
                "uname": "user0",
                "nick": "superman",
                "created": "2020-07-01T13:35:14.388+00:00",
                "updated": null,
                "imgSrc": null,
                "disabled": false,
                "roles": null,
                "perms": null
            }
        }
    ]

- GET
- RequiresAuthentication

## Upload and Resource

资源文件存储在application.properties中的user.resource.path处。

### /upload

上传文件。上传成功后将返回一个文件名，用来访问该文件。

- POST

    value = "file"

Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "oper": "default",
        "data": "uujcozinkhuopkrazmbn.png"
    }


### /img/{filename}

- GET

返回文件名为filename的，用upload上传的图片。

## authorization

登录,权限有关的事情.

### token

登录时,会返回一个token.与服务器的交互需要在header中携带token.

例子请参考现有代码.

token可能会过期.

### Permissions

不同用户拥有不同权限，这些权限是可以动态调整的。（API待补充）


#### Exceptions

如果没有权限进行操作，或者token过期，会返回错误。

（待补充）