package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {//상품 주문 테스트

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemService itemService;

    //주문
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Item> item = itemService.findById(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.get().getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item.get(), item.get().getPrice(), count);
        //주문 생성
        Order order = Order.createOrder(member.get(), delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //주문 취소
    public void cancleOrder(Long orderId){
        //주문 엔티티 조회
        Optional<Order> order = orderRepository.findById(orderId);
        //주문 취소
        order.get().cancel();
    }

    //주문 검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch.toSpecification());
    }
}
