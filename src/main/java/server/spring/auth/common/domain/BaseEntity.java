/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package server.spring.auth.common.domain;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity 공통필드 정의
 * 추상클래스에 @Id 필드를 넣을 경우 의존성이 높아지기때문에
 * 넣지않는 것 권고.
 */
@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  // 생성자
  private String createdBy;

  // 생성시간
  private LocalDateTime createdDate;

  // 수정자
  private String lastModifiedBy;

  // 수정시간
  private LocalDateTime lastModifiedDate;

}
