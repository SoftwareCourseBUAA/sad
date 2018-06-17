package com.expert.demo.Controller;

import com.expert.demo.Entity.Expert;
import com.expert.demo.Entity.ExpertAndPaper;
import com.expert.demo.Entity.Paper;
import com.expert.demo.Repository.ExpertAndPaperRepository;
import com.expert.demo.Repository.ExpertRepository;
import com.expert.demo.Repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.expert.demo.Controller.PaperController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class PaperAndExpertController {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private ExpertAndPaperRepository expertAndPaperRepository;

    @GetMapping(value="/expert-paper/{expertid}")
    public List<Paper> getPapersByExpertId(@PathVariable("expertid") int expertid)
    {
        Expert expert= expertRepository.getByExpertId(expertid);
        if(expert==null)
            return null;
        else{
            List<ExpertAndPaper> sumlist = expertAndPaperRepository.findAllByExpert(expert);
            if(sumlist==null)
                return null;
            List<Paper> result = new ArrayList<Paper>();
            for(ExpertAndPaper e :sumlist)
            {
                result.add(e.getPaper());
            }
            return result;
        }
    }

    @GetMapping(value="/papercount/{expertid}")
    public int getPaperCount(@PathVariable("expertid") int eid)
    {
        Expert e = expertRepository.getByExpertId(eid);
        if(e==null) return 0;
        else{
            int re = expertAndPaperRepository.getPaperCountByExpert(e);
            return re;
        }
    }

    @PostMapping(value="/expert-paper/addone/{expertid}")
    public boolean addExpertAndPaper(@PathVariable("expertid") int expertid,@RequestBody Paper paper)
    {
        if(paper==null||paper.getTitle()==null)
            return false;
        else{
            Paper p = new Paper();
            p.setTitle(paper.getTitle());
            if(paper.getUrl()!=null)
                p.setUrl(paper.getUrl());
            paperRepository.save(p);
            ExpertAndPaper ep = new ExpertAndPaper();
            ep.setExpert(expertRepository.getByExpertId(expertid));
            ep.setPaper(p);
            expertAndPaperRepository.save(ep);
            return true;
        }
    }

    @PutMapping(value="/expert-paper/{EPId}")
    public boolean changeEP(@PathVariable("EPId") int EPId,@RequestBody Paper paper)
    {
        ExpertAndPaper EP = expertAndPaperRepository.findByExpertAndPaperId(EPId);
        if(EP==null||paper==null||paper.getTitle()==null)
            return false;
        else{
            Paper o_paper = EP.getPaper();
            o_paper.setTitle(paper.getTitle());
            if(paper.getUrl()!=null)
                o_paper.setUrl(paper.getUrl());
            paperRepository.save(o_paper);
            return true;
        }
    }

    @DeleteMapping(value="/expert-paper/{EPId}")
    public boolean deleteEP(@PathVariable("EPId") int epid)
    {
        ExpertAndPaper EP = expertAndPaperRepository.findByExpertAndPaperId(epid);
        if(EP==null)
            return false;
        else{
            Paper p =EP.getPaper();
            expertAndPaperRepository.delete(EP);
            paperRepository.delete(p);
            return true;
        }
    }
}
