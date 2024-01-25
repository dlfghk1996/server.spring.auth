package server.spring.auth.common.domain;//package com.ilhwa.auth.common.domain;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.envers.AuditOverride;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@AuditOverride(forClass = AbstractEntity.class)
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public class AuditEntity<P> extends AbstractEntity {
//
//  /** created on */
//  @CreatedDate private LocalDateTime createdAt;
//
//  /** updated on */
//  @LastModifiedDate private LocalDateTime updatedAt;
//
//  /** creator */
//  @CreatedBy private P createdBy;
//
//  /** modifier */
//  @LastModifiedBy private P updatedBy;
//
//  @PrePersist
//  protected void onCreatedAt() {
//    this.updatedAt = this.createdAt = LocalDateTime.now();
//  }
//
//  @PreUpdate
//  protected void onUpdatedAt() {
//    this.updatedAt = LocalDateTime.now();
//  }
//}
