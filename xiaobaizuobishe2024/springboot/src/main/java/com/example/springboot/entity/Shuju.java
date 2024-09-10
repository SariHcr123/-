package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hcr
 * @since 2024-02-06
 */
@Getter
@Setter
  @TableName("sys_shuju")
@ApiModel(value = "Shuju对象", description = "")
public class Shuju implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("设备编号")
      private String num;

      @ApiModelProperty("设备名称")
      private String name;

      @ApiModelProperty("设备地址")
      private String address;

      @ApiModelProperty("x偏移量")
      private String dx;

      @ApiModelProperty("y偏移量")
      private String dy;

      @ApiModelProperty("z偏移量")
      private String dz;


}
