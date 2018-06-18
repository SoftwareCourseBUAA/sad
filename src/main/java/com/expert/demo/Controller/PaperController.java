package com.expert.demo.Controller;

import com.expert.demo.Entity.Paper;
import com.expert.demo.Repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PaperController {
    @Autowired
    private PaperRepository paperRepository;

    @GetMapping(value="/paper/all")
    public List<Paper> getAllPapers()
    {
        return paperRepository.findAll();
    }

    @GetMapping(value="/paper/all/count")
    public  int getPaperCount(){return paperRepository.getPaperCount();}

    @DeleteMapping(value="/paper/{paperid}")
    public boolean deletePaperById(@PathVariable("paperid") int paperid)
    {
        Paper paper = paperRepository.getByPaperId(paperid);
        if(paper!=null)
        {
            paperRepository.delete(paper);
            return true;
        }
        return false;
    }

    @PostMapping(value="/paper")
    public boolean addPaper(@RequestBody Paper paper)
    {
        if(paper.getTitle()!=null)
        {
            paperRepository.save(paper);
            return true;
        }
        return false;
    }

    @PutMapping(value="/paper/{paperid}")
    public boolean changePaper(@PathVariable("paperid") int paperid,@RequestBody Paper new_paper)
    {
        Paper paper = paperRepository.getByPaperId(paperid);
        if(paper==null)
            return false;
        else{
            if(new_paper==null&&new_paper.getTitle()==null)
                return false;
            else{
                paper.setTitle(new_paper.getTitle());
                if(new_paper.getUrl()!=null)
                    paper.setUrl(new_paper.getUrl());
                paperRepository.save(paper);
                return true;
            }
        }
    }


}
