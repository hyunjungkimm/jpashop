package jpabook.jpashop.domain;

import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

public class OrderSearch {
    private String memberName; //회원 이름
    private OrderStatus orderStatus; //주문 상태[ORDER , CANCEL]

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    //추가
    public Specification<Order> toSpecification(){
        return where(OrderSpec.memberNameLike(memberName))
            .and(OrderSpec.orderStatusEq(orderStatus));
    }
}
