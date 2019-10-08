package com.model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.bean.MedicalRecord;
import com.model.bean.User;
import com.model.mapper.MedicalRecordMapper;
import com.model.mapper.PatientMapper;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordMapper medicalRecordMapper;
	@Autowired
	private PatientMapper patientMapper;
	
	public void saveMedicalRecord(MedicalRecord mr,HttpSession session){
		mr.setStatus(1);
		mr.setOperator(((User)session.getAttribute("loginUser")).getUserid());
		mr.setOperatedate(new java.sql.Date(new Date().getTime()));
		//保存病例
		medicalRecordMapper.saveMedicalRecord(mr);
		//更新患者状态
		Map<String, Object> map = new HashMap<>();
		map.put("k_pid", mr.getPid());
		map.put("k_pstatus", "已看诊");
		map.put("k_operator", ((User)session.getAttribute("loginUser")).getUserid());
		map.put("k_operatedate", new java.sql.Date(new Date().getTime()));
		patientMapper.updatePatientStatus(map);
		
	}
	
}
