package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main extends MemberFileConverter {

    @Override
    protected MemberExporter getMemberExporter() {
        return new MemberExporterImpl();
    }

    @Override
    protected MemberImporter getMemberImporter() {
        return new MemberImporterImpl();
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
        Set<String> uniqueIds = new HashSet<>();
        List<Member> distinctMembers = new ArrayList<>();
        for (Member member : membersFromFile) {
            if (uniqueIds.add(member.getId())) {
                distinctMembers.add(member);
            }
        }
        return distinctMembers;
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
        Map<String, List<Member>> membersByState = new HashMap<>();
        for (Member member : validMembers) {
            membersByState.computeIfAbsent(member.getState(), k -> new ArrayList<>()).add(member);
        }
        return membersByState;
    }

    public static void main(String[] args) throws Exception {
        File inputFile = new File("Members.txt");

        Main main = new Main();

        MemberImporter memberImporter = main.getMemberImporter();
        List<Member> members = memberImporter.importMembers(inputFile);

        // Remove duplicates
        List<Member> nonDuplicateMembers = main.getNonDuplicateMembers(members);

        // Split members by state
        Map<String, List<Member>> membersByState = main.splitMembersByState(nonDuplicateMembers);

        MemberExporter memberExporter = main.getMemberExporter();
        for (Map.Entry<String, List<Member>> entry : membersByState.entrySet()) {
            String state = entry.getKey();
            String outputFile = state + "_outputFile.csv";
            List<Member> stateMembers = entry.getValue();

            memberExporter.exportMembers(stateMembers, outputFile);
        }
    }
}
