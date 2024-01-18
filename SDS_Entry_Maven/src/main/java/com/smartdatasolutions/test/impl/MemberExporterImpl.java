package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;

import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;


public class MemberExporterImpl implements MemberExporter {

	@Override
    public void writeMember(Member member, Writer writer) throws IOException {
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.write(member.toCSVString());
        printWriter.flush();
    }

	@Override
    public void exportMembers(List<Member> members, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Member member : members) {
                writer.println(member.getId() + "," + member.getFirstName() + "," + member.getLastName() + "," +
                        member.getAddress() + "," + member.getCity() + "," + member.getZip());
            }
        }
    }

}
