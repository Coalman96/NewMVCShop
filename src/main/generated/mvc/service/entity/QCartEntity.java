package mvc.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartEntity is a Querydsl query type for CartEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartEntity extends EntityPathBase<CartEntity> {

    private static final long serialVersionUID = -1948881375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartEntity cartEntity = new QCartEntity("cartEntity");

    public final ListPath<CartItemEntity, QCartItemEntity> cartItems = this.<CartItemEntity, QCartItemEntity>createList("cartItems", CartItemEntity.class, QCartItemEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DatePath<java.time.LocalDate> createDate = createDate("createDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity user;

    public QCartEntity(String variable) {
        this(CartEntity.class, forVariable(variable), INITS);
    }

    public QCartEntity(Path<? extends CartEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartEntity(PathMetadata metadata, PathInits inits) {
        this(CartEntity.class, metadata, inits);
    }

    public QCartEntity(Class<? extends CartEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

