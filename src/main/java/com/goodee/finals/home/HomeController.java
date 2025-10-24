package com.goodee.finals.home;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.finals.approval.ApprovalListDTO;
import com.goodee.finals.approval.ApprovalService;
import com.goodee.finals.attend.AttendDTO;
import com.goodee.finals.attend.AttendService;
import com.goodee.finals.notice.NoticeDTO;
import com.goodee.finals.ride.RideDTO;
import com.goodee.finals.staff.StaffDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private AttendService attendService;
	@Autowired
	private HomeService homeService;
	@Autowired
	private ApprovalService approvalService;
	
	
	@GetMapping("/")
	public String getHome(Model model, Pageable pageable) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		StaffDTO staffDTO = null;
		if (auth.getPrincipal() instanceof StaffDTO) {
			staffDTO = (StaffDTO) auth.getPrincipal();			
		}

        // 로그인 안 된 경우, 대시보드 접근
        if (auth == null || auth.getName().equals("anonymousUser")) {
            return "redirect:/staff/login";
        }
        
        // 출퇴근 - 오늘날짜
        String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.addAttribute("todayDate", todayDate);

        // 출퇴근 - 기록확인 및 출력
		AttendDTO attendDTO = attendService.findAttend();
		model.addAttribute("attendDTO", attendDTO);
		
		// 공지사항 - 목록 5개 출력
		List<NoticeDTO> noticeList = homeService.getRecentNoticesForDashboard();
		model.addAttribute("noticeList", noticeList);
		
		// 결재현황
		Page<ApprovalListDTO> result = approvalService.getApprovalRequestList(staffDTO.getStaffCode(), "", pageable);
		List<ApprovalListDTO> approvalList = new ArrayList<>();
		
		// 어트랙션 운용현황
		for (int i = 0; i < Math.min(5, result.getContent().size()); i++) {
			approvalList.add(result.getContent().get(i));
		}
		
		model.addAttribute("approvalList", approvalList);
		
		// 어트랙션 운휴 현황
		List<RideDTO> rides = homeService.homeList();
		model.addAttribute("rides", rides);
		
		// 날씨
		
		return "index";
	}
	
}
