package org.example.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_pet_0")
public class Pet {

    private Long id;

    private String name;

    private String owner;

    private String species;

    private String sex;

    private Integer age;

    private Date gmtCreate;

}
