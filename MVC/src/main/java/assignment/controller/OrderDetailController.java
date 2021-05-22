package assignment.controller;

import assignment.entity.OrderDetail;
import assignment.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<OrderDetail> getAllOrders(){
        return orderDetailService.getAllOrders();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public void addOrder(@RequestBody OrderDetail orderDetailDetail){
        orderDetailService.addOrder(orderDetailDetail);
    }

//    @RequestMapping(path = "", method = RequestMethod.DELETE)
//    public void deleteAllOrders(){
//        orderDetailService.deleteAllOrder();
//    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OrderDetail getOrderById(@PathVariable String id) {
        return orderDetailService.getOrderById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrderById(@PathVariable String id) {
        orderDetailService.deleteByOrderId(id);
    }

//    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
//    public void updateOrderById(@PathVariable String id, @RequestBody OrderDetail orderDetail) {
//        orderDetailService.updateOrderById(id, orderDetail);
//    }
}

