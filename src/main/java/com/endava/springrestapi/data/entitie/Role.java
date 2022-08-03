package com.endava.springrestapi.data.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;
      private String name;
}
