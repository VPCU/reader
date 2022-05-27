# 4 系统实现
## 4.1 应用的技术介绍
`		`Spring Boot 是由 Pivotal 团队提供的全新框架，2014 年 4 月发布 Spring Boot 1.0 2018 年 3 月 Spring Boot 2.0发布。它是对spring的进一步封装，其设计目的是用来简化 Spring 应用的初始搭建以及开发过程。

`	`JPA(Java Persistence API)意即Java持久化API，是Sun官方在JDK5.0后提出的Java持久化规范（JSR 338，这些接口所在包为javax.persistence，详细内容可参考https://github.com/javaee/jpa-spec）。Spring Data JPA是Spring Data家族的一部分，可以轻松实现基于JPA的存储库。此模块处理对基于JPA的数据访问层的增强支持。它使构建使用数据访问技术的Spring驱动应用程序变得更加容易。Spring Data JPA旨在通过减少实际需要的工作量来显著改善数据访问层的实现。 编写repository接口，包括自定义查找器方法，Spring将自动提供实现。

`	`Quasar是基于开源Vue.js的响应式框架，使用它可以快速创建具有多种功能的响应式网站/应用程序。Quasar的座右铭是：编写一次代码，并同时将其部署为web网站，移动应用程序和Electron应用程序。通过Quasar CLI，在Quasar Web组件的帮助下可以在极短的时间内开发应用程序。使用Quasar时，不需要其他繁重的组件库，例如Hammerjs，Momentjs或Bootstrap。自身就用于强大的组件库，并且占用的空间很小。

`	`Apache Shiro 是一种功能强大且易于使用的Java安全框架，它具有身份验证、访问授权、数据加密、会话管理等功能，可用于保护任何应用程序的安全。与spring security一样，都是一个作为权限管理的安全框架。但是与spring security相比，在于shiro使用了比较简单、易懂且易于使用的授权方式。

## 4.2 各功能模块的实现
`	`介绍各功能模块的实现细节及所实现的效果展示。

这是重点内容，可以结合界面截图、程序代码介绍。

共有前端技术介绍：

1、基于VUE的前端开发框架Quasar，使用router路由来管理页面跳转

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.001.png)

`	`2、采用包含型的页眉页脚，所有页面共享一个页眉和页脚，便于管理页面跳转。

### **登录注册**
![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.002.png)

图4-1 登录+注册

前端技术：q-input输入框控件，接收用户输入，q-btn按钮提交控件结合axios的post方法提交到后台处理。后端对应的API为/login，如果认证成功，将在应答中返回一个token，之后前端的请求凭借这个token便可验证身份。如果一段时间内没有活动，token会过期。

#### **广场-浏览书评，部分显示**
![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.003.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.004.png)

图4-2 浏览书评主界面+书评部分显示功能

前端技术：q-infinite-scroll无线滚动控件，q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，filters过滤器限制一个空间内可以出现的字数。@click事件触发与axios的post、get方法与后端交互。

后端对应的api是/reviews/bylimit，用来获得书评信息，需要提供offset，limit，desc三个参数，分别是游标，需要获得的书评的数量和排序。还使用了/reviews/countekil、/reviews/getekil、/reviews/setekil来处理赞；用/reviews/getlike、/reviews/setlike来处理收藏。


![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.005.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.006.png)

图4-3 查看书评详细内容+评论内容

前端技术：q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，q-dialog动态显示交互控件。@click触发函数，axios的post、get方法与后端交互。后端对应的api是/reviews/id/{id}和/comments/all?id=xx，分别获得书评和评论。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.007.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.008.png)

图4-4 评论书评+评论成功

前端技术：q-btn按钮控件触发动作，显示出q-dialog动态显示交互控件。@click触发函数，axios的post、get方法与后端交互。

后端技术：对应的API是/comments/new，需要书评的id、父亲评论的id和内容三个参数。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.009.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.010.png)

图4-5 点赞书评+收藏书评

前端技术：q-infinite-scroll无线滚动控件，q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，filters过滤器限制一个空间内可以出现的字数。@click事件触发与axios的post、get方法与后端交互。

后端技术：需要使用/reviews/getekil和/reviews/setekil两个API。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.011.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.012.png)

图4-6 发布书评+撰写书评

前端技术：q-input控件接收简单输入，q-dialog动态显示控件，q-editor控件允许输入富文本内容。@click触发函数，axios的post方法与后端交互。

后端技术：对应的API是/reviews/new，后端验证用户身份和权限后，将书评内容、当前时间等信息写入数据库。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.013.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.014.png)

图4-7 发起举报+举报成功

前端技术：q-dialog动态显示控件。@click触发函数，axios的post方法与后端交互。

后端技术：使用/report/review，传入书评的id和举报理由两个参数。后端会补充举报用户和被举报用户、举报时间等信息，生成一个举报信息对象，存入数据库。
#### **图书漂流**
![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.015.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.016.png)

图4-8 可借阅漂流书籍+不可借阅漂流书籍

前端技术：q-infinite-scroll无线滚动控件，q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，filters过滤器限制一个空间内可以出现的字数。@click事件触发与axios的post、get方法与后端交互。

后端技术：用到的API有/drifting/bylimit、/drifting/return、/drifting/borrow。借阅和归还时，只需要提供书籍的对应id，后端会完成相关操作的记录。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.017.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.018.png)

图4-9 借阅书籍+归还书籍

前端技术：（与上一个一致）q-infinite-scroll无线滚动控件，q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，filters过滤器限制一个空间内可以出现的字数。@click事件触发与axios的post、get方法与后端交互。

#### **商城**
![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.019.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.020.png)

图4-10 商城首页+分类查看

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.021.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.022.png)

图4-11 查看特定书籍+加入购物车

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.023.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.024.png)

图4-12 购物车+个人信息

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.025.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.026.png)

图4-13 购买书籍+购买成功

商城统一介绍前端技术： vue-pug语言统一解析json数据，并按照pug语言的格式排版。使用axios的post方法从后端获取json数据。

商城统一介绍后端技术：使用jsoup从当当网上面爬取的1000多本书籍以及其信息，包括封面，后端从数据库中读出所有记录，ORM映射成实体类，再对这些信息按照书籍种类进行分类，转换成dto对象后返回前端。
#### **我的**
![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.027.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.028.png)

图4-14 用户个人信息与修改个人信息

前端技术：q-item控件，q-card控件显示内容。@click事件触发，axios的get方法与后端交互。

后端技术：对应了/reviews/my、/reviews/like、/user/edit等API。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.029.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.030.png)

图4-15 我的收藏+我的书评

前端技术：q-infinite-scroll无线滚动控件，q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，filters过滤器限制一个空间内可以出现的字数。@click事件触发与axios的post、get方法与后端交互。对应后端的/reviews/my等API。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.031.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.032.png)

图4-16 我的好友+好友信息

前端技术：q-list控件显示各项信息，q-btn控件监听动作，axios的post方法与后端交互。

后端技术：对应了/friends/all等API。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.033.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.034.png)

图4-17 查看好友私信+管理员面板

前端技术（好友私信）：q-chat-message控件显示私信内容，q-input控件写回信，q-btn控件监听提交。axios的post方法与后端交互。

后端技术：对应了/message/send、/message/all、/messagesent等API。对于每个用户，提供了一个时间戳，记录该用户最后一次查看消息的时间，用来判断消息是否被阅读过。

![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.035.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.036.png)

图4-18 发起漂流+管理漂流

前端技术：q-input控件接收简单输入，q-dialog动态显示控件。@click触发函数，q-infinite-scroll无线滚动控件，q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，filters过滤器限制一个空间内可以出现的字数。@click事件触发与axios的post、get方法与后端交互。

后端技术：对应了/drifting/new等API。

#### **管理员功能**
![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.037.png)![](readme/Aspose.Words.d575bcb2-de7f-4a26-bdf0-1d4d671753f5.038.png)

图4-19 处理举报

前端技术：q-infinite-scroll无线滚动控件，q-card卡片展示，v-for遍历从后台获取的元素，v-html按照html解析富文本，filters过滤器限制一个空间内可以出现的字数。@click事件触发与axios的post、get方法与后端交互。

后端技术：使用/admin/disableuser、/admin/enableuser、/report/admin/reviews等API，处理过的举报信息会被做好记录，不会再次交给前端。
15

