package mvc.service.domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResultDTO<DTO, Entity> {

  //DTO리스트
  private List<DTO> dtoList;

  @Value("${pageUnit}")
  int pageUnit;

  @Value("${pageSize}")
  int pageSize;

  //총, 현재 페이지 번호
  private int totalPage,currentPage;

  //페이지 시작, 끝 번호
  private int start,end;

  //페이지 이전, 다음
  private boolean prev, next;

  //페이지 번호 목록
  private List<Integer> pageList;

  public PageResultDTO(Page<Entity> result, Function<Entity, DTO> fn){

    dtoList = result.stream().map(fn).collect(Collectors.toList());

    totalPage = result.getTotalPages();

    makePageList(result.getPageable());

  }

  private void makePageList(Pageable pageable){
    this.pageUnit = pageable.getPageNumber();
    this.pageSize = pageable.getPageSize();

    //temp and page
    int tempEnd = (int)(Math.ceil(pageUnit/10.0)) * 10;

    start = tempEnd -9;

    prev = start > 1;

    end = totalPage > tempEnd ? tempEnd : totalPage;

    next = totalPage > tempEnd;

    pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

  }

}
