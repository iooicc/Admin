package my.admin.model;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

public class ModelGen {
	public static void main(String[] args) throws Exception {
		ConnectionSource source = ConnectionSourceHelper.getSimple(
			"com.mysql.jdbc.Driver",
			"jdbc:mysql://47.93.45.36:3306/db_model_matching?useUnicode=true&characterEncoding=utf-8",
			"root",
			"Admin_!qaz"
		);
		DBStyle mysql = new MySqlStyle();
		// sql语句放在classpagth的/sql 目录下
		SQLLoader loader = new ClasspathLoader("/sql");
		// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
		UnderlinedNameConversion nc = new UnderlinedNameConversion();
//		DefaultNameConversion nc = new DefaultNameConversion();
		// 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
		SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[] { new DebugInterceptor() });
		
		GenConfig config = new GenConfig();
		config.setBaseClass("my.admin.core.model.BaseModel");
		sqlManager.genPojoCode("sys_file", "my.admin.model", config);
		
		System.out.println("done");
	}
}
