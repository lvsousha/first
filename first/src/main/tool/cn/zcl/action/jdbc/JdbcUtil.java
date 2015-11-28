package cn.zcl.action.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtil {

	public Connection connect(String drivename, String url, String username, String password) throws Exception{
		Connection connection;
		Class.forName(drivename);
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	public ResultSet select(Connection conn, String sql) throws Exception{
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	/*
	 * 获取该用户下的所有表
	 */
	public void getALLTableList(Connection conn, String schemaname){
		try {
			DatabaseMetaData dbMetaData = conn.getMetaData();
			String[] types = { "TABLE" };
            ResultSet rs = dbMetaData.getTables(null, schemaname, "%", types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");  //表名
                String tableType = rs.getString("TABLE_TYPE");  //表类型
                String remarks = rs.getString("REMARKS");       //表备注
                System.out.println(tableName + "-" + tableType + "-" + remarks);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	 /**
     * 获得该用户下面的所有视图
     */
    public void getAllViewList(Connection conn, String schemaName) {
         try{
 			DatabaseMetaData dbMetaData = conn.getMetaData();
             String[] types = { "VIEW" };
             ResultSet rs = dbMetaData.getTables(null, schemaName, "%", types);
             while (rs.next()){
                 String viewName = rs.getString("TABLE_NAME"); //视图名
                 String viewType = rs.getString("TABLE_TYPE"); //视图类型
                 String remarks = rs.getString("REMARKS");      //视图备注
                 System.out.println(viewName + "-" + viewType + "-" + remarks);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    /**
     * 获得数据库中所有方案名称
     */
    public void getAllSchemas(Connection conn){
        try{
 			DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getSchemas();
            while (rs.next()){
                String tableSchem = rs.getString("TABLE_SCHEM");
                System.out.println(tableSchem);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 获得表或视图中的所有列信息
     */
    public void getTableColumns(Connection conn, String schemaName, String tableName) {

        try{
        	DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getColumns(null, schemaName, tableName, "%");
            while (rs.next()){
                    String tableCat = rs.getString("TABLE_CAT");//表目录（可能为空）
                    String tableSchemaName = rs.getString("TABLE_SCHEM");//表的架构（可能为空）
                    String tableName_ = rs.getString("TABLE_NAME");//表名
                    String columnName = rs.getString("COLUMN_NAME");//列名
                    int dataType = rs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
                    String dataTypeName = rs.getString("TYPE_NAME");//java.sql.Types类型   名称
                    int columnSize = rs.getInt("COLUMN_SIZE");//列大小
                    int decimalDigits = rs.getInt("DECIMAL_DIGITS");//小数位数
                    int numPrecRadix = rs.getInt("NUM_PREC_RADIX");//基数（通常是10或2）
                    int nullAble = rs.getInt("NULLABLE");//是否允许为null
                    String remarks = rs.getString("REMARKS");//列描述
                    String columnDef = rs.getString("COLUMN_DEF");//默认值
                    int sqlDataType = rs.getInt("SQL_DATA_TYPE");//sql数据类型
                    int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");   //SQL日期时间分?
                    int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");   //char类型的列中的最大字节数
                    int ordinalPosition = rs.getInt("ORDINAL_POSITION");  //表中列的索引（从1开始）

                    System.out.println(tableCat + "-" + tableSchemaName + "-" + tableName_ + "-" + columnName + "-"
                            + dataType + "-" + dataTypeName + "-" + columnSize + "-" + decimalDigits + "-" + numPrecRadix
                            + "-" + nullAble + "-" + remarks + "-" + columnDef + "-" + sqlDataType + "-" + sqlDatetimeSub
                            + charOctetLength + "-" + ordinalPosition);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
    }

    /**
     * 获得一个表的索引信息
     */
    public void getIndexInfo(Connection conn, String schemaName, String tableName) {
        try{
        	DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getIndexInfo(null, schemaName, tableName, true, true);
            while (rs.next()){
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");//非唯一索引(Can index values be non-unique. false when TYPE is  tableIndexStatistic   )
                String indexQualifier = rs.getString("INDEX_QUALIFIER");//索引目录（可能为空）
                String indexName = rs.getString("INDEX_NAME");//索引的名称
                short type = rs.getShort("TYPE");//索引类型
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");//在索引列顺序号
                String columnName = rs.getString("COLUMN_NAME");//列名
                String ascOrDesc = rs.getString("ASC_OR_DESC");//列排序顺序:升序还是降序
                int cardinality = rs.getInt("CARDINALITY");   //基数
                System.out.println(nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-" + ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 获得一个表的主键信息
     */
    public void getAllPrimaryKeys(Connection conn, String schemaName, String tableName) {
        try{
        	DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getPrimaryKeys(null, schemaName, tableName);
            while (rs.next()){
                String columnName = rs.getString("COLUMN_NAME");//列名
                short keySeq = rs.getShort("KEY_SEQ");//序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
                String pkName = rs.getString("PK_NAME"); //主键名称
                System.out.println(columnName + "-" + keySeq + "-" + pkName);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 获得一个表的外键信息
     */
    public void getAllExportedKeys(Connection conn, String schemaName, String tableName) {

        try{
        	DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getExportedKeys(null, schemaName, tableName);
            while (rs.next()){
                String pkTableCat = rs.getString("PKTABLE_CAT");//主键表的目录（可能为空）
                String pkTableSchem = rs.getString("PKTABLE_SCHEM");//主键表的架构（可能为空）
                String pkTableName = rs.getString("PKTABLE_NAME");//主键表名
                String pkColumnName = rs.getString("PKCOLUMN_NAME");//主键列名
                String fkTableCat = rs.getString("FKTABLE_CAT");//外键的表的目录（可能为空）出口（可能为null）
                String fkTableSchem = rs.getString("FKTABLE_SCHEM");//外键表的架构（可能为空）出口（可能为空）
                String fkTableName = rs.getString("FKTABLE_NAME");//外键表名
                String fkColumnName = rs.getString("FKCOLUMN_NAME"); //外键列名
                short keySeq = rs.getShort("KEY_SEQ");//序列号（外键内值1表示第一列的外键，值2代表在第二列的外键）。

                /**
                 * hat happens to foreign key when primary is updated:
                 * importedNoAction - do not allow update of primary key if it has been imported
                 * importedKeyCascade - change imported key to agree with primary key update
                 * importedKeySetNull - change imported key to NULL if its primary key has been updated
                 * importedKeySetDefault - change imported key to default values if its primary key has been updated
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility)
                 */
                short updateRule = rs.getShort("UPDATE_RULE");

                /**
                 * What happens to the foreign key when primary is deleted.
                 * importedKeyNoAction - do not allow delete of primary key if it has been imported
                 * importedKeyCascade - delete rows that import a deleted key
                 * importedKeySetNull - change imported key to NULL if its primary key has been deleted
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility)
                 * importedKeySetDefault - change imported key to default if its primary key has been deleted
                 */
                short delRule = rs.getShort("DELETE_RULE");
                String fkName = rs.getString("FK_NAME");//外键的名称（可能为空）
                String pkName = rs.getString("PK_NAME");//主键的名称（可能为空）

                /**
                 * can the evaluation of foreign key constraints be deferred until commit
                 * importedKeyInitiallyDeferred - see SQL92 for definition
                 * importedKeyInitiallyImmediate - see SQL92 for definition
                 * importedKeyNotDeferrable - see SQL92 for definition
                 */
                short deferRability = rs.getShort("DEFERRABILITY");

                System.out.println(pkTableCat + "-" + pkTableSchem + "-" + pkTableName + "-" + pkColumnName + "-"
                        + fkTableCat + "-" + fkTableSchem + "-" + fkTableName + "-" + fkColumnName + "-" + keySeq + "-"
                        + updateRule + "-" + delRule + "-" + fkName + "-" + pkName + "-" + deferRability);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		JdbcUtil ju = new JdbcUtil();
		ResourceBundle rb = java.util.ResourceBundle.getBundle("properties.sqlserver.jdbc");
		try {
			Connection connection = ju.connect(rb.getString("jdbc.driverClassName"), rb.getString("jdbc.url"), rb.getString("jdbc.username"), rb.getString("jdbc.password"));
//			ju.getALLTableList(connection, null);
//			ju.getAllViewList(connection, null);
//			ju.getAllSchemas(connection);
//			ju.getTableColumns(connection, null, "zcl_organizations");
//			ju.getIndexInfo(connection, null, "zcl_users");
//			ju.getAllPrimaryKeys(connection, null, "zcl_users");
			ju.getAllExportedKeys(connection, null, "zcl_organizations");
			ju.getAllExportedKeys(connection, null, "zcl_users");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
