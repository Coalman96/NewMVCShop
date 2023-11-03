package mvc.service.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseDTO is a Querydsl query type for BaseDTO
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseDTO extends EntityPathBase<BaseDTO> {

    private static final long serialVersionUID = 133552881L;

    public static final QBaseDTO baseDTO = new QBaseDTO("baseDTO");

    public final DatePath<java.time.LocalDate> regDate = createDate("regDate", java.time.LocalDate.class);

    public QBaseDTO(String variable) {
        super(BaseDTO.class, forVariable(variable));
    }

    public QBaseDTO(Path<? extends BaseDTO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseDTO(PathMetadata metadata) {
        super(BaseDTO.class, metadata);
    }

}

