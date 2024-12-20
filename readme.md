# Spring+vue3的ai对战平台

一个蛇类ai对战游戏平台Spring+vue3+SpringSecurity+Mybatis-plus+Mysql

[github开源链接](https://github.com/godicewang/Kob)

[游戏部署链接](https://www.godice.cn/pk/)

- **技术栈**：Vue3、SpringBoot、SpringSecurity、Mybatis-plus、Mysql
- **项目介绍**：BotArena是一款在线程序对抗平台。主要功能包括：注册、登录、自动匹配、在线对战、对战记录、比赛回访、积分排行榜。支持多种对战模式（人人、人机、机器间）
- 项目亮点：
  - 通过整合Spring Security、JWT（Access Token+Fresh Token）与OAuth2技术，实现一键登录
    - 解决跨域问题、解决GET请求明文传输token、易被XSS获取Cookie问题
    - 优化前端后端&后端微服务间的访问控制——避免未授权接口泄露问题
  - 利用WebSocket技术，实现前后端即时通信机制，确保游戏交互的低延迟，提升游戏体验
  - 实现了一套ELO分数和等待时间的智能匹配算法，有效缩减玩家等待时间，同时保障比赛公平
  - 采用生产者消费者模型、策略模式并结合Docker处理用户提交的代码，提升系统可维护性
    - 避免RCE漏洞的产生
  - 结合Minimax Search和alpha-beta剪枝技术，对AI决策逻辑优化，显著提高机器对战智能水平



