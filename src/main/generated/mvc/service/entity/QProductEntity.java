package mvc.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductEntity is a Querydsl query type for ProductEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductEntity extends EntityPathBase<ProductEntity> {

    private static final long serialVersionUID = -1022613004L;

    public static final QProductEntity productEntity = new QProductEntity("productEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath fileName = createString("fileName");

    public final StringPath manuDate = createString("manuDate");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> prodCount = createNumber("prodCount", Integer.class);

    public final StringPath prodDetail = createString("prodDetail");

    public final StringPath prodName = createString("prodName");

    public final NumberPath<Integer> prodNo = createNumber("prodNo", Integer.class);

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public QProductEntity(String variable) {
        super(ProductEntity.class, forVariable(variable));
    }

    public QProductEntity(Path<? extends ProductEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductEntity(PathMetadata metadata) {
        super(ProductEntity.class, metadata);
    }

}

