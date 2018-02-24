package top.kylewang;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * Activiti工作流框架创建流程引擎Test
 * @author Kyle.Wang
 * 2018/2/24 0024 12:22
 */
public class CreateProcessEngineTest {


    /*
     * 获取ProcessEngine对象:
     *  - 取得ProcessEngineConfiguration对象
     *  - 设置数据库连接属性
     *  - 设置创建表的策略(自动创建表)
     *  - 通过ProcessEngineConfiguration对象创建ProcessEngine对象
     */

    /**
     * A.通过代码形式创建流程引擎,自动创建数据库和表
     */
    @Test
    public void CreateProcessEngineTest1(){

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


    /**
     * B.通过加载自定义配置文件获取流程引擎并自动创建数据库和表
     */
    @Test
    public void CreateProcessEngineTest2(){
        // 从类加载路径中查找资源,取得ProcessEngineConfiguration对象
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件activiti.cfg.xml创建流程引擎成功");
    }

    /**
     * C.通过ProcessEngines获取默认流程引擎并自动创建数据库和表
     */
    @Test
    public void CreateProcessEngineTest3(){
        // 默认从类加载路径中查找配置文件activiti.cfg.xml,取得ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("通过ProcessEngines创建流程引擎成功");
    }




}
