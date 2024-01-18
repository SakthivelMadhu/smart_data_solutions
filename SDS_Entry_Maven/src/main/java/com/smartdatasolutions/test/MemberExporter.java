package com.smartdatasolutions.test;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface MemberExporter {

	public void writeMember( Member member, Writer writer ) throws IOException;

	public void exportMembers(List<Member> members, String filePath) throws IOException;

}
