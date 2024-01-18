package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {
	

	@Override
    public List<Member> importMembers(File inputFile)  {
        List<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line = br.readLine();
            while (line  != null) {
                Member member = parseMember(line);
                members.add(member);
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }
        return members;
    }

    private Member parseMember(String line) {
        Member member = new Member();
        member.setId(line.substring(0, 12).trim());
        member.setLastName(line.substring(12, 37).trim());
        member.setFirstName(line.substring(37, 62).trim());
        member.setAddress(line.substring(62, 92).trim());
        member.setCity(line.substring(92, 112).trim());
        member.setZip(line.substring(112).trim());
        return member;
	
	}
	


}
