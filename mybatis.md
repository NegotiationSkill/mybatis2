


优化config.xml
    <1>将config.xml中的数据库配置写到properties文件中，这样便于后期的维护和改动。
         dataSource.properties文件中的内容
            driver=com.mysql.cj.jdbc.Driver
            url = jdbc:mysql://localhost:3306/mybatis
            username = root
            password = 1602liquqijun
         在config.xml文件中加载dataSource.properties文件
         <configuration>
            <properties source="dataSourcs.properties"/>
            //然后将dataSource.properties中的数据读到config.xml中，和el表示一样
            例： <property name="driver" value="${driver}"/>
         </configuration>
别名：
    之前的在mapper.xml文件中写返回值和参数的时候要写全类名很麻烦，可通过别名的方法解决此问题
    在congfig.xml文件中配置即可
    <configuration>
         <TypeAliases>
            <typeAlias type="enitry.Person" alias="Person"/>
         </TypeAliases>
         也可以同时设置多个别名
         <TypeAliases>
            <package= ""包名"/>  //此包中的类都有别名默认为类名
         </TypeAliases>
    </configuration>
    
类型转换：TypeHandler
    1.实现方法
        <1>实现接口TyepeHandler
        <2>继承BaseTypeHandler
    2.将类型转换器配置到config.xml文件中：在<configuration></configuration>中配置
    
#{}和 ${}的区别：
    <1>当传的参数为简单类型的时候  #{param}  ${value} 这里的$必须是value
    <2>当传的参数是其他引用类型是 #{property} ${property}  此时他们必须和对象的属性名一样
    <3>#{}会自动给string类型加上‘’  但是${}不会
    <4>${}多用于 动态的列查询，排序
    
paramType为HashMap(返回值和参数都可以是HashMap)
<1>当paramType为HashMap的时候#{param}中的param必须是map的key
<2>当返回值为HashMap的时候，查询语句中的别名就是map的key
    例如：  select  pesonName name,age userage from person where  id = 3  HashMap的可以就是 name   userage

当java对象的属性名和jdbc表的列名不一样的是，处理使用resultMap之外的另外一种方法。（别名法）
 select id son,pesonName name from person where age>23
 id：是mysql的列名  son：是java类中的属性名 
 
 
 动态sql标签：
 <if></if>
    用来判断参数的是否为存在或者为空的
    例：
        <!---使用if   where标签-->
            <select id="selectByAAndNameWithIf" parameterType="HashMap" resultType="Person">
                select * from person where
                <if test="age!=null and name!=null and name!='' and age>0">
                    pesonName=#{name} and age=#{age}
                </if>
             </select>
             
 <where></where> 可以处理第一个and
         <select id="selectByAAndNameWithIf" parameterType="HashMap" resultType="Person">
                select * from person where
                <if test="and name!=null and name!='' ">
                    pesonName=#{name} 
                </if>
               <if test="age!=null  and age>0">
                   and age=#{age}
               </if>
            </select>
            上面子这种情况假如没有传来name时就会出错，因为sql语句就成了select * from person where  and age=#{age}
            使用where可以自动处理第一个and没有时会自动加上，有时会自动去掉
             <select id="selectByAAndNameWithIf" parameterType="HashMap" resultType="Person">
                    select * from person
                   <where>
                       <if test="name!=null and name!=''">
                           pesonName=#{name}
                       </if>
                       <if test="age!=null  and age>0">
                           and  age=#{age}
                       </if>
                   </where>
            
                </select>

 <foreach><foreach>  对象的属性   集合list  数组  对象数组
   用来迭代集合，数组等，比如：select * from person where son in(12,23,45);
    <1>对象属性的遍历（对象属性为数组或者集合） 
    <2>当为数组是变名
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    一级缓存：同一个sqlSession多次查询同一个值，就会使用一级缓存。session就是一级缓存。mybatis默认开启一级缓存。大家了解即可。
    二级缓存：同一个mapper.xml文件就是二级缓存
    	PersonMapper mapper = session.getMapper(PersonMapper.class);
            PersonMapper mapper1 = session1.getMapper(PersonMapper.class);
            这里的mapper和mapper1共享二级缓存
    
    二级缓存需手动开始：
    	<1>在cofig.xml文件中配置
    		
    
    ```
     <settings>
            <!--开启二级缓存-->
            <setting name="cacheEnabled" value="true"/>
        </settings>
    ```
    	<2>在mapper.xml文件中声明
    	
    
    ```
     <!-- 声明二级缓存-->
        <cache/>
    ```
    	<3>序列化目标类：需要将使用到的类序列化，就是实现Serializable接口（因为二级缓存在硬盘上）。
    
    当sqlSession.close时，一级缓存的中的数据才会转存到二级缓存中。
    
    二级缓存的关闭：
    
    ```
    <select id="selectById" resultType="Person" parameterType="int" useCache="false"> 
          select * from Person where id = #{id}
        </select>
    ```
    
    注：一级二级缓存，都是在执行session.commit()时清除，防止缓存数据和数据库数据不一致，导致错读。
    	
