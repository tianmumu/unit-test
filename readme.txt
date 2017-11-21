1.@Mock和@InjectMocks的区别：
  @Mock: 创建一个Mock.
  @InjectMocks: 创建一个实例，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。
  
2.@Rollback，默认为@Rollback(true),如果加上这个注解，默认SQL执行完毕会回滚。
     设置为：@Rollback(false),SQL语句执行完毕不会回滚
     
3.  


  