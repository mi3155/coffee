package com.example.demo.controller;


import com.example.demo.Entity.Notice;
import com.example.demo.dto.PageDTO;
import com.example.demo.dto.noticeDTO;
import com.example.demo.service.NoticeServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Log4j2
public class noticeController {

    @Autowired
    final private NoticeServiceImp NoticeService;

    @GetMapping("/notice")
    public String test1(PageDTO dto, Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("login", userDetails);

        int nowPage =1;

        if(dto.getNowPage()!=0)
            nowPage=dto.getNowPage();

        //게시물 가져오기
        Page<Notice> list = NoticeService.getBoardList(nowPage-1,10);

        int pagePerBlock=15;                        //한페이지당 표시할 블럭수
        Long totalRecord=list.getTotalElements();   //전체 레코드 수
        int totalPage = list.getTotalPages();       //전체 페이지 수
        int nowBlock = (int)Math.ceil((double)nowPage/pagePerBlock);//현재블럭 번호
        int totalBlock=(int)Math.ceil((double)totalPage/pagePerBlock);//전체블럭 개수

        //블럭에 표시할 StartNum,EndNum 계산
        int pageStart=(nowBlock -1)*pagePerBlock+1;
        int pageEnd=((pageStart+pagePerBlock)<=totalPage)?(pageStart+pagePerBlock):totalPage+1;

        //Model에 연결
        model.addAttribute("list",list);
        model.addAttribute("PS",pageStart);
        model.addAttribute("PE",pageEnd);
        model.addAttribute("pagePerBlock",pagePerBlock);
        model.addAttribute("nowBlock",nowBlock);
        model.addAttribute("totalBlock",totalBlock);
        model.addAttribute("nowPage",nowPage);


        return "notice/notice.html";
    }



    @GetMapping("/write")
    public String write(Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("login", userDetails);

        return "notice/write.html";
    }

    @PostMapping("/writeproc")
    public String PostProcPage(noticeDTO dto,Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        dto.setId(userDetails.getUsername());
        NoticeService.postfunc(dto);

        return "redirect:/notice";
    }

    @GetMapping("/read")
    public String read(HttpServletRequest req,Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("login", userDetails);

        System.out.println("NUM : " + req.getParameter("num"));


        int nowPage = Integer.parseInt(req.getParameter("nowPage"));

        //세션추출
        HttpSession session = req.getSession();
        //현재 읽고있는 게시물 정보 받기
        Long num =  Long.parseLong(req.getParameter("num"));

        //카운트 증가
        NoticeService.Upcount(num);

        //게시물 받아오기
        Notice notice = NoticeService.getBoard(num);

        //세션에 읽고있는 게시물 넣기
        session.setAttribute("notice", notice);
        session.setAttribute("nowPage",nowPage);

        //모델에 추가(페이지로 전달)
        model.addAttribute("notice", notice);
        model.addAttribute("nowPage",nowPage);

        return "notice/read.html";
    }

    @PostMapping("/update")
    public String update(noticeDTO dto, Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("login", userDetails);

        model.addAttribute("dto",dto);
        return "notice/update.html";
    }

    @PostMapping("/updateProc")
    public String updateProc(noticeDTO dto,HttpServletRequest req){
        HttpSession session = req.getSession();

        Notice board = (Notice)session.getAttribute("notice");
        int nowPage = (Integer)session.getAttribute("nowPage");

        if(dto.getPassword().equals(board.getPassword())){
            //dto에 값 추가
            dto.setId(board.getId());
            dto.setNum(board.getNum());
            //서비스 수정함수 사용
            NoticeService.Updateboard(dto);;
            //list.do 로 이동 (읽고 있는 페이지로 이동)
            return "redirect:/notice?nowPage="+nowPage;
        } else{
            //read로 이동(읽고 있는 게시물로)
            return "redirect:/read?num=" + board.getNum()+"&nowPage="+nowPage;

        }

    }

    @GetMapping("/delete")
    public String deletepage(Authentication authentication,Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //System.out.println("안녕하세요 " + userDetails.getUsername());

        model.addAttribute("login", userDetails);

        return "notice/delete.html";
    }

    @PostMapping("/deleteProc")
    public String deleteProc(noticeDTO dto, HttpServletRequest req){
        log.info("Delete 예정인 정보 : " + dto.toString());

        HttpSession session = req.getSession();
        Notice notice = (Notice)session.getAttribute("notice");
        int nowPage = (Integer)session.getAttribute("nowPage");
        log.info("READ중인 정보 : " + notice.toString());
        //패스워드 일치 여부
        if(dto.getPassword().equals(notice.getPassword())){

            //서비스 삭제함수 사용
            NoticeService.deleteboard(notice.getNum());

            //list.do 로 이동 (읽고 있는 페이지로 이동)
            return "redirect:/notice?nowPage="+nowPage;
        } else{
            //read.do로 이동(읽고 있는 게시물로)
            return "redirect:/read?num=" + notice.getNum()+"&nowPage="+nowPage;

        }
    }
}
