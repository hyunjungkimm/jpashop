package jpabook.jpashop.service;

import jpabook.jpashop.domain.Album;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품등록() throws Exception {
        //Given
        Item item = new Album();
        item.setName("album");

        //When
        itemService.saveItem(item);
        Long id = item.getId();

        //Then
        assertEquals(item, itemService.fineOne(id));
    }

    @Test
    public void 중복_상품을_등록하면_기존_상품을_수정한다() throws Exception {
        //Given
        Item item = new Book();
        item.setName("book1");
        itemService.saveItem(item);

        //When
        item.setName("book2");
        itemService.saveItem(item);
        Long id = item.getId();

        //Then
        assertEquals(item.getName(), itemService.fineOne(id).getName());
    }
}
