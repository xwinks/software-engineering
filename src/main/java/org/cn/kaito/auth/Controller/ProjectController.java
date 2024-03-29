package org.cn.kaito.auth.Controller;

import org.cn.kaito.auth.Controller.Request.CreateProjectRequest;
import org.cn.kaito.auth.Controller.Request.EditProjectRequest;
import org.cn.kaito.auth.Controller.Response.LogResponse;
import org.cn.kaito.auth.Controller.Response.ProjectDetailResponse;
import org.cn.kaito.auth.Controller.Response.RandomTasksResponse;
import org.cn.kaito.auth.Controller.Response.SimpleProjectResponse;
import org.cn.kaito.auth.DTO.LogDTO;
import org.cn.kaito.auth.DTO.ProjectDetailDTO;
import org.cn.kaito.auth.DTO.SimpleProjectDTO;
import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.LogService;
import org.cn.kaito.auth.Service.ProjectService;
import org.cn.kaito.auth.Service.WorkExecuteService;
import org.cn.kaito.auth.Utils.LogUtil;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/project")
@PreAuthorize(value = "hasPermission('project','read')")
public class ProjectController extends BaseController{
//    @Autowired
//    LogUtil logUtil;

    @Autowired
    LogService logService;

    @Autowired
    WorkExecuteService workExecuteService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/random")
    @PreAuthorize(value = "hasPermission('project','edit')")
    public RandomTasksResponse getRandomTasks() throws CustomerException {
        return projectService.getRandomTasks();
    }

    @PostMapping("/create")
    @PreAuthorize(value = "hasPermission('project','create')")
    public void createProject(@RequestBody CreateProjectRequest createProjectRequest) throws CustomerException, IOException {
        projectService.createProject(getUid(),createProjectRequest);
    }

    @GetMapping("/list")
    public SimpleProjectResponse getSimpleProjects(@RequestParam(name = "page") int page) throws CustomerException {
        List<SimpleProjectDTO> projectDTOS =  projectService.getSimpleProjects(getUid(),page);
        SimpleProjectResponse simpleProjectResponse = new SimpleProjectResponse();
        simpleProjectResponse.setProjects(projectDTOS);
        return simpleProjectResponse;
    }

    @GetMapping("/log")
    public LogResponse getLogs(@RequestParam(name = "projectID") String projectID,
                               @RequestParam(name = "page") int page) throws CustomerException {
        List<LogDTO> logs = logService.getLogs(getUid(),projectID,page);
        LogResponse logResponse = new LogResponse();
        logResponse.setActions(logs);
        return logResponse;
    }

    @PostMapping("/editProject")
    @PreAuthorize(value = "hasPermission('project','edit')")
    public void editProject(@RequestBody EditProjectRequest editProjectRequest) throws CustomerException {
        projectService.editProject(getUid(),editProjectRequest);
    }

    @GetMapping("/stop")
    @PreAuthorize(value = "hasPermission('project','end')")
    public void stopProject(@RequestParam(name = "projectID") String projectID) throws CustomerException {
        projectService.stopProject(getUid(),projectID);
    }

    @GetMapping("/restart")
    @PreAuthorize(value = "hasPermission('project','restart')")
    public void restartProject(@RequestParam(name = "projectID") String projectID) throws CustomerException {
        projectService.restart(getUid(),projectID);
    }

    @GetMapping("/delete")
    @PreAuthorize(value = "hasPermission('project','delete')")
    public void deleteProject(@RequestParam(name = "projectID") String projectID) throws CustomerException {
        projectService.delete(getUid(),projectID);
    }

    @GetMapping("/{pid}")
    public ProjectDetailResponse projectDetail(@PathVariable(name = "pid") String pid) throws CustomerException {
        ProjectDetailDTO projectDetailDTO = projectService.getDeatil(getUid(),pid);
        ProjectDetailResponse p = new ProjectDetailResponse();
        p.setProject(projectDetailDTO);
        return p;
    }

//    @GetMapping("/read")
//    public void readProject() throws IOException {
//        System.out.println("nice");
//        logUtil.INFO("A","hello");
//        workExecuteService.save("A");
//    }
}
