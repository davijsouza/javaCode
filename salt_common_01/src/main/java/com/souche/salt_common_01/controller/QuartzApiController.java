package com.souche.salt_common_01.controller;
import com.souche.salt_common_01.config.QuartzScheduler;
import com.souche.salt_common_01.service.TimeJobService;
import org.apache.ibatis.annotations.Delete;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 这里并没有采用restful风格 只是简单封装了一下api
 *
 * @author yvan
 *
 */
@RestController
public class QuartzApiController {
    @Autowired
    private QuartzScheduler quartzScheduler;
    @Autowired
    private TimeJobService timeJobService;

    @PostMapping("/lantu/salt/quartz")
    public Integer startQuartzJob(String name,String startTime) {


        try {
            quartzScheduler.startJob(name,startTime);
            return 1;
        } catch (Exception e) {
           // timeJobService.deleteJob(name);
            e.printStackTrace();
            return 0;
        }
    }

    @GetMapping("/lantu/salt/info")
    public String getQuartzJob(String name, String group) {
        String info = null;
        try {
            info = quartzScheduler.getJobInfo(name, group);
        } catch (SchedulerException e) {

            e.printStackTrace();
        }
        return info;
    }

    @PutMapping("/lantu/salt/modify")
    public void modifyQuartzJob(String name, String startTime) {

        boolean flag = true;
        try {
             quartzScheduler.modifyJob(name, "group1", startTime);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        //return flag;
    }

    @GetMapping (value = "/lantu/salt/pause")
    public void pauseQuartzJob(String name, String group) {
        try {
            quartzScheduler.pauseJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/lantu/salt/pauseAll")
    public void pauseAllQuartzJob() {
        try {
            quartzScheduler.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "/lantu/salt/delete")
    public void deleteJob(String name, String group) {
        try {
            quartzScheduler.deleteJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
