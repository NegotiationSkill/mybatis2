package enitry;

import mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.naming.Name;
import java.io.IOException;
import java.io.Reader;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 刘棋军
 * @date2019-03-17
 */

public class Test {
    public static void main(String[] args) throws IOException {
        //1.加载config.xml配置问价‘
        Reader input = Resources.getResourceAsReader("config.xml");
        //2.创建sqlSessionFactory
        SqlSessionFactory factory =  new SqlSessionFactoryBuilder().build(input);
        //3.创建sqlSession 它就等同于我们之前的connection
        SqlSession session = factory.openSession();
        SqlSession session1 = factory.openSession();
        //4.获得映射
        PersonMapper mapper = session.getMapper(PersonMapper.class);
        PersonMapper mapper1 = session1.getMapper(PersonMapper.class);
        mapper.selectAll();
        mapper1.selectAll();

        //插入
        //insert(session, mapper,new Person(8,"王司徒",25,true));
        //更新
        //update(session, mapper,new Person(8,"王司徒",25,true));
        //删除
        //delete(session, mapper, 3);
        //selectAll(mapper);
        //使用${}查询
       // System.out.println(selectByColumn(mapper,"pesonName"));
        //参数为hashMap查询
       // HashMap<String, Object> map = new HashMap<String, Object>();
       // map.put("name","吕");
       // map.put("age",25);
       // likeSelectWithHashMap(mapper,map);
        //返回值为HashMap
      //  HashMap<String, Object> map = mapper.selectByIdReMap();
       // System.out.println(map.get("name"));
        //java对象的属性名和jabc的列名不一样
        //System.out.println(mapper.selectByAge());

/*
        //使用if标签
        HashMap<String, Object> map = new HashMap<String, Object>();
       // map.put("name","玄德");
        map.put("age",22);
        System.out.println(selectByAAndNameWithIWithIf(mapper,map));*/

        //使用foreach  遍历对象的属性
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(5);
        Grade grade = new Grade();
        grade.setPersons(list);
        System.out.println(selectByForeach(mapper,grade));

    }

    private static List<Person> selectByForeach(PersonMapper mapper, Grade grade) {
        return  mapper.selectByForeach(grade);
    }

    private static Person selectByAAndNameWithIWithIf(PersonMapper mapper, HashMap<String,Object> map) {
            return mapper.selectByAAndNameWithIf(map);
    }

    private static  List<Person>  selectByColumn(PersonMapper mapper,String column){
        return mapper.selectByColumn(column);
    }
    private static void delete(SqlSession session, PersonMapper mapper,int id) {
        mapper.deletePerson(id);
        session.commit();
    }

    private static void update(SqlSession session, PersonMapper mapper,Person p1) {
        mapper.updatePerson(p1);
        session.commit();
    }

    private static void insert(SqlSession session, PersonMapper mapper,Person p) {
        mapper.insertPerson(p);
        session.commit();
        selectAll(mapper);
    }

    private static void selectAll(PersonMapper mapper) {
        //查询
        List<Person> personList = mapper.selectAll();
        System.out.println(personList);
    }
    private static void likeSelectWithHashMap(PersonMapper mapper,HashMap<String, Object> map){
       System.out.println(mapper.likeSelectWithMap(map)) ;
    }

}
