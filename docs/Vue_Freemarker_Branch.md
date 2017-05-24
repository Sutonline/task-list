### 工程重构
    Problems:  因为目前是基于view的刷新，所以导致在做查询的时候和其他操作的时候不能完全将view和data分离。所以改为freemarker和vue来实现。
    
#### 实现步骤
1. 去掉thymeleaf的依赖和spring mvc中的配置
2. 增加freemarkder的依赖和spring mvc的依赖
3. 增加vue
4. 实现login页面的逻辑
5. 实现taskList的逻辑