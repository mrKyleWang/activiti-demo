package top.kylewang;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Activiti工作流框架Test
 * @author Kyle.Wang
 * 2018/2/24 0024 12:22
 */
public class ActivitiTest {


    /**
     * 取得流程引擎, 自动创建Activiti设计的数据库和表
     */
    @Test
    public void CreateProcessEngine(){

        /*
        1.通过代码形式创建
            - 取得ProcessEngineConfiguration对象
            - 设置数据库连接属性
            - 设置创建表的策略(自动创建表)'
            - 通过ProcessEngineConfiguration对象创建ProcessEngine对象
         */

        // 取得ProcessEngineConfiguration对象
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        // 设置数据库连接属性
        engineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        engineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti-demo?useSSL=false&characterEncoding=utf-8");
        engineConfiguration.setJdbcUsername("wking");
        engineConfiguration.setJdbcPassword("king");
        // 设置创建表的策略(当没有表时,自动创建表)
        engineConfiguration.setDatabaseSchemaUpdate("true");

        // 通过ProcessEngineConfiguration对象创建ProcessEngine对象
        ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
        System.out.println("流程引擎创建成功");

    }

}
