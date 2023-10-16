package mvc.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequestDTO {
	//JPA에서 사용하는 Pageable 객체 생성이 목적
	///Field
	@Value("${pageSize}")
	private int pageSize;

	@Value("${pageUnit}")
	private int pageUnit;

	//검색조건 추가
	private String searchType,searchKeyword;
	public Pageable getPageable(Sort sort){

		return PageRequest.of(pageUnit -1, pageSize, sort);

	}

}