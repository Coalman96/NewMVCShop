package mvc.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class UserEntity extends BaseEntity{
	
	///Field
	@Id
	@Column(length = 20)
	private String userId;

	@Column(length = 50)
	private String userName;

	@Column(length = 10, updatable = false)
	private String password;

	@Builder.Default
	@Column(length = 5, updatable = false)
	private String role = "user";

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

}