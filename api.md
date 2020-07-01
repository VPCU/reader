# API

### /newreview

- POST
- RequiresRoles: reader

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


### /square/reviews

- GET
- RequiresRoles: reader, admin

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

初始化数据库（在删库之后使用）。将会生成一个管理员：admin0, 123456.

Answer:

    Success


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

### /newreview

- GET

返回所有书评（待改进流加载）

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


## authorization

登录,权限有关的事情。

### token

登录时，会返回一个token。与服务器的交互中，需要在header中携带token。

例子请参考现有代码。

token可能会过期。

### Permissions

不同用户拥有不同权限，这些权限是可以动态调整的。（API待补充）


#### Exceptions

如果没有权限进行操作，或者token过期，会返回错误。

（待补充）