package assignment.controller;

import assignment.entity.Producer;
import assignment.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;
    @RequestMapping(path = "/getProducers", method = RequestMethod.POST)
    public Map<String, String> getAllProducers(){
        List<Producer> list = producerService.getAllProducers();
        Map<String, String> map = new HashMap<>();
        for (int i = 0 ; i < list.size(); i++){
            map.put(String.valueOf(i), list.get(i).toString());
        }
        return map;
    }
}

