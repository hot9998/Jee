package org.member.model;

import java.util.ArrayList;

public interface SMeberDAO {
	//추가
	public void memInsert(MemberDTO dto);	
	//전체보기
	public ArrayList<MemberDTO> memList();
	//수정
	public void memUpdate(MemberDTO dto);
	//삭제
	public void memDelete(String userid);
	//상세보기
	public MemberDTO memDetail(String userid);
}
