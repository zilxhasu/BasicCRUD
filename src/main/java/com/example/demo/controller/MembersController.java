package com.example.demo.controller;

import com.example.demo.model.Members;
import com.example.demo.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MembersController {

    @Autowired
    private MembersService membersService;

    // 會員列表頁面
    @GetMapping
    public String getAllMembers(Model model) {
        model.addAttribute("members", membersService.getAllMembers());
        return "members";
    }

    // 新增會員頁面
    @GetMapping("/new")
    public String createMemberForm(Model model) {
        model.addAttribute("member", new Members());
        return "new-member";
    }

    // 新增會員
    @PostMapping
    public String createMember(@ModelAttribute Members member) {
        membersService.createMember(member);
        return "redirect:/members";
    }

    // 會員詳情頁面
    @GetMapping("/{id}")
    public String viewMember(@PathVariable Long id, Model model) {
        Members member = membersService.getMemberById(id);
        if (member == null) {
            return "redirect:/members?error=notfound";
        }
        model.addAttribute("member", member);
        return "view-member";
    }

    // 編輯會員頁面 - 改為GET方法
    @GetMapping("/edit/{id}")
    public String editMemberForm(@PathVariable Long id, Model model) {
        Members member = membersService.getMemberById(id);
        if (member == null) {
            return "redirect:/members?error=notfound";
        }
        model.addAttribute("member", member);
        return "edit-member";
    }

    // 更新會員 - 新增此方法
    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute Members member) {
        member.setId(id);
        membersService.updateMember(id, member);
        return "redirect:/members";
    }

    // 刪除會員 - 可以保持POST，但需要修改HTML文件
    @PostMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        membersService.deleteMember(id);
        return "redirect:/members";
    }
}