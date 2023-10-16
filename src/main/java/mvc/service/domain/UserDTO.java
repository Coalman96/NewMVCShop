package mvc.service.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String userId;
	private String userName;
	private String password;
	private String role;
	private String ssn;
	private String phone;
	private String addr;
	private String email;
	private LocalDate regDate;
	private String phone1;
	private String phone2;
	private String phone3;
	private boolean active;
	private CartDTO cart; // 장바구니 ID를 나타내는 신규 필드

}
