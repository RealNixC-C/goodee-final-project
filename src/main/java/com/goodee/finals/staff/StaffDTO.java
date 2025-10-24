package com.goodee.finals.staff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goodee.finals.alert.AlertDTO;
import com.goodee.finals.approval.ApprovalDTO;
import com.goodee.finals.approval.ApproverDTO;
import com.goodee.finals.attend.AttendDTO;
import com.goodee.finals.calendar.CalendarDTO;
import com.goodee.finals.common.attachment.StaffAttachmentDTO;
import com.goodee.finals.common.attachment.StaffSignDTO;
import com.goodee.finals.common.validation.MyPageValid;
import com.goodee.finals.common.validation.StaffValid;
import com.goodee.finals.drive.DocumentDTO;
import com.goodee.finals.drive.DriveDTO;
import com.goodee.finals.drive.DriveShareDTO;
import com.goodee.finals.inspection.InspectionDTO;
import com.goodee.finals.fault.FaultDTO;
import com.goodee.finals.lost.LostDTO;
import com.goodee.finals.messenger.ChatUserDTO;
import com.goodee.finals.notice.NoticeDTO;
import com.goodee.finals.product.ProductDTO;
import com.goodee.finals.productManage.ProductManageDTO;
import com.goodee.finals.ride.RideDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "staffCode")
@Entity
@Table(name = "staff")
public class StaffDTO implements UserDetails {
	@Id
	private Integer staffCode;
	@ManyToOne
	@JoinColumn(name = "deptCode")
	private DeptDTO deptDTO;
	@ManyToOne
	@JoinColumn(name = "jobCode")
	private JobDTO jobDTO;
	private String staffPw;
	
	@NotBlank(message = "이름을 입력해주세요.", groups = StaffValid.class)
	private String staffName;
	@NotNull(message = "성별을 선택해주세요.", groups = StaffValid.class)
	private Integer staffGender;
	@Email(message = "이메일을 정확하게 입력해주세요.", groups = {StaffValid.class, MyPageValid.class})
	private String staffEmail;
	@Pattern(regexp = "^$|^010-([0-9]{4,})-([0-9]{4,})$", message = "휴대폰 번호를 정확하게 입력해주세요.", groups = {StaffValid.class, MyPageValid.class})
	private String staffPhone;
	private Integer staffPostcode;
	private String staffAddress;
	private String staffAddressDetail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "오늘 이전의 날짜만 입력 가능합니다.", groups = StaffValid.class)
	private LocalDate staffHireDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate staffFireDate;
	
	private Integer staffUsedLeave;
	private Integer staffRemainLeave;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<NoticeDTO> notices;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ChatUserDTO> chatUserDTOs;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	private StaffAttachmentDTO staffAttachmentDTO;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	private StaffSignDTO staffSignDTO;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ApprovalDTO> approvalDTOs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ApproverDTO> approverDTOs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LostDTO> lostDTOs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductDTO> productDTOs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductManageDTO> productManageDTOs;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "staffDTO", cascade = CascadeType.ALL) @JsonIgnore
	private List<AlertDTO> alertDTOs;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<RideDTO> rideDTOs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<InspectionDTO> inspectionDTOs;
	
	@OneToMany(mappedBy = "staffDTO", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DriveDTO> driveDTOs;
	
	@OneToMany(mappedBy = "staffDTO", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DriveShareDTO> driveShareDTOs;
	
	@OneToMany(mappedBy = "staffDTO", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DocumentDTO> documentDTOs;
	
	@OneToMany(mappedBy = "staffDTO", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<CalendarDTO> calendarDTOs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<AttendDTO> attendDTOs;
	
	@Column(insertable = false)
	@ColumnDefault("1")
	private Boolean staffLocked;
	@Column(insertable = false)
	@ColumnDefault("1")
	private Boolean staffEnabled;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "staffDTO", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FaultDTO> faultDTOs;
	
	// For Input
	@Transient
	private Integer inputDeptCode;
	@Transient
	private Integer inputJobCode;
	@Transient
	private String todayState;
	
	@Override
	public String getUsername() {
		return String.valueOf(staffCode);
	}
	
	@Override
	public String getPassword() {
		return staffPw;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantList = new ArrayList<>();
		grantList.add(new SimpleGrantedAuthority(deptDTO.getDeptName()));
		grantList.add(new SimpleGrantedAuthority(jobDTO.getJobName()));
		
		return grantList;
	}

	@Override
	public boolean isAccountNonLocked() {
		return staffLocked;
	}

	@Override
	public boolean isEnabled() {
		return staffEnabled;
	}
	
}