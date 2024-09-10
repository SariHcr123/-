package com.example.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springboot.service.IShujuService;
import com.example.springboot.entity.Shuju;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hcr
 * @since 2024-02-06
 */
@RestController
@RequestMapping("/shuju")
public class ShujuController {

    @Resource
    private IShujuService shujuService;

    @PostMapping
    public boolean save(@RequestBody Shuju shuju){

        //新增或更新
        return shujuService.saveOrUpdate(shuju);
        }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return shujuService.removeById(id);
        }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return shujuService.removeByIds(ids);
        }

    @GetMapping
    public List<Shuju> findAll() {
        return shujuService.list();
        }

    @GetMapping("/{id}")
    public Shuju findOne(@PathVariable Integer id) {
        return shujuService.getById(id);
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String num,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String address) {
        QueryWrapper<Shuju> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(num)) {
            queryWrapper.like("num", num);
        }
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        return Result.success(shujuService.page(new Page<>(pageNum, pageSize),queryWrapper));
        }

        }

