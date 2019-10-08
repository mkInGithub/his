package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.bean.CheckItemRecordList;
import com.model.service.CheckItemRecordService;

@Controller
public class CheckItemRecordController {
	@Autowired
	private CheckItemRecordService  checkItemRecordService;
	
	@RequestMapping("saveCheckItemRecord/{pid}")
	@ResponseBody
	public  String saveCheckItemRecord(@PathVariable int pid,CheckItemRecordList cirList){
		checkItemRecordService.saveCheckItemRecord(pid, cirList);
		return "{\"result\":\"提交检查申请成功\"}";
	}
	
	
}
