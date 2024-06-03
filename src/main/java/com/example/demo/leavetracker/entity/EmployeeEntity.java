package com.example.demo.leavetracker.entity;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "EmployeeTable")
public class EmployeeEntity implements UserDetails{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long employeeId;

    private String employeeName;
    
    private String password;
    
    private String email;
    
     private String role;

    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private EmployeeEntity manager;
    

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<AttendanceEntity> attendance;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<LeaveEntity> leaves ;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + role));

	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
    
    
}