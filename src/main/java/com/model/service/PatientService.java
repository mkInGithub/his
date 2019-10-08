package com.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.bean.Patient;
import com.model.bean.User;
import com.model.mapper.PatientMapper;

@Service
public class PatientService {
	@Autowired
	private PatientMapper patientMapper;
	
	public int getPatientNo(){
		Patient p = patientMapper.getPatientNo();
		return p.getPid();
	}
	
	public List<Patient>  getPatientData(int pagesize,int pagenum){
		//构建查询条件
		Map<String, Object> map = new HashMap<>();
		map.put("k_pagesize", pagesize);
		map.put("k_beginIndex", (pagenum-1)*pagesize);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, - 7);
		Date d = c.getTime();
		String day = format.format(d);
		Date selectDate = null;
		try {
			selectDate = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sDate = new java.sql.Date(selectDate.getTime());
		map.put("k_date", sDate);
		
		return patientMapper.getPatientData(map);
	}
	
	public void savePatient(Patient p,HttpSession session){
		//setData
		p.setBirthday(p.getYear()+"-"+p.getMonth());
		p.setPstatus("未看诊");
		p.setStatus(1);
		p.setOperator(((User)session.getAttribute("loginUser")).getUserid());
		p.setOperateDate(new java.sql.Date(new Date().getTime()));
		
		patientMapper.savePatient(p);
	}
	
	public Patient  getPatientByNo(int pid){
		return patientMapper.getPatientByNo(pid);
	}
	
	public void updatePatientStatus(int pid,String pstatus,HttpSession session){
		Map<String, Object> map = new HashMap<>();
		map.put("k_pid", pid);
		map.put("k_pstatus", pstatus);
		map.put("k_operator", ((User)session.getAttribute("loginUser")).getUserid());
		map.put("k_operatedate", new java.sql.Date(new Date().getTime()));
		patientMapper.updatePatientStatus(map);
	}
	
	public List<Patient>  getPatientDataByDoctor(String dname){
		Map<String, Object> map = new HashMap<>();
		map.put("k_dname", dname);
		map.put("k_createdate", new java.sql.Date(new Date().getTime()));
		return patientMapper.getPatientDataByDoctor(map);
	}
	
	
	
}
















