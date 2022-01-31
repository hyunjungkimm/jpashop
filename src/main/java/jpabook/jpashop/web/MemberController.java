package jpabook.jpashop.web;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.MemberDto;
import jpabook.jpashop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String home(){
        return "/home";
    }

    @RequestMapping(value = "/members/new", method= RequestMethod.GET)
    public String createForm(){
        return "members/createMemberForm";
    }

    @RequestMapping(value = "/members/new", method= RequestMethod.POST)
    public String create(MemberDto memberDto){
        System.out.println(memberDto.getCity());
        memberService.join(memberDto);
        return "redirect:/";
    }
    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


}
