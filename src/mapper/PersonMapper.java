package mapper;

import enitry.Grade;
import enitry.Person;

import java.util.HashMap;
import java.util.List;

/**
 * @author 刘棋军
 * @date2019-03-19
 *
 * 使用动态代理方式：
 * <1>创建一个接口，但是这个接口没有实现类  接口的名字必须和mapper.xml文件的名字一样
 * <2>方法：方法名必须和mapper文件中的id名一样，参数返回时也必须一样
 * <3>在测试方法中直接使用接口定位mapper
 */

public interface PersonMapper {

    public List<Person> selectAll();

    public void insertPerson(Person p );
    public void updatePerson(Person p );
    public void deletePerson(int id);
    public List<Person> selectByColumn(String column);
    public List<Person> likeSelectWithMap(HashMap map);
    public List<Person> selectByAge();
    public HashMap<String,Object> selectByIdReMap();
    public Person selectByAAndNameWithIf( HashMap<String,Object> map);
    public List<Person> selectByForeach(Grade grade);
}
