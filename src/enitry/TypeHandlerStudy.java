package enitry;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 刘棋军
 * @date2019-03-20
 */

public class TypeHandlerStudy extends BaseTypeHandler<Boolean> {
    /**  将java ----> jdbc
     * @param preparedStatement :操作对象
     * @param i  ：操作对象的位置
     * @param b  ：就是java数据
     * @param jdbcType  ：jdbc数据类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Boolean b, JdbcType jdbcType) throws SQLException {
        if(b){
            //如果java来的值是true将preparedStatement的i字段转换层1
            preparedStatement.setInt(i,1);
        }else{
            preparedStatement.setInt(i,0);
        }
    }

    /**
     * jdbc  ----> java
     * @param resultSet:返回的结果
     * @param s
     */
    @Override
    public Boolean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //如果查询出来的值为1，返回true，为0返回false
        return resultSet.getInt(s)==1?true:false;
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //如果查询出来的值为1，返回true，为0返回false
        return resultSet.getInt(i)==1?true:false;
    }

    @Override
    public Boolean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        //如果查询出来的值为1，返回true，为0返回false
        return callableStatement.getInt(i)==1?true:false;
    }


}
