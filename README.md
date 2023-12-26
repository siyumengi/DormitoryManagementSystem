## 目的

各位老师，我正在开发一个宿舍管理系统。我们的目标是通过这个系统，让宿舍管理变得更加高效，也让管理员的工作变得更轻松。
在这个系统中，我设计了四个主要模块：学生管理、寝室管理、消费管理和报修管理。我认为这些模块都是宿舍管理中必不可少的组成部分。

## 主要功能

以下是系统的一些主要界面设计：
登录界面设计简洁明了，便于用户快速登录系统。

### 管理员界面包括以下几个部分：

1. 首页：提供直观的数据概览，帮助管理员快速了解宿舍的整体情况。
2. 学生页面：详细记录每个学生的信息，方便管理员进行查询和管理。
3. 宿管页面：针对宿管人员的操作界面，包含宿舍分配、调整等管理功能。
4. 宿舍页面：展示所有宿舍的信息，包括宿舍状态、住宿学生等详细内容。
5. 寝室页面：具体到每个寝室的详细信息和管理功能。
6. 缴费页面：方便管理员查看和管理学生的缴费情况。
7. 宿舍状态页面：实时更新宿舍的状态信息，便于管理员监控和决策。
8. 维修页面：处理学生的维修申请，跟踪维修进度和记录。
9. 维修记录页面：保存所有的维修记录，便于查询和分析。

### 学生界面主要包括：

1. 个人信息页面：学生可以查看和更新自己的个人信息。
2. 室友信息页面：展示同寝室的室友信息，促进同学间的交流和互动。
3. 维修报告页面：学生可以提交维修申请，跟踪维修进度。
4. 查询费用页面：学生可以查询自己的缴费记录和欠费情况。

## 技术选型

### 前端：

- Vue 2.6.x：用于构建用户界面和实现交互功能。
- Axios 0.21.x：用于发送HTTP请求，与后端进行数据交互。
- Element 2.15.x：提供丰富的UI组件，帮助我们快速构建美观且易用的界面。

后端：

- Java 8：作为主要的编程语言。
- Spring Boot 2：用于构建微服务架构，提高系统的可扩展性和稳定性。
- MyBatis：作为ORM框架，方便数据库操作。
- Redis：用于缓存数据，提高系统性能。

## 登录界面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579035841-54d0921d-3110-4454-90de-f66e1f60c171.png#averageHue=%23197d6b&clientId=u95d96dd4-a6d9-4&from=paste&height=1003&id=ud105d231&originHeight=1003&originWidth=1892&originalType=binary&ratio=1&rotation=0&showTitle=false&size=3315110&status=done&style=none&taskId=u941d3bea-4b33-41d6-9ff8-9adf993cc04&title=&width=1892)

## 管理员

### 首页

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703578956196-6ff67752-5719-4e32-af2b-2d10b89f454b.png#averageHue=%237c5f32&clientId=u95d96dd4-a6d9-4&from=paste&height=1020&id=u4410c397&originHeight=1020&originWidth=1920&originalType=binary&ratio=1&rotation=0&showTitle=false&size=2674199&status=done&style=none&taskId=u0e1f7481-c56e-4836-8979-4dabafe20ed&title=&width=1920)

### 学生页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579059106-d195b5c0-4303-4734-962c-132360140429.png#averageHue=%23fefdfd&clientId=u95d96dd4-a6d9-4&from=paste&height=1000&id=uf20fb98d&originHeight=1000&originWidth=1906&originalType=binary&ratio=1&rotation=0&showTitle=false&size=118972&status=done&style=none&taskId=u56036204-0e74-445b-b88a-6375804f493&title=&width=1906)

### 宿管页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579088692-7e23b0ab-41e0-4fa2-9dec-aa356b161731.png#averageHue=%23fefdfd&clientId=u95d96dd4-a6d9-4&from=paste&height=1001&id=ud5720510&originHeight=1001&originWidth=1911&originalType=binary&ratio=1&rotation=0&showTitle=false&size=104263&status=done&style=none&taskId=u08f08a55-f2fb-4fc2-b194-e36e99ca47e&title=&width=1911)

### 宿舍页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579115698-01ad8ea4-7bd4-4fbd-b4cc-d1280ac0806b.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=1004&id=u4dc7064d&originHeight=1004&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=76874&status=done&style=none&taskId=ub910c773-ec05-49f6-bbd0-ad859c6c145&title=&width=1912)

### 寝室页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579144231-3dd6a51b-424f-40e6-a7d0-ffe937582825.png#averageHue=%23fefefd&clientId=u95d96dd4-a6d9-4&from=paste&height=1004&id=u4ba563c8&originHeight=1004&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=107006&status=done&style=none&taskId=u717336e7-21fc-4ab4-9dcc-be8b3e76d06&title=&width=1912)

### 缴费页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579156732-10ab12b4-69c9-45a3-8b53-5032719222ad.png#averageHue=%23fefefd&clientId=u95d96dd4-a6d9-4&from=paste&height=1004&id=u7f2177de&originHeight=1004&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=106538&status=done&style=none&taskId=uf5cca964-f387-46bd-9f2f-f8d10f93c51&title=&width=1912)

### 宿舍状态页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579184801-17d8aabd-4e7f-4f2d-acf8-98e6534eed77.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=1004&id=u40ae92c8&originHeight=1004&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=63711&status=done&style=none&taskId=u361de6ba-804a-4784-91ad-07c998ce2e3&title=&width=1912)

### 维修页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579203677-538ed25a-37aa-4367-8fd4-96685935816b.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=1004&id=u3dcff34f&originHeight=1004&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=85555&status=done&style=none&taskId=u5287a71c-c283-4196-b2e7-3a4d2761d3d&title=&width=1912)

### 维修记录页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579222073-668a9297-412c-498b-9dac-e9700d2d9126.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=1004&id=ue9f4bdb2&originHeight=1004&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=101544&status=done&style=none&taskId=u5f237b64-c1ab-4a64-aca0-1693e79b1ea&title=&width=1912)

## 学生

### 学生个人信息

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579290004-b67ed330-ca6b-4b8d-825c-aeefda9ac981.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=970&id=u5f4f9a14&originHeight=970&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=39886&status=done&style=none&taskId=u9e0d91be-55f5-4630-b115-d4655660ad5&title=&width=1912)

### 室友信息

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579307974-85825d4f-5d4f-4d33-b349-a5bc72eb7f35.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=970&id=ua875ed67&originHeight=970&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=51185&status=done&style=none&taskId=ub1825b0e-79f7-45b2-940a-6b9377dde71&title=&width=1912)

### 维修报告页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579328524-82037eb6-7528-4923-bff7-e93f569a860a.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=970&id=u8ae80344&originHeight=970&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=61609&status=done&style=none&taskId=ucfbdfe0e-a256-4bdb-8d41-1c64674bffd&title=&width=1912)

### 查询费用页面

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32496237/1703579349088-18f6fff0-a099-4859-9a22-ffc75954cc9e.png#averageHue=%23fefefe&clientId=u95d96dd4-a6d9-4&from=paste&height=970&id=ud77166bf&originHeight=970&originWidth=1912&originalType=binary&ratio=1&rotation=0&showTitle=false&size=77957&status=done&style=none&taskId=u147cc5a4-3e64-4abf-8a05-756f0697a1f&title=&width=1912)