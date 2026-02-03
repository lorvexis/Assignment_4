package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController {

    private final Library library = new Library();

    @GetMapping
    public List<LibraryMember> getMembers() {
        return library.getAllMembers();
    }

    @PostMapping
    public String addMember(@RequestBody LibraryMember member) {
        library.AddMemberToDatabase(member);
        return "Member added";
    }
    @DeleteMapping("/{memberId}")
    public String deleteMember(@PathVariable int memberId) {
        library.deleteMember(memberId);
        return "Member deleted";
    }
}
