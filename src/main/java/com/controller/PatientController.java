package com.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.bean.Patient;
import com.model.bean.User;
import com.model.service.PatientService;

@Controller
public class PatientController {
	@Autowired
	private PatientService  patientService;
	
	@RequestMapping("getPatientNo")
	@ResponseBody
	public String getPatientNo(){
		int pno = patientService.getPatientNo();
		return "{\"pno\":\""+pno+"\"}";
	}
	
	@RequestMapping("getPatientData/{pagesize}/{pagenum}")
	@ResponseBody
	public List<Patient>  getPatientData(@PathVariable int pagesize,@PathVariable int pagenum){
		return patientService.getPatientData(pagesize, pagenum);
	}
	
	@RequestMapping("savePatient")
	@ResponseBody
	public String savePatient(Patient p,HttpSession session){
		patientService.savePatient(p, session);
		System.out.println(p.getPname());
		return "{\"result\":\"挂号成功\"}";
	}
	
	@RequestMapping("getPatientByNo/{pid}")
	@ResponseBody
	public  Patient  getPatientByNo(@PathVariable int pid){
		Patient p = patientService.getPatientByNo(pid);
		if(p==null){
			return new Patient();
		}else{
			return p;
		}
	}
	
	@RequestMapping("refundPatient/{pid}")
	@ResponseBody
	public String refundPatient(@PathVariable int pid,HttpSession session){
		patientService.updatePatientStatus(pid, "已退号", session);
		return "{\"result\":\"退号成功\"}";
	}
	
	@RequestMapping("getPatientDataByDoctor")
	@ResponseBody
	public List<Patient> getPatientDataByDoctor(HttpSession session){
		String dname = ((User)session.getAttribute("loginUser")).getUsername();
		return patientService.getPatientDataByDoctor(dname);
	}

	@RequestMapping("selectPatientByPno/{pid}")
	@ResponseBody
	public Patient selectPatientByPno(@PathVariable int pid){
		return null;
	}
	
	
}











