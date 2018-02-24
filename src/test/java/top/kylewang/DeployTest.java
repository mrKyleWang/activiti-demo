package top.kylewang;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * Activiti工作流框架部署流程Test
 * @author Kyle.Wang
 * 2018/2/24 0024 14:47
 */
public class DeployTest {


    /**
     * 部署流程定义
     */
    @Test
    public void deployTest(){
        // 取得流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取RepositoryService 仓库服务:管理流程定义
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 部署
        Deployment deploy = repositoryService.createDeployment()    //创建一个部署
                .addClasspathResource("processes/leave.bpmn")   //从类路径中添加资源,一次只能添加一个资源
                .addClasspathResource("processes/leave.png")    //从类路径中添加资源
                .name("请假流程")   //设置部署的名称
                .category("办公")   //设置部署的类别
                .deploy();//部署
        System.out.println("部署的id: "+deploy.getId());
        System.out.println("部署的name: "+deploy.getName());

    }



}
