package mvc.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class UserEntity extends BaseEntity implements UserDetails {

	///Field
	@Id
	@Column(length = 20)
	private String userId;

	@Column(length = 50)
	private String userName;

//	@Column(length = 10, updatable = false)
	private String password;

	@Enumerated(EnumType.STRING)
//	@Column(updatable = false)
	private Role role;

	@Column(length = 13, updatable = false)
	private String ssn;

	@Column(length = 14)
	private String phone;

	@Column(length = 100)
	private String addr;

	@Column(length = 50)
	private String email;

	private String phone1;

	private String phone2;

	private String phone3;

	private boolean active;

	//@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
	@OneToOne
	private CartEntity cart;


	public void setPhone(String phone) {
		// phone1, phone2, phone3 설정 부분 추가
		this.phone1 = phone.split("-")[0];
		this.phone2 = phone.split("-")[1];
		this.phone3 = phone.split("-")[2];

		// 전체 phone 설정 부분 추가
		this.phone = phone;
	}

	//security 적용 후 구현할 메소드
	//권한을 가져올 메소드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name())); //단순권한부여를 리스트로 반환
	}

	//이메일을 가져올 메소드(유저네임이지만 이메일만 가져올수있다.)
	@Override
	public String getUsername() {
		return email;
	}

	//만료되지않은 계정, true로해야 계정이 만료안됨
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있지않아야한다, true유지
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//자격증명이 만료되지않았음을 뜻함, true유지
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//사용가능한계정을 뜻함, true 유지
	@Override
	public boolean isEnabled() {
		return true;
	}
}