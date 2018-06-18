package com.smart.web;

import com.smart.bean.Girl;
import com.smart.dao.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class BbsDaemon {

    @Autowired
    private GirlRepository girlRepository;

    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String index(){

        return "welcome boot ";
    }

    /**
     * query all girls
     * @return
     */
    @GetMapping("/girls")
    public List<Girl> getGirls(){
        return girlRepository.findAll();
    }

    /**
     *              add a girl
     * @param age
     * @param name
     * @param cupSize
     * @return
     */
    @PostMapping("/addgirl")
    public Girl addGirl(@RequestParam("age") Integer age, @RequestParam("name") String name,
                        @RequestParam("cupSize") String cupSize){

        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        girl.setName(name);
        return girlRepository.save(girl);
    }
    // 查询一个女生
    @GetMapping("/girl/{id}")
    public Girl getGirl(@PathVariable("id") Integer id){

         return girlRepository.findOne(id);
    }
    // 修改一个女生
    @PutMapping("/girl/{id}")
    public Girl updateGirl(@PathVariable("id") Integer id, @RequestParam("age") Integer age,
                           @RequestParam(value = "name",required = false) String name,
                           @RequestParam(value = "cupSize" ,required = false) String cupSize){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        if(cupSize!=null){ // 这里没有用， 即使不传cupSize的值， 数据库中的值也会被改为null，不灵活

            girl.setCupSize(cupSize);
        }
        girl.setName(name);
        return girlRepository.save(girl);
    }


}
