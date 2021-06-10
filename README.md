# mybatis-plus
mybatisplus逻辑删除自动填充
1. 在application.yml中开启mybatis-plus的逻辑删除

```cpp
mybatis-plus:
  global-config:
    db-config:
      # 逻辑删除规则
      # 1表示删除，0表示未删除
      logic-delete-value: 1
      logic-not-delete-value: 0
  configuration:
    #打印SQL
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

2. 新建user表如下
![user表结构及数据](https://img-blog.csdnimg.cn/2021061020402435.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80Njg0MTE5OQ==,size_16,color_FFFFFF,t_70)


3. 对应实体类，加上逻辑删除注解以及自动填充注解

```cpp
@Data
public class User {
    private long id;
    //自动填充注解
    @LogicDelFill("'已删除'")
    private String name;
    private int age;
    //逻辑删除注解
    @TableLogic
    private String delflag;
    //自动填充注解
    @LogicDelFill("now()")
    private String deltime;
}
```
4. 实体类mapper，继承mybatisplus的basemapper

```cpp
public interface UserMapper extends BaseMapper<User> {
}
```
#### 4、开始测试
1. 调用userMapper继承自Basemapper的deleteById方法
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210610204520917.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80Njg0MTE5OQ==,size_16,color_FFFFFF,t_70)
2. 查看日志，可以看到deleteById方法已经成功改写为update语句，并且拼接上自动填充字段
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210610204739902.png)
