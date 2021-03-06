package com.khh.web;

import com.khh.domain.Board;
import com.khh.domain.Criteria;
import com.khh.domain.PageMaker;
import com.khh.domain.SearchCriteria;
import com.khh.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 14..
 */
@Controller
@RequestMapping("/bbs/*")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String root = "bbs";

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET(
            @ModelAttribute("criteria") SearchCriteria criteria, Model model) throws Exception {
        logger.info("register get .......");
        model.addAttribute("root", root);

        return "/bbs/register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(Board board, RedirectAttributes rttr) throws Exception {
        logger.info("register post .......");
        logger.info(board.toString());

        boardService.register(board);

        return "redirect:/bbs/list";
    }

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listGET(@ModelAttribute("criteria") SearchCriteria cri, Model model) throws Exception {
        logger.info("show all list .......");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(cri);
        pageMaker.setTotalCount(boardService.count(cri));

        model.addAttribute("list", boardService.list(cri));
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("root", root);

        return "/bbs/list";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String viewGET(@RequestParam("no") int no,
                          @ModelAttribute("criteria") SearchCriteria criteria,
                          Model model) throws Exception {
        logger.info("view no." + no + "........");
        //TODO: view count 증가기능 추가

        model.addAttribute("board", boardService.read(no));
        model.addAttribute("root", root);

        return "/bbs/view";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String removePOST(@RequestParam("no") int no,
                             SearchCriteria criteria,
                             RedirectAttributes rttr) throws Exception {
        logger.info("remove no." + no + " ........");

        boardService.delete(no);

        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());

        return "redirect:/bbs/list";
    }

    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("no") int no,
                            @ModelAttribute("criteria") SearchCriteria criteria,
                            Model model) throws Exception{
        logger.info("modify no." + no + " ........");

        model.addAttribute("board", boardService.read(no));
        model.addAttribute("root", root);

        return "/bbs/modify";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String modifyPOST(Board board,
                             @ModelAttribute("criteria") SearchCriteria criteria,
                             RedirectAttributes rttr) throws Exception {
        logger.info("modify board ........");
        logger.info(board.toString());

        boardService.update(board);

        rttr.addAttribute("page", criteria.getPage());
        rttr.addAttribute("perPageNum", criteria.getPerPageNum());
        rttr.addAttribute("searchType", criteria.getSearchType());
        rttr.addAttribute("keyword", criteria.getKeyword());

        return "redirect:/bbs/list";
    }

    @ResponseBody
    @RequestMapping(value = "/getAttach/{bno}", method = RequestMethod.GET)
    public List<String> getAttach(@PathVariable("bno") int bno) throws Exception {

        return boardService.getAttach(bno);
    }
}
