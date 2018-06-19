package com.expert.demo.Controller;

import com.expert.demo.AssitClass.ExExpert;
import com.expert.demo.Entity.CertifyApplication;
import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.User;
import com.expert.demo.Entity.MatchApplication;
import com.expert.demo.Repository.CertifyApplicationRepository;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.MatchApplicationRepository;
import com.expert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@CrossOrigin
@RestController
public class ApplicationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private MatchApplicationRepository matchApplicationRepository;

    @Autowired
    private CertifyApplicationRepository certifyApplicationRepository;

    @GetMapping(value="/admin/apply")
    public List<MatchApplication> getAllApplication()
    {
        return matchApplicationRepository.findAll();
    }

    @GetMapping(value="/admin/count")
    public int getApplicationCount(){return matchApplicationRepository.getAppNumber();}

    @GetMapping(value="/admin/apply/add/{userId}/{expertId}")
    public MatchApplication addApplication(@PathVariable("userId") int userid, @PathVariable("expertId") int expertid)
    {
        MatchApplication tobeexpert = new MatchApplication();
        User user = userRepository.findByUserId(userid);
//        if(user==null)
//        {
//            user = new User();
//            user.setName("can't find the user");
//        }
        Expert expert= expertRepository.getByExpertId(expertid);
//        if(expert==null)
//        {
//            expert = new Expert();
//            expert.setName("can't find the expert");
//        }
        if(user!=null && expert!=null) {
            tobeexpert.setUser(user);
            tobeexpert.setExpert(expert);
            matchApplicationRepository.save(tobeexpert);
        }
        return tobeexpert;
    }

    @DeleteMapping(value="/admin/apply/{applyId}")
    public boolean rejectApplication(@PathVariable("applyId") int applyid)
    {
        MatchApplication t = matchApplicationRepository.findByApplyId(applyid);
        if(t==null)
            return false;
        matchApplicationRepository.delete(t);
        return true;
    }

    @GetMapping(value="/admin/apply/accept/{applyId}")
    public boolean acceptApplication(@PathVariable("applyId") int applyid)
    {
        MatchApplication t = matchApplicationRepository.findByApplyId(applyid);
        if(t==null)
            return false;
        User user = t.getUser();
        Expert expert = t.getExpert();
        expert.setUser(user);
        if(user==null || expert==null)
            return false;
        expertRepository.save(expert);
        matchApplicationRepository.delete(t);
        return true;
    }


    @PostMapping(value="/admin/apply/certifiedExpert/{userId}")
    public Boolean certifiedExpert(@RequestBody ExExpert certifiedExpert, @PathVariable("userId") int userId)
    {
        CertifyApplication certifyApplication=new CertifyApplication();
        if(certifiedExpert!=null)
        {
            if(certifiedExpert.getName()!="") {
                certifyApplication.setName(certifiedExpert.getName());
            }
            else
            {
                return false;
            }
            if(certifiedExpert.getInstitution()!=""){
                certifyApplication.setinstitution(certifiedExpert.getInstitution());
            }
            else
                return false;
            if(certifiedExpert.getField()!="")
                certifyApplication.setField(certifiedExpert.getField());
            else
                return false;
            certifyApplication.setPatent(certifiedExpert.getPatent());
            certifyApplication.setProject(certifiedExpert.getProject());
            certifyApplication.setOtherAchievement(certifiedExpert.getOtherAchievement());
            certifyApplication.setUser(userRepository.findByUserId(userId));
            certifyApplicationRepository.save(certifyApplication);
            return  true;
        }
        else
            return false;
    }
    @PostMapping(value="/admin/apply/certifiedExpert/upload/{userId}")
    public Boolean uploadCertifiedExpert(@RequestParam("file") MultipartFile multipartFile
            ,@PathVariable("userId") int userId)
    {

        String url=upload(multipartFile,"evidence/"+userId);
        if(url==null)
            return false;
        else
        {
           User user=userRepository.findByUserId(userId);
           if(user!=null) {
               CertifyApplication certifyApplication =certifyApplicationRepository.findByUser(user);
               certifyApplication.setDownloadUrl(url);
               certifyApplicationRepository.save(certifyApplication);
               return true;
           }
           else
               return false;
        }
    }

    @PostMapping(value="/admin/apply/matchExpert")
    public String matchExpert(@RequestParam("expertId") int expertId,@RequestParam("userId") int userId,
                              MultipartHttpServletRequest request)
    {
        List<MultipartFile> files=request.getFiles("file");
        MultipartFile file;
        BufferedOutputStream stream;
        String downloadUrl="";
        for (int i =0; i< files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String pathName="evidence/"+expertId +file.getOriginalFilename();
                    File saveFile=new File(pathName);
                    stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                    downloadUrl+=saveFile.getAbsolutePath()+";";
                } catch (Exception e) {
                    return "上传失败";
                }
            }
        }
        User user=userRepository.findByUserId(userId);
        Expert expert=expertRepository.getByExpertId(expertId);
        if(user!=null&&expert!=null)
        {
            MatchApplication matchApplication =new MatchApplication();
            matchApplication.setExpert(expert);
            matchApplication.setUser(user);
            matchApplication.setDownloadUrl(downloadUrl);
            matchApplicationRepository.save(matchApplication);
            return "上传成功";
        }
        else
            return "上传失败，不存在对应的专家或者用户";

    }

    private String upload(MultipartFile file,String path)
    {
        String downloadUrl="";
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            String pathName = path + saveFileName;
            File saveFile = new File(pathName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                downloadUrl=saveFile.getAbsolutePath();
            } catch (FileNotFoundException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }
        return downloadUrl;
    }

}
