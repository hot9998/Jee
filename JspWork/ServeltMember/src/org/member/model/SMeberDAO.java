package org.member.model;

import java.util.ArrayList;

public interface SMeberDAO {
	//�߰�
	public void memInsert(MemberDTO dto);	
	//��ü����
	public ArrayList<MemberDTO> memList();
	//����
	public void memUpdate(MemberDTO dto);
	//����
	public void memDelete(String userid);
	//�󼼺���
	public MemberDTO memDetail(String userid);
}
