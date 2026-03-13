### 基于SpringBoot + Vue的供应链采购销售系统.

供应链采购销售系统、进销存一体化系统、ERP采购销售模块、供应商协同系统、智能采购销售管理

###### 管理员：
收货地址、预警配置、公告管理、商品管理、商品折扣、商品类型、配送物流、订单管理、库房出库、库房入库、库存预警、库房管理、库房记录、用户管理、供应商管理、原料库存、原件采购、出库记录、员工管理、原件出库、原件市场、询价记录、数据统计、订单年月统计。

###### 用户：
账户注册登录、密码修改、个人信息、收货地址、订单物流、订单管理、商品选购、线上支付。

###### 供应商：
账户注册登录、密码修改、企业信息、供货原件、供货订单、客户询价。

###### 员工：
账户注册登录、密码修改、采购检验、个人信息。

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 默认后台账户密码
[管理员]
admin
1234qwer

[供应商]
SUP-fank
1234qwer

[员工]
STF-fank
1234qwer

[用户]
fank
1234qwer

#### 项目截图
暂无

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/3af18fcd-3af2-4bc4-8931-1702e79c2ca4.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/b6764672-151f-422c-96f5-025280c23563.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/2fe232a3-e475-4813-b807-e4dbcc7f2ab4.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/b874a547-72b7-452c-bd98-47ebed59ac0d.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/2fc18ebb-003f-4a4b-91dd-0194cb4b3a68.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/ad98b898-5d0e-4f3a-8f4d-828156080f07.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/2a85ccee-2797-4c31-85f8-06a54a6cb03c.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/a980b834-3957-43a2-957e-76451582c079.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/0a24ad8d-daaf-45e2-b518-64107cd219f8.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/48953281-0e26-480a-889b-56ac8efd6b4d.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/fe9dcdd3-02e1-40c1-94b5-67fe5b93b8c0.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/00034149-c41a-45bb-9426-49039dd27854.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/fde68658-fa2c-437c-b067-c6a8df7b587f.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/4430cf5c-5f4b-4a75-b336-7361f3a2a334.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/ed68d0b1-ad55-4275-a055-9f02c75e49cb.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/721aaaee-9ca2-425a-ab5a-a8cb78a3a6b3.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/e1bf026d-621b-4010-939f-77d812585e73.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/0236c0ee-6908-498a-8ecf-989e70173a2f.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/d7912308-0abe-463d-9cbd-1e696402fe82.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/41b6f445-7300-4b6b-b578-7c78cc291659.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/d47ef55d-f9ac-4299-91a2-0f646e28b4a0.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/8a4cd814-b22a-43ca-bc0a-e9a1d5d16cf8.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/c3945fa6-6f5f-40df-8ff2-9a051d796181.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/6d426c74-5f42-40ad-a026-255caba4cc5c.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/c1f96c4e-4a62-4f95-8287-17628056bda3.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/5c1cfcd5-f920-464b-a22a-128bc3a539c8.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/c1cea100-1c8d-4073-8ac4-937f8d093e12.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/4de703c6-fffd-46a5-b7ec-00d6353d68ac.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png) |

#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

#### 其它资源

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)
