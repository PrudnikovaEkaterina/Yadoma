package ru.yadoma_realty.dataBase.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.yadoma_realty.dataBase.converter.RoleConverter;
import ru.yadoma_realty.dataBase.enums.RoleEnum;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity()
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone_verified_at")
    private Date phoneVerifiedAt;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "role")
    @Convert(converter = RoleConverter.class)
    private RoleEnum role;

    @Column(name = "status")
    private Integer status;

    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "referral_code")
    private String referral_code;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy="user")
    @Transient //поле игнорируется при запросе users из базы
    private List<CallbackPhoneEntity> callbackPhoneEntityList;
}
