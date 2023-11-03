package mvc.service.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 1972662924L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final StringPath addr = createString("addr");

    public final QCartEntity cart;

    public final StringPath email = createString("email");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath phone1 = createString("phone1");

    public final StringPath phone2 = createString("phone2");

    public final StringPath phone3 = createString("phone3");

    //inherited
    public final DatePath<java.time.LocalDate> regDate = _super.regDate;

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath ssn = createString("ssn");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new QCartEntity(forProperty("cart"), inits.get("cart")) : null;
    }

}

