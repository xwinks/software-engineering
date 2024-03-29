package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Dao.Entity.NoticeEntity;
import org.cn.kaito.auth.Dao.Repository.NoticeRespository;
import org.cn.kaito.auth.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeRespository noticeRespository;

    public void saveTaskNotice(String uid, String projectID,String projectName) {
        NoticeEntity noticeEntity =  createNotice(uid);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您在项目编号为"+projectID+"，项目名为"+projectName+"的项目中有等待执行的任务"+"      "
                +sformat.format(new Date()));
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveChangedNotice(String uid,String projectID,String projectName){
        NoticeEntity noticeEntity =  createNotice(uid);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("项目编号为"+projectID+"，项目名为"+projectName+"的任务被重新编辑，请注意查看。"+"      "
                +sformat.format(new Date()));
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveDelegateWorkDone(String uid, String projectID, String projectName, String delegateWorker) {
        NoticeEntity noticeEntity =  createNotice(uid);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您委托给"+delegateWorker+"的项目编号为"+projectID+"，项目名为"+projectName+"的任务已经完成。      "
                +sformat.format(new Date()));
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveEndNotice(String executor, String projectID, String projectName, String taskID) {
        NoticeEntity noticeEntity = createNotice(executor);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您所在的项目编号为"+projectID+"，项目名为"+projectName+"的项目终止。       "
                +sformat.format(new Date()));
        System.out.println(noticeEntity);
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveStopNotice(String user, String projectID, String projectName,String taskID) {
        NoticeEntity noticeEntity = createNotice(user);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您在项目编号为"+projectID+"，项目名为"+projectName+"的项目被中止。您任务编号为"+taskID+"的任务被中止。       "
                +sformat.format(new Date()));
        System.out.println(noticeEntity);
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveStopEntrustNotice(String user, String projectID, String projectName, String taskID) {
        NoticeEntity noticeEntity = createNotice(user);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您在项目编号为"+projectID+"，项目名称为"+projectName+"中被委托的任务编号为"+taskID+"的任务被中止。       "
                +sformat.format(new Date()));
        System.out.println(noticeEntity);
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveRestartNotice(String executor, String projectID, String projectName, String taskID) {
        NoticeEntity noticeEntity = createNotice(executor);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您所参与的项目编号为"+projectID+"，项目名称为"+projectName+"的任务被重新启动。       "
                +sformat.format(new Date()));
        System.out.println(noticeEntity);
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveDelegateNotice(String executor, String projectID, String projectName, String taskID) {
        NoticeEntity noticeEntity = createNotice(executor);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您被委托执行项目编号为"+projectID+"，项目名称为"+projectName+"的项目中的任务"+taskID+"       "
                +sformat.format(new Date()));
        System.out.println(noticeEntity);
        noticeRespository.save(noticeEntity);
    }

    @Override
    public void saveDelegateTakenNotice(String executor, String projectID, String projectName, String taskID) {
        NoticeEntity noticeEntity = createNotice(executor);
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式
        noticeEntity.setMessage("您被委托的项目编号为"+projectID+"，项目名称为"+projectName+"的项目中的任务"+taskID+"被收回       "
                +sformat.format(new Date()));
        System.out.println(noticeEntity);
        noticeRespository.save(noticeEntity);
    }

    private NoticeEntity createNotice(String user){
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeDate(new Date());
        noticeEntity.setRead(false);
        noticeEntity.setReceiver(user);
        return noticeEntity;
    }
}
