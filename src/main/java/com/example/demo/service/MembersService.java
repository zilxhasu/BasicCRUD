package com.example.demo.service;

import com.example.demo.model.Members;
import com.example.demo.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembersService {

    @Autowired
    private MembersRepository membersRepository;

    public List<Members> getAllMembers() {
        return membersRepository.findAll();
    }

    public Members getMemberById(Long id) {
        return membersRepository.findById(id).orElse(null);

    }
    public Members createMember(Members member) {
        return membersRepository.save(member);
    }
    public Members updateMember(Long id, Members updatedMember) {
        return membersRepository.findById(id).map(existingMember -> {
            existingMember.setName(updatedMember.getName());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setPhone(updatedMember.getPhone());
            existingMember.setAddress(updatedMember.getAddress());
            return membersRepository.save(existingMember);
        }).orElse(null);
    }

    public boolean deleteMember(Long id) {
        if (membersRepository.existsById(id)) {
            membersRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
