package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.bean.MedicalRecord;
import com.model.service.MedicalRecordService;

@Controller
public class MedicalRecordController {
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@RequestMapping("regMedicalRecord/{pid}")
	@ResponseBody
	public String regMedicalRecord(@PathVariable int pid,MedicalRecord mr,HttpSession session){
		mr.setPid(pid);
		medicalRecordService.saveMedicalRecord(mr, session);
		return "{\"result\":\"看诊成功\"}";
	}
	
}
