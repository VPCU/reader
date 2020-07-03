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
        "token": "b856a716-9e33-477b-8113-dffcef5c7a2f"
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
        "position":"东起第一根电线杆"
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

获取发起的所有漂流。

- GET
- RequiresRoles: reader

Answer:

    [
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


    dirftId int
    bookName string
    author string
    isbn string
    position string
    guardian string

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
            "pwd": "",
            "salt": "",
            "disabled": false,
            "created": "2020-07-01T13:35:14.388+00:00",
            "updated": "2020-07-01T13:35:14.388+00:00",
            "roles": [
                "reader"
            ],
            "perms": [
                "new:review",
                "new:drifting",
                "new:comment"
            ]
        }
    ]

### /admin/setdisabled

封禁帐号。

- GET
- RequiresRoles: admin
- RequiresPermissions: disable:reader

    
    id int
    disabled bool
    
Answer:

    {
        "msg": "ok",
        "code": 1,
        "succ": true,
        "oper": "default"
    }

    
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